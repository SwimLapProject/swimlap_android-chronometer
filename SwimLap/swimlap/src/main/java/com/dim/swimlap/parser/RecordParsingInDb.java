/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */
package com.dim.swimlap.parser;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.MeetingModel;

import java.sql.SQLException;

public class RecordParsingInDb {

    private DbUtilitiesBuilder dbUtilitiesBuilder;

    public RecordParsingInDb(Context context){
        dbUtilitiesBuilder = new DbUtilitiesBuilder(context);
    }

    public void recordMeetingFromFFNex(MeetingModel meetingModel){
        try {
            dbUtilitiesBuilder.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
