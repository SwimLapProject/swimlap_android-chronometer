/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.builder.DbHelper;
import com.dim.swimlap.models.ClubModel;

import java.util.List;

public class ClubUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbHelper dbHelper;

    public ClubUtilities(SQLiteDatabase sqLiteDatabaseSwimLap) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.dbHelper = dbHelper;
    }

    public void addClubInDb(ClubModel clubModel) {

    }

    public void deleteClub(ClubModel clubModel) {

    }

    public List<ClubModel> getAllClubsRecordedInDb() {
        return null;
    }
}