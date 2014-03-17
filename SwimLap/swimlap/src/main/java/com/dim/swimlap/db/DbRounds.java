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

public class DbRounds extends SQLiteOpenHelper {

    // TABLE
    public static final String ROUNDS_TABLE_NAME = "table_round";

    public static final String COL_ROU_ID_ROUND = "col_rou_id_round";
    public static final String COL_ROU_NAME = "col_rou_name";





    private static final String
            ROUNDS_TABLE_CREATE = "CREATE TABLE " + ROUNDS_TABLE_NAME + " (" +
            COL_ROU_ID_ROUND + " INTEGER, " +
            COL_ROU_NAME + " TEXT " +
            ");";

    public DbRounds(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, Environment.getExternalStorageDirectory() + "/" + databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ROUNDS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ROUNDS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
