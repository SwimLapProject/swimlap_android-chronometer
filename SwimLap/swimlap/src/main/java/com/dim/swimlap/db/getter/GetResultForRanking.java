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
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.models.SwimmerModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetResultForRanking {
    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";
    private Context context;

    public GetResultForRanking(Context context) {
        this.context = context;
    }

    public ArrayList<ResultModel> getResultBySwimmer(int idSwimmer) {
        ArrayList<ResultModel> resultsForRanking = null;
        SwimmerModel swimmer = null;
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            resultsForRanking = db1.getResultUtilities().getAllResultsBySwimmer_FromDb(idSwimmer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }
        return resultsForRanking;
    }

    public ArrayList<ResultModel> getResultByMeeting(int meetingId) {
        ArrayList<ResultModel> resultsForRanking = null;
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            resultsForRanking = db1.getResultUtilities().getAllResultsBySwimmerOrderByMeeting_FromDb(meetingId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }

        return resultsForRanking;
    }

    public ArrayList<ResultModel> getResultBySeason(int idSeason) {
        ArrayList<ResultModel> resultsForRanking = null;
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            SeasonModel season = db1.getSeasonUtilities().getSeasonById_FromDb(idSeason);
            ArrayList<MeetingModel> meetingsInSeason = db1.getMeetingUtilities().getAllMeetings_FromDb();
            for(int indexMeeting=0;indexMeeting<meetingsInSeason.size();indexMeeting++){
                int idMeeting = meetingsInSeason.get(indexMeeting).getId();
                ArrayList<ResultModel> resultsInMeeting = db1.getResultUtilities().getAllResultsBySwimmerOrderByMeeting_FromDb(idMeeting);
                for (int indexResult=0;indexResult<resultsInMeeting.size();indexResult++){
                    resultsForRanking.add(resultsInMeeting.get(indexResult));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }

        return resultsForRanking;
    }

    public ArrayList<ResultModel> getResultByRace(int raceId) {
        ArrayList<ResultModel> resultsForRanking = null;
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            resultsForRanking = db1.getResultUtilities().getAllResultsByRace_FromDb(raceId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }
        //ADD SWIMMER
        for (int indexResult = 0; indexResult < resultsForRanking.size(); indexResult++) {
            fillWithSwimmer(resultsForRanking.get(indexResult));
        }

        return resultsForRanking;
    }

    private void fillWithSwimmer(ResultModel resultToFill) {
        DbUtilitiesBuilder db2 = new DbUtilitiesBuilder(context);
        try {
            db2.open();
            SwimmerModel swimmer = db2.getSwimmerUtilities().getSwimmer_FromDb(resultToFill.getSwimmerModel().getIdFFN());
            resultToFill.getSwimmerModel().setName(swimmer.getName());
            resultToFill.getSwimmerModel().setFirstname(swimmer.getFirstname());
            resultToFill.getSwimmerModel().setGender(swimmer.getGender());
            resultToFill.getSwimmerModel().setDateOfBirth(swimmer.getDateOfBirth());
            int codeClub = resultToFill.getSwimmerModel().getClubModel().getCodeFFN();
            resultToFill.getSwimmerModel().setClubModel(fillWithClub(codeClub));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db2.close();
        }
    }

    public ClubModel fillWithClub(int codeClub) {
        ClubModel club = null;
        DbUtilitiesBuilder db3 = new DbUtilitiesBuilder(context);
        try {
            db3.open();
            club = db3.getClubUtilities().getClub_FromDb();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db3.close();
        }
        return club;
    }
}
