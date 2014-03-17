/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DbBuilder {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_swimlap.db";

    private SQLiteDatabase swimLapSQLiteDatabase;

    private DbClubs dbClubs;
    private DbEvents dbEvents;
    private DbMeetings dbMeetings;
    private DbRaces dbRaces;
    private DbRounds dbRounds;
    private DbSwimmers dbSwimmers;


    private String[] allColumns = {
            dbEvents.COL_AGE_AGEGROUP_ID,
            dbEvents.COL_EVE_ORDER,
            dbEvents.COL_EVE_QUALIFYING_TIME};

    public DbBuilder(Context context) {
        dbClubs = new DbClubs(context, DATABASE_NAME , null, DATABASE_VERSION);
        dbEvents = new DbEvents(context, DATABASE_NAME , null, DATABASE_VERSION);
        dbMeetings = new DbMeetings(context, DATABASE_NAME , null, DATABASE_VERSION);
        dbRaces = new DbRaces(context, DATABASE_NAME , null, DATABASE_VERSION);
        dbRounds = new DbRounds(context, DATABASE_NAME , null, DATABASE_VERSION);
        dbSwimmers = new DbSwimmers(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        swimLapSQLiteDatabase = dbClubs.getWritableDatabase();

    }

    public void close() {
        swimLapSQLiteDatabase.close();
    }
}
