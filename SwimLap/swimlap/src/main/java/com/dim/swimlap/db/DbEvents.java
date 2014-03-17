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

public class DbEvents extends SQLiteOpenHelper {

/* THIS TABLE *** EVENTS *** IS A JOIN TABLE FROM SWIMMERS, RACES AND MEETINGS */

    // TABLE
    public static final String EVENTS_TABLE_NAME = "table_events";

    // FOREIGN KEYS AS PRIMARY KEYS
    public static final String COL_SWI_ID_FFN = "col_swi_id_ffn";
    public static final String COL_MEE_ID_MEET = "col_mee_id_meet";
    public static final String COL_RAC_ID_RACE = "col_rac_id_race"; // if null : type is not a race (then type is event)

    // MEETING SPECIFICATIONS
    public static final String COL_EVE_ROUND_ID = "col_rou_id_round"; // event format: heat=60 , final=11 etc //todo
    public static final String COL_EVE_ORDER = "col_eve_order"; //order of events during meeting
    
    // SWIMMER SPECIFICATIONS
    public static final String COL_EVE_QUALIFYING_TIME = "col_eve_qualifying_time"; // IMPORTANT !!!
    public static final String COL_EVE_SWIM_TIME = "col_eve_swim_time"; // final time
    public static final String COL_AGE_AGEGROUP_ID = "col_age_agegroup_id";// optional
    
    // RACE SPECIFICATION
    public static final String COL_EVE_SPLIT_25 = "col_eve_split_25";
    public static final String COL_EVE_SPLIT_50 = "col_eve_split_50";
    public static final String COL_EVE_SPLIT_75 = "col_eve_split_75";
    public static final String COL_EVE_SPLIT_100 = "col_eve_split_100";
    public static final String COL_EVE_SPLIT_125 = "col_eve_split_125";
    public static final String COL_EVE_SPLIT_150 = "col_eve_split_150";
    public static final String COL_EVE_SPLIT_175 = "col_eve_split_175";
    public static final String COL_EVE_SPLIT_200 = "col_eve_split_200";
    public static final String COL_EVE_SPLIT_225 = "col_eve_split_225";
    public static final String COL_EVE_SPLIT_250 = "col_eve_split_250";
    public static final String COL_EVE_SPLIT_275 = "col_eve_split_275";
    public static final String COL_EVE_SPLIT_300 = "col_eve_split_300";
    public static final String COL_EVE_SPLIT_325 = "col_eve_split_325";
    public static final String COL_EVE_SPLIT_350 = "col_eve_split_350";
    public static final String COL_EVE_SPLIT_375 = "col_eve_split_375";
    public static final String COL_EVE_SPLIT_400 = "col_eve_split_400";
    public static final String COL_EVE_SPLIT_425 = "col_eve_split_425";
    public static final String COL_EVE_SPLIT_450 = "col_eve_split_450";
    public static final String COL_EVE_SPLIT_475 = "col_eve_split_475";
    public static final String COL_EVE_SPLIT_500 = "col_eve_split_500";
    public static final String COL_EVE_SPLIT_525 = "col_eve_split_525";
    public static final String COL_EVE_SPLIT_550 = "col_eve_split_550";
    public static final String COL_EVE_SPLIT_575 = "col_eve_split_575";
    public static final String COL_EVE_SPLIT_600 = "col_eve_split_600";
    public static final String COL_EVE_SPLIT_625 = "col_eve_split_625";
    public static final String COL_EVE_SPLIT_650 = "col_eve_split_650";
    public static final String COL_EVE_SPLIT_675 = "col_eve_split_675";
    public static final String COL_EVE_SPLIT_700 = "col_eve_split_700";
    public static final String COL_EVE_SPLIT_725 = "col_eve_split_725";
    public static final String COL_EVE_SPLIT_750 = "col_eve_split_750";
    public static final String COL_EVE_SPLIT_775 = "col_eve_split_775";
    public static final String COL_EVE_SPLIT_800 = "col_eve_split_800";
    public static final String COL_EVE_SPLIT_825 = "col_eve_split_825";
    public static final String COL_EVE_SPLIT_850 = "col_eve_split_850";
    public static final String COL_EVE_SPLIT_875 = "col_eve_split_875";
    public static final String COL_EVE_SPLIT_900 = "col_eve_split_900";
    public static final String COL_EVE_SPLIT_925 = "col_eve_split_925";
    public static final String COL_EVE_SPLIT_950 = "col_eve_split_950";
    public static final String COL_EVE_SPLIT_975 = "col_eve_split_975";
    public static final String COL_EVE_SPLIT_1000 = "col_eve_split_1000";
    public static final String COL_EVE_SPLIT_1025 = "col_eve_split_1025";
    public static final String COL_EVE_SPLIT_1050 = "col_eve_split_1050";
    public static final String COL_EVE_SPLIT_1075 = "col_eve_split_1075";
    public static final String COL_EVE_SPLIT_1100 = "col_eve_split_1100";
    public static final String COL_EVE_SPLIT_1125 = "col_eve_split_1125";
    public static final String COL_EVE_SPLIT_1150 = "col_eve_split_1150";
    public static final String COL_EVE_SPLIT_1175 = "col_eve_split_1175";
    public static final String COL_EVE_SPLIT_1200 = "col_eve_split_1200";
    public static final String COL_EVE_SPLIT_1225 = "col_eve_split_1225";
    public static final String COL_EVE_SPLIT_1250 = "col_eve_split_1250";
    public static final String COL_EVE_SPLIT_1275 = "col_eve_split_1275";
    public static final String COL_EVE_SPLIT_1300 = "col_eve_split_1300";
    public static final String COL_EVE_SPLIT_1325 = "col_eve_split_1325";
    public static final String COL_EVE_SPLIT_1350 = "col_eve_split_1350";
    public static final String COL_EVE_SPLIT_1375 = "col_eve_split_1375";
    public static final String COL_EVE_SPLIT_1400 = "col_eve_split_1400";
    public static final String COL_EVE_SPLIT_1425 = "col_eve_split_1425";
    public static final String COL_EVE_SPLIT_1450 = "col_eve_split_1450";
    public static final String COL_EVE_SPLIT_1475 = "col_eve_split_1475";
    public static final String COL_EVE_SPLIT_1500 = "col_eve_split_1500";



