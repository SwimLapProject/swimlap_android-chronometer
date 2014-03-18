/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableRaces implements DbTableMODEL {

    // TABLE
    public static final String TABLE_NAME = "table_races";
    // COLUMNS
    public static final String COL_RAC_ID_RACE = "col_rac_id_race";
    public static final String COL_RAC_DISTANCE = "col_rac_distance";
    public static final String COL_RAC_STYLE = "col_rac_style";
    public static final String COL_RAC_IS_RELAY = "col_rac_is_relay";
    public static final String COL_RAC_GENDER = "col_rac_gender";

    private static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_RAC_ID_RACE + " INTEGER, " +
            COL_RAC_DISTANCE + " INTEGER, " +
            COL_RAC_STYLE + " TEXT, " +
            COL_RAC_IS_RELAY + " INTEGER, " +
            COL_RAC_GENDER + " TEXT " +
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
        String[] strings = {COL_RAC_ID_RACE,
                COL_RAC_DISTANCE,
                COL_RAC_STYLE,
                COL_RAC_IS_RELAY,
                COL_RAC_GENDER};
        return strings;
    }
}
