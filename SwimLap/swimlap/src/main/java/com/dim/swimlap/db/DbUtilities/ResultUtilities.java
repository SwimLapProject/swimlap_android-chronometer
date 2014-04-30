/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.tables.DbTableResults;

public class ResultUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableResults table;

    public ResultUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableResults dbTableResults) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableResults;
    }
}
