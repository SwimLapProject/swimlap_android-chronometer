/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.recorder;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.SeasonModel;

import java.sql.SQLException;

public class RecordSettings {

    private DbUtilitiesBuilder db;

    public RecordSettings(Context context) {
        db = new DbUtilitiesBuilder(context);
    }

    public void recordSeason(SeasonModel seasonToAdd) {
        try {
            db.open();
            SeasonModel seasonFromDb = db.getSeasonUtilities().getSeason_FromDb(seasonToAdd.getStartDate());
            if (seasonFromDb == null) {
                db.getSeasonUtilities().addSeason_InDb(seasonToAdd);
            } else {
                seasonToAdd.setId(seasonFromDb.getId());
                db.getSeasonUtilities().addSeason_InDb(seasonToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void recordClub(ClubModel clubModel) {
        try {
            db.open();
            // only one club in application:
            // no need to update because the club in db is deleted before adding new one
            db.getClubUtilities().addClub_InDb(clubModel);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
