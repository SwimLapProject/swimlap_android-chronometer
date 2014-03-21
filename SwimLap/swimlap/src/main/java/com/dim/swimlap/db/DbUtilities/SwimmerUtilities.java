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

import com.dim.swimlap.db.builder.DbHelper;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;
import java.util.List;

public class SwimmerUtilities {

    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbHelper dbHelper;

    public SwimmerUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbHelper dbHelper) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.dbHelper = dbHelper;
    }


    /* SWIMMER */
    public List<SwimmerModel> getAllSwimmers() {
        List<SwimmerModel> allSwimmers = new ArrayList<SwimmerModel>();
        Cursor cursor = sqLiteDatabaseSwimLap.query("table_swimmers", dbHelper.dbTableSwimmers.getAllColumnsAsStrings(), null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SwimmerModel swimmer = getRowFromDbSwimmers(cursor);
            allSwimmers.add(swimmer);
            cursor.moveToNext();
        }
        cursor.close();
        return allSwimmers;
    }

    private SwimmerModel getRowFromDbSwimmers(Cursor cursor) {// cursorToFilm
        SwimmerModel swimmerModel = new SwimmerModel();
        swimmerModel.setIdSwimmer(cursor.getInt(0));
        swimmerModel.setName(cursor.getString(1));
        swimmerModel.setFirstname(cursor.getString(2));
        swimmerModel.setDateOfBirth(cursor.getString(3));
        swimmerModel.setGender(cursor.getString(4));
        swimmerModel.setClubId(cursor.getInt(5));
        return swimmerModel;
    }


    public void addSwimmerInDb(SwimmerModel newSimmer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.dbTableSwimmers.COL_SWI_ID_SWIMMER, newSimmer.getIdSwimmer());
        contentValues.put(dbHelper.dbTableSwimmers.COL_SWI_NAME, newSimmer.getName());
        contentValues.put(dbHelper.dbTableSwimmers.COL_SWI_FIRST_NAME, newSimmer.getFirstname());
        contentValues.put(dbHelper.dbTableSwimmers.COL_SWI_DATE_OF_BIRTH, newSimmer.getDateOfBirth());
        contentValues.put(dbHelper.dbTableSwimmers.COL_SWI_GENDER, newSimmer.getGender());
        contentValues.put(dbHelper.dbTableSwimmers.COL_CLU_CLUB_ID, newSimmer.getClubId());

        sqLiteDatabaseSwimLap.insert(dbHelper.dbTableSwimmers.TABLE_NAME, null, contentValues);
        Cursor cursor = sqLiteDatabaseSwimLap.query(dbHelper.dbTableSwimmers.TABLE_NAME, dbHelper.dbTableSwimmers.getAllColumnsAsStrings(), null, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    private boolean swimmerAlreadyInDb(int swimmerIdToSearch) {
        boolean isPresent = false;
        List<SwimmerModel> listSwimmers = getAllSwimmers();
        for (int indexSwimmer = 0; indexSwimmer < listSwimmers.size(); indexSwimmer++) {
            if (listSwimmers.get(indexSwimmer).getIdSwimmer() == swimmerIdToSearch) {
                isPresent = true;
            }
        }
        return isPresent;
    }

    private SwimmerModel getSwimmerFromDb(int swimmerIdToGet) {
        String[] swimmerIdAsStrings = {String.valueOf(swimmerIdToGet)};
        SwimmerModel swimmer = null;
        Cursor cursor = sqLiteDatabaseSwimLap.query("table_swimmers", dbHelper.dbTableSwimmers.getAllColumnsAsStrings(), dbHelper.dbTableSwimmers.COL_CLU_CLUB_ID + " = ?", swimmerIdAsStrings, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            swimmer = getRowFromDbSwimmers(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return swimmer;
    }

    public void deleteSwimmerInDb(int swimmerIdToDelete) {

    }

    public void updateSwimmerInDb(SwimmerModel modifiedSwimmer) {
    }

}