    private static final String
            EVENTS_TABLE_CREATE = "CREATE TABLE " + EVENTS_TABLE_NAME + " (" +

            COL_SWI_ID_FFN + " INTEGER , " +
            COL_MEE_ID_MEET + " INTEGER , " +
            COL_RAC_ID_RACE + " INTEGER , " +

            // MEETING SPECIFICATIONS
            COL_EVE_ROUND_ID + " INTEGER , " +
            COL_EVE_ORDER + " INTEGER , " +

            // SWIMMER SPECIFICATIONS
            COL_EVE_QUALIFYING_TIME + " REAL , " +
            COL_EVE_SWIM_TIME + " REAL , " +
            COL_AGE_AGEGROUP_ID + " INTEGER , " +

            // RACE SPECIFICATION
            COL_EVE_SPLIT_25 + " REAL , " +
            COL_EVE_SPLIT_50 + " REAL , " +
            COL_EVE_SPLIT_75 + " REAL , " +
            COL_EVE_SPLIT_100 + " REAL , " +
            COL_EVE_SPLIT_125 + " REAL , " +
            COL_EVE_SPLIT_150 + " REAL , " +
            COL_EVE_SPLIT_175 + " REAL , " +
            COL_EVE_SPLIT_200 + " REAL , " +
            COL_EVE_SPLIT_225 + " REAL , " +
            COL_EVE_SPLIT_250 + " REAL , " +
            COL_EVE_SPLIT_275 + " REAL , " +
            COL_EVE_SPLIT_300 + " REAL , " +
            COL_EVE_SPLIT_325 + " REAL , " +
            COL_EVE_SPLIT_350 + " REAL , " +
            COL_EVE_SPLIT_375 + " REAL , " +
            COL_EVE_SPLIT_400 + " REAL , " +
            COL_EVE_SPLIT_425 + " REAL , " +
            COL_EVE_SPLIT_450 + " REAL , " +
            COL_EVE_SPLIT_475 + " REAL , " +
            COL_EVE_SPLIT_500 + " REAL , " +
            COL_EVE_SPLIT_525 + " REAL , " +
            COL_EVE_SPLIT_550 + " REAL , " +
            COL_EVE_SPLIT_575 + " REAL , " +
            COL_EVE_SPLIT_600 + " REAL , " +
            COL_EVE_SPLIT_625 + " REAL , " +
            COL_EVE_SPLIT_650 + " REAL , " +
            COL_EVE_SPLIT_675 + " REAL , " +
            COL_EVE_SPLIT_700 + " REAL , " +
            COL_EVE_SPLIT_725 + " REAL , " +
            COL_EVE_SPLIT_750 + " REAL , " +
            COL_EVE_SPLIT_775 + " REAL , " +
            COL_EVE_SPLIT_800 + " REAL , " +
            COL_EVE_SPLIT_825 + " REAL , " +
            COL_EVE_SPLIT_850 + " REAL , " +
            COL_EVE_SPLIT_875 + " REAL , " +
            COL_EVE_SPLIT_900 + " REAL , " +
            COL_EVE_SPLIT_925 + " REAL , " +
            COL_EVE_SPLIT_950 + " REAL , " +
            COL_EVE_SPLIT_975 + " REAL , " +
            COL_EVE_SPLIT_1000 + " REAL , " +
            COL_EVE_SPLIT_1025 + " REAL , " +
            COL_EVE_SPLIT_1050 + " REAL , " +
            COL_EVE_SPLIT_1075 + " REAL , " +
            COL_EVE_SPLIT_1100 + " REAL , " +
            COL_EVE_SPLIT_1125 + " REAL , " +
            COL_EVE_SPLIT_1150 + " REAL , " +
            COL_EVE_SPLIT_1175 + " REAL , " +
            COL_EVE_SPLIT_1200 + " REAL , " +
            COL_EVE_SPLIT_1225 + " REAL , " +
            COL_EVE_SPLIT_1250 + " REAL , " +
            COL_EVE_SPLIT_1275 + " REAL , " +
            COL_EVE_SPLIT_1300 + " REAL , " +
            COL_EVE_SPLIT_1325 + " REAL , " +
            COL_EVE_SPLIT_1350 + " REAL , " +
            COL_EVE_SPLIT_1375 + " REAL , " +
            COL_EVE_SPLIT_1400 + " REAL , " +
            COL_EVE_SPLIT_1425 + " REAL , " +
            COL_EVE_SPLIT_1450 + " REAL , " +
            COL_EVE_SPLIT_1475 + " REAL , " +
            COL_EVE_SPLIT_1500 + " REAL " +
            ");";

    public DbEvents(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, Environment.getExternalStorageDirectory() + "/" + databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EVENTS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
