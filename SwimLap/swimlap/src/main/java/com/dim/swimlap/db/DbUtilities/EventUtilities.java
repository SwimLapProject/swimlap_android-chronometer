/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.builder.DbHelper;
import com.dim.swimlap.db.tables.DbTableEvents;
import com.dim.swimlap.models.EventModel;

import java.util.List;

public class EventUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableEvents table;

    public EventUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableEvents dbTableEvents) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableEvents;
    }

    /* GETTERS */

    /* GET CONTENT */

    /* ADDER */

    /* DELETER */

    /* UPDATER */

    /* VERIFY ENTRY */
    //todo
}
