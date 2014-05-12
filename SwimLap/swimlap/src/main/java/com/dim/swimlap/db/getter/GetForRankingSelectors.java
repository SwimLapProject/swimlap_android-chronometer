/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.getter;

import android.content.Context;

import com.dim.swimlap.data.RaceData;
import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.models.SwimmerModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetForRankingSelectors {

    private Context context;

    public GetForRankingSelectors(Context context) {
        this.context = context;
    }
    public ArrayList<SeasonModel> getSeasonsForSelect() {
        ArrayList<SeasonModel> seasons = null;
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            seasons = db1.getSeasonUtilities().getAllSeasons_FromDb();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }

        return seasons;
    }
    public ArrayList<SwimmerModel> getSwimmersForSelect() {
        GetSwimmers getSwimmers = new GetSwimmers(context);
        return getSwimmers.getSwimmerList();
    }



    public ArrayList<MeetingModel> getMeetingsForSelect() {
        ArrayList<MeetingModel> meetings = null;
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            meetings = db1.getMeetingUtilities().getAllMeetings_FromDb();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }

        return meetings;
    }

    public ArrayList<RaceModel> getRacesForSelect() {
        ArrayList<RaceModel> races = null;
        RaceData raceData = new RaceData();
        raceData.makeData();
        for (int i = 0; i < 300; i++) {
            if (raceData.raceIdExists(i)) {
                RaceModel race = new RaceModel(i);
                races.add(race);
            }
        }
        return races;
    }
}
