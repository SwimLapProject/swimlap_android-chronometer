/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.tables.DbTableSwimmers;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;
import java.util.List;

public class SwimmerUtilities {

    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableSwimmers table;

    public SwimmerUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableSwimmers dbTableSwimmers) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableSwimmers;
    }

    /* GETTERS */
    public List<SwimmerModel> getAllSwimmers_FromDb() {
        List<SwimmerModel> allSwimmers = new ArrayList<SwimmerModel>();
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SwimmerModel swimmer = getDataSwimmer_FromDb(cursor);
            allSwimmers.add(swimmer);
            cursor.moveToNext();
        }
        cursor.close();
        return allSwimmers;
    }

    private SwimmerModel getSwimmer_FromDb(int swimmerIdToGet) {
        String[] swimmerIdAsStrings = {String.valueOf(swimmerIdToGet)};
        SwimmerModel swimmer = null;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, table.COL_SWI_ID + " = ?", swimmerIdAsStrings, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            swimmer = getDataSwimmer_FromDb(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return swimmer;
    }

    /* GET CONTENT */
    private SwimmerModel getDataSwimmer_FromDb(Cursor cursor) {// cursorToFilm
        SwimmerModel swimmerModel = new SwimmerModel();
        swimmerModel.setIdFFN(cursor.getInt(0));
        swimmerModel.setName(cursor.getString(1));
        swimmerModel.setFirstname(cursor.getString(2));
        swimmerModel.setDateOfBirth(cursor.getString(3));
        swimmerModel.setGender(cursor.getString(4));
        swimmerModel.setClubModel(new ClubModel(cursor.getInt(5)));
        return swimmerModel;
    }


    /* ADDER */
    public void addSwimmer_InDb(SwimmerModel newSimmer) {
        if (swimmerAlready_InDb(newSimmer.getIdFFN())) {
            //do notthing
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(table.COL_SWI_ID, newSimmer.getIdFFN());
            contentValues.put(table.COL_SWI_NAME, newSimmer.getName());
            contentValues.put(table.COL_SWI_FIRST_NAME, newSimmer.getFirstname());
            contentValues.put(table.COL_SWI_DATE_OF_BIRTH, newSimmer.getDateOfBirth());
            contentValues.put(table.COL_SWI_GENDER, newSimmer.getGender());
            contentValues.put(table.COL_CLU_CLUB_CODE, newSimmer.getClubModel().getCodeFFN());

            sqLiteDatabaseSwimLap.insert(table.TABLE_NAME, null, contentValues);
        }
    }

    /* DELETER */
    public void deleteSwimmer_InDb(int swimmerIdToDelete) {
        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, table.COL_SWI_ID + " = " + swimmerIdToDelete, null);
        //todo ON DELETE CASCADE FOR RECORDS EVENTS ??
    }

    /* UPDATER */
    public void updateSwimmer_InDb(SwimmerModel modifiedSwimmer) {
        deleteSwimmer_InDb(modifiedSwimmer.getIdFFN());
        addSwimmer_InDb(modifiedSwimmer);
    }

    /* VERIFY ENTRY */
    public boolean swimmerAlready_InDb(int swimmerIdToSearch) {
        boolean isPresent;
        if (getSwimmer_FromDb(swimmerIdToSearch) == null) {
            isPresent = false;
        } else {
            isPresent = true;
        }
        return isPresent;
    }


}
