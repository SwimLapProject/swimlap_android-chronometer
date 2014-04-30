/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.tables.DbTableSeasons;

public class SeasonDataBuikder {

    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableSeasons table;

    public SeasonDataBuikder(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableSeasons dbTableSeasons) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableSeasons;
    }

    private void buildData() {

    }
}
