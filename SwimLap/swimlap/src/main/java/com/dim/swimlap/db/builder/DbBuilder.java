/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.builder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.DbUtilities.ClubUtilities;
import com.dim.swimlap.db.DbUtilities.EventUtilities;
import com.dim.swimlap.db.DbUtilities.MeetingUtilities;
import com.dim.swimlap.db.DbUtilities.RacesDataBuilder;
import com.dim.swimlap.db.DbUtilities.RecordUtilities;
import com.dim.swimlap.db.DbUtilities.RoundDataBuilder;
import com.dim.swimlap.db.DbUtilities.SeasonDataBuikder;
import com.dim.swimlap.db.DbUtilities.SwimmerUtilities;

import java.sql.SQLException;

public class DbBuilder {

    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbHelper dbHelper;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_swimlap.db";

    private ClubUtilities clubUtilities;
    private EventUtilities eventUtilities;
    private MeetingUtilities meetingUtilities;
    private RacesDataBuilder racesDataBuilder;
    private RecordUtilities recordUtilities;
    private RoundDataBuilder roundDataBuilder;
    private SeasonDataBuikder seasonDataBuikder;
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

    public void buildUtilities() {
        clubUtilities = new ClubUtilities(sqLiteDatabaseSwimLap);
        eventUtilities = new EventUtilities(sqLiteDatabaseSwimLap);
        meetingUtilities = new MeetingUtilities(sqLiteDatabaseSwimLap);
        racesDataBuilder = new RacesDataBuilder(sqLiteDatabaseSwimLap);
        recordUtilities = new RecordUtilities(sqLiteDatabaseSwimLap);
        roundDataBuilder = new RoundDataBuilder(sqLiteDatabaseSwimLap);
        seasonDataBuikder = new SeasonDataBuikder(sqLiteDatabaseSwimLap);
        swimmerUtilities = new SwimmerUtilities(sqLiteDatabaseSwimLap);
    }
}
