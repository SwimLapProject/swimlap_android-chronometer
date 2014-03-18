/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableSwimmers implements DbTableMODEL {
    // TABLE
    public static final String TABLE_NAME = "table_swimmers";
    // COLUMNS
    public static final String COL_SWI_ID_SWIMMER = "col_swi_id_swimmer";
    public static final String COL_SWI_NAME = "col_swi_name";
    public static final String COL_SWI_FIRST_NAME = "col_swi_firstname";
    public static final String COL_SWI_DATE_OF_BIRTH = "col_swi_date_of_birth";
    public static final String COL_SWI_GENDER = "col_swi_gender";
    public static final String COL_CLU_CLUB_ID = "col_clu_club_id";


    private static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_SWI_ID_SWIMMER + " INTEGER, " +
            COL_SWI_NAME + " TEXT," +
            COL_SWI_FIRST_NAME + " TEXT," +
            COL_SWI_DATE_OF_BIRTH + " TEXT," +
            COL_SWI_GENDER + " TEXT," +
            COL_CLU_CLUB_ID + " TEXT" +
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
        String[] strings = {COL_SWI_ID_SWIMMER,
                COL_SWI_NAME,
                COL_SWI_FIRST_NAME,
                COL_SWI_FIRST_NAME,
                COL_SWI_DATE_OF_BIRTH,
                COL_SWI_GENDER,
                COL_CLU_CLUB_ID};
        return strings;
    }
}
