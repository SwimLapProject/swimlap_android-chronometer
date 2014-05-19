/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.getter;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.ResultModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllMeetingsForListInSwimmerDetails {

    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";

    private DbUtilitiesBuilder db;

    public GetAllMeetingsForListInSwimmerDetails(Context context) {
        db = new DbUtilitiesBuilder(context);
    }

    public ArrayList<ResultModel> getAllMeetingForSwimmerId(int swimmerId) {
        ArrayList<ResultModel> resultBySwimmer = null;

        try {
            db.open();
            resultBySwimmer = db.getResultUtilities().getAllResultsBySwimmer_FromDb(swimmerId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        if (resultBySwimmer != null) {
            for (int indexResult = 0; indexResult < resultBySwimmer.size(); indexResult++) {

            }
        }
        return resultBySwimmer;
    }

    public String getMeetingName(int idMeeting) {
        String meetingName="";
        try {
            db.open();
            meetingName = db.getMeetingUtilities().getMeetingName_FromDb(idMeeting);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
       return meetingName;
    }
}
