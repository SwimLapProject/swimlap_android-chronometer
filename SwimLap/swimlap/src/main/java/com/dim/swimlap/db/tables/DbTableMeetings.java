/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.tables;

public class DbTableMeetings implements DbTableMODEL {
    // TABLE
    public static final String TABLE_NAME = "table_meetings";
    // COLUMNS
    public static final String COL_MEE_ID_MEET = "col_mee_id_meet";
    public static final String COL_MEE_MEETING_NAME = "col_mee_meeting_name";
    public static final String COL_MEE_CITY = "col_mee_city";
    public static final String COL_MEE_START_DATE = "col_mee_start_date";
    public static final String COL_MEE_STOP_DATE = "col_mee_stop_date";
    public static final String COL_MEE_POOL_SIZE = "col_mee_pool_size";
    public static final String COL_AGE_AGEGROUP_ID = "col_age_agegroup_id";
    public static final String COL_SEA_ID = "col_sea_id";


    public static final String
            REQUEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_MEE_ID_MEET + " INTEGER, " +
            COL_MEE_MEETING_NAME + " TEXT, " +
            COL_MEE_CITY + " TEXT, " +
            COL_MEE_START_DATE + " TEXT, " +
            COL_MEE_STOP_DATE + " TEXT, " +
            COL_MEE_POOL_SIZE + " INTEGER, " +
            COL_AGE_AGEGROUP_ID + " INTEGER, " +
            COL_SEA_ID + " INTEGER " +
            ");";

    public static final String[] ALL_COLUMNS_AS_STRING_TAB = {COL_MEE_ID_MEET,
            COL_MEE_MEETING_NAME,
            COL_MEE_CITY,
            COL_MEE_START_DATE,
            COL_MEE_STOP_DATE,
            COL_MEE_POOL_SIZE,
            COL_AGE_AGEGROUP_ID,
            COL_SEA_ID};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getRequestTableCreate() {
        return REQUEST_TABLE_CREATE;
    }
}