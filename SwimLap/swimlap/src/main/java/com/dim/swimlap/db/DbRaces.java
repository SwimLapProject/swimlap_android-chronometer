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

public class DbRaces extends SQLiteOpenHelper {

    // TABLE
    public static final String RACES_TABLE_NAME = "table_races";

    public static final String COL_RAC_ID_RACE = "col_rac_id_race";
    public static final String COL_RAC_DISTANCE = "col_rac_distance";
    public static final String COL_RAC_STYLE = "col_rac_style";
    public static final String COL_RAC_IS_RELAY = "col_rac_is_relay";
    public static final String COL_RAC_GENDER = "col_rac_gender";




    private static final String
            RACES_TABLE_CREATE = "CREATE TABLE " + RACES_TABLE_NAME + " (" +
            COL_RAC_ID_RACE + " INTEGER, " +
            COL_RAC_DISTANCE + " INTEGER, " +
            COL_RAC_STYLE + " TEXT, " +
            COL_RAC_IS_RELAY + " INTEGER, " +
            COL_RAC_GENDER + " TEXT " +
            ");";

    public DbRaces(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, Environment.getExternalStorageDirectory() + "/" + databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(RACES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RACES_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
