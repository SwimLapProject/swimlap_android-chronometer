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

public class DbClubs extends SQLiteOpenHelper {

    // TABLE
    public static final String CLUBS_TABLE_NAME = "table_clubs";

    public static final String COL_CLU_ID_CLUB = "col_clu_id_race";
    public static final String COL_CLU_NAME = "col_clu_distance";

    private static final String
            CLUBS_TABLE_CREATE = "CREATE TABLE " + CLUBS_TABLE_NAME + " (" +
            COL_CLU_ID_CLUB + " INTEGER, " +
            COL_CLU_NAME + " TEXT" +
            ");";

    public DbClubs(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, Environment.getExternalStorageDirectory() + "/" + databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CLUBS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CLUBS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
