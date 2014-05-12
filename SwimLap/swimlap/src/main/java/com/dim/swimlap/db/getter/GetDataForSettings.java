/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.getter;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.objects.DateTransformer;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDataForSettings {
    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";

    private DbUtilitiesBuilder db;

    public GetDataForSettings(Context context) {
        db = new DbUtilitiesBuilder(context);
    }

    public ClubModel getClubRecordedInDb() {
        ClubModel club = null;
        try{
            db.open();
            club = db.getClubUtilities().getClub_FromDb();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        if (club == null) {
            // give an empty club if no club recorded in db
            club = new ClubModel(0, 888888888);
            club.setName("Club Name");
        }

        return club;
    }

    public SeasonModel getCurrentSeasonInDb() {
        SimpleDateFormat format = new SimpleDateFormat(FFNEX_DATE_FORMAT);
        String todayAsString = format.format(new Date());
        SeasonModel season = null;
        try{
            db.open();
            season = db.getSeasonUtilities().getSeasonByDate_FromDb(todayAsString);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        if (season == null) {
            // give an empty season if no season corresponds to today
            DateTransformer transformer = new DateTransformer();
            season = new SeasonModel(transformer.getTodayAsString());
        }
        return season;
    }
}
