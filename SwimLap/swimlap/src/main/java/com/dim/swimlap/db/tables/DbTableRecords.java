/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableRecords implements DbTableMODEL {

    // TABLE
    public static final String TABLE_NAME = "table_records";
    // COLUMNS
    public static final String COL_SWI_SWIMMER_ID = "col_swi_swimmer_id";
    public static final String COL_RAC_RACE_ID = "col_rac_race_id";
    public static final String COL_REC_SWIMTIME_25 = "col_rec_swimtime_25";
    public static final String COL_REC_SWIMTIME_50 = "col_rec_swimtime_50";

    private static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_SWI_SWIMMER_ID + " INTEGER, " +
            COL_RAC_RACE_ID + " INTEGER, " +
            COL_REC_SWIMTIME_25 + " REAL, " +
            COL_REC_SWIMTIME_50 + " REAL " +
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
        String[] strings = {COL_SWI_SWIMMER_ID,
                COL_RAC_RACE_ID,
                COL_REC_SWIMTIME_25,
                COL_REC_SWIMTIME_50};
        return strings;
    }
}
