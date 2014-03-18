/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableClubs implements DbTableMODEL {
    // TABLE
    public static final String TABLE_NAME = "table_clubs";
    // COLUMNS
    public static final String COL_CLU_ID_CLUB = "col_clu_id_club";
    public static final String COL_CLU_NAME = "col_clu_name";
    private static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_CLU_ID_CLUB + " INTEGER, " +
            COL_CLU_NAME + " TEXT" +
            ");";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getRequestTableCreate() {
        return REQUEST_TABLE_CREATE;
    }

    @Override
    public String[] getAllColumnsAsStrings() {
        String[] strings = {COL_CLU_ID_CLUB, COL_CLU_NAME};
        return strings;
    }
}
