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

public class DbSwimmers extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_swimlap.db";

    public static final String SWIMMERS_TABLE_NAME = "table_swimmers";

    public static final String COL_SWI_ID_FFN = "col_swi_id_ffn";
    public static final String COL_SWI_NAME = "col_swi_name";
    public static final String COL_SWI_FIRST_NAME = "col_swi_firstname";
    public static final String COL_SWI_DATE_OF_BIRTH = "col_swi_date_of_birth";
    public static final String COL_SWI_GENDER = "col_swi_gender";
    public static final String COL_SWI_CLUB_ID = "col_swi_club_id";


    private static final String
            SWIMMERS_TABLE_CREATE = "CREATE TABLE " + SWIMMERS_TABLE_NAME + " (" +
            COL_SWI_ID_FFN + " INTEGER, " +
            COL_SWI_NAME + " TEXT," +
            COL_SWI_FIRST_NAME + " TEXT," +
            COL_SWI_DATE_OF_BIRTH + " TEXT," +
            COL_SWI_GENDER + " TEXT," +
            COL_SWI_CLUB_ID + " TEXT" +
            ");";

    public DbSwimmers(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Environment.getExternalStorageDirectory() + "/" + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SWIMMERS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SWIMMERS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
