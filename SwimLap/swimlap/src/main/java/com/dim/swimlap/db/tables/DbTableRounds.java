/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableRounds implements DbTableMODEL {
    // ROUND can be heat, final etc.
    // TABLE
    public static final String TABLE_NAME = "table_round";
    // COLUMNS
    public static final String COL_ROU_ID_ROUND = "col_rou_id_round";
    public static final String COL_ROU_NAME = "col_rou_name";
    public static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ROU_ID_ROUND + " INTEGER, " +
            COL_ROU_NAME + " TEXT " +
            ");";

    public static final String[] ALL_COLUMNS_AS_STRING_TAB = {COL_ROU_ID_ROUND, COL_ROU_NAME};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getRequestTableCreate() {
        return REQUEST_TABLE_CREATE;
    }
}
