/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.builder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.DbUtilities.SwimmerUtilities;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;

import java.sql.SQLException;

public class DbBuilder {

    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbHelper dbHelper;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_swimlap.db";
    private SwimmerUtilities swimmerUtilities;

    /* Database fields */
    private String[] allColumns = {};

    public DbBuilder(Context context) {
        dbHelper = new DbHelper(context, DATABASE_NAME, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        sqLiteDatabaseSwimLap = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void getUtilities(){
        swimmerUtilities = new SwimmerUtilities(sqLiteDatabaseSwimLap,dbHelper);

    }

    /* *********************************************************************************************** */

    /* MEETING */
    public void addMeetingInDB(MeetingModel newMeeting) {
    }

    public void deleteMeetingInDB(int meetingId) {

    }

    public void updateSwimmer(MeetingModel modifiedMeeting) {
    }

    private void meetingAlreadyInDb(int meetingId) {

    }

    private MeetingModel getMeetingFromDb() {
        return null;
    }

    /* EVENT */
    public void addEventInDB(EventModel newEvent) {
    }

    public void deleteEventInDB(EventModel newEvent) {

    }

    public void updateEventInDB(EventModel newEvent) {
    }

    private void eventAlreadyInDb(EventModel newEvent) {

    }

    private EventModel geEventFromDb() {
        return null;
    }

}
