/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.builder.DbHelper;

public class EventUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbHelper dbHelper;

    public EventUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbHelper dbHelper) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.dbHelper = dbHelper;
    }
}
