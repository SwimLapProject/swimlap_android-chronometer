/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DbMeetings extends SQLiteOpenHelper {

    // TABLE
    public static final String MEETINGS_TABLE_NAME = "table_meetings";

    public static final String COL_MEE_ID_MEET = "col_mee_id_meet";
    public static final String COL_MEE_MEETING_NAME = "col_mee_meeting_name";
    public static final String COL_MEE_CITY = "col_mee_city";
    public static final String COL_MEE_START_DATE = "col_mee_start_date";
    public static final String COL_MEE_STOP_DATE = "col_mee_stop_date";
    public static final String COL_MEE_POOL_SIZE = "col_mee_pool_size";
    public static final String COL_AGE_AGEGROUP_ID = "col_age_agegroup_id";


    private static final String
            MEETINGS_TABLE_CREATE = "CREATE TABLE " + MEETINGS_TABLE_NAME + " (" +
            COL_MEE_ID_MEET + " INTEGER, " +
            COL_MEE_MEETING_NAME + " TEXT, " +
            COL_MEE_CITY + " TEXT, " +
            COL_MEE_START_DATE + " TEXT, " +
            COL_MEE_STOP_DATE + " TEXT, " +
            COL_MEE_POOL_SIZE + " INTEGER, " +
            COL_AGE_AGEGROUP_ID + " INTEGER " +
            ");";

    public DbMeetings(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, Environment.getExternalStorageDirectory() + "/" + databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MEETINGS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MEETINGS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
