/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.builder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.dim.swimlap.db.tables.DbTableClubs;
import com.dim.swimlap.db.tables.DbTableEvents;
import com.dim.swimlap.db.tables.DbTableMODEL;
import com.dim.swimlap.db.tables.DbTableMeetings;
import com.dim.swimlap.db.tables.DbTableRaces;
import com.dim.swimlap.db.tables.DbTableRecords;
import com.dim.swimlap.db.tables.DbTableRounds;
import com.dim.swimlap.db.tables.DbTableSeasons;
import com.dim.swimlap.db.tables.DbTableSwimmers;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_swimlap.db";
    private List<DbTableMODEL> tablesOfDb;

    public DbTableClubs dbTableClubs;
    public DbTableEvents dbTableEvents;
    public DbTableMeetings dbTableMeetings;
    public DbTableRaces dbTableRaces;
    public DbTableRecords dbTableRecords;
    public DbTableRounds dbTableRounds;
    public DbTableSeasons dbTableSeasons;
    public DbTableSwimmers dbTableSwimmers;

    public DbHelper(Context context, String databaseName, int dbVersion) {
        super(context, Environment.getExternalStorageDirectory() + "/" + databaseName, null, dbVersion);

        tablesOfDb = new ArrayList<DbTableMODEL>();
        dbTableClubs = new DbTableClubs();
        tablesOfDb.add(dbTableClubs);

        dbTableEvents = new DbTableEvents();
        tablesOfDb.add(dbTableEvents);

        dbTableMeetings = new DbTableMeetings();
        tablesOfDb.add(dbTableMeetings);

        dbTableRaces = new DbTableRaces();
        tablesOfDb.add(dbTableRaces);

        dbTableRecords = new DbTableRecords();
        tablesOfDb.add(dbTableRecords);

        dbTableRounds = new DbTableRounds();
        tablesOfDb.add(dbTableRounds);

        dbTableSeasons = new DbTableSeasons();
        tablesOfDb.add(dbTableSeasons);

        dbTableSwimmers = new DbTableSwimmers();
        tablesOfDb.add(dbTableSwimmers);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int indexTable = 0; indexTable < tablesOfDb.size(); indexTable++) {
            sqLiteDatabase.execSQL(tablesOfDb.get(indexTable).getRequestTableCreate());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        for (int indexTable = 0; indexTable < tablesOfDb.size(); indexTable++) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tablesOfDb.get(indexTable).getTableName());
        }
        onCreate(sqLiteDatabase);
    }

    public DbTableMODEL getTableFromDb(String tableName) {
        DbTableMODEL dbTableMODEL = null;
        for (int indexTable = 0; indexTable < tablesOfDb.size(); indexTable++) {
            if (tableName.equals(tablesOfDb.get(indexTable).getTableName())) {
                dbTableMODEL = tablesOfDb.get(indexTable);
            }
        }
        return dbTableMODEL;
    }
}
