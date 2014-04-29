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

    public SwimmerUtilities(SQLiteDatabase sqLiteDatabaseSwimLap) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
    }

    public List<SwimmerModel> getAllSwimmers() {
        List<SwimmerModel> allSwimmers = new ArrayList<SwimmerModel>();
        Cursor cursor = sqLiteDatabaseSwimLap.query(DbTableSwimmers.TABLE_NAME,DbTableSwimmers.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
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
        swimmerModel.setIdFFN(cursor.getInt(0));
        swimmerModel.setName(cursor.getString(1));
        swimmerModel.setFirstname(cursor.getString(2));
        swimmerModel.setDateOfBirth(cursor.getString(3));
        swimmerModel.setGender(cursor.getString(4));
        swimmerModel.setClubModel(new ClubModel(cursor.getInt(5)));
        return swimmerModel;
    }


    public void addSwimmerInDb(SwimmerModel newSimmer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbTableSwimmers.COL_SWI_ID_SWIMMER, newSimmer.getIdFFN());
        contentValues.put(DbTableSwimmers.COL_SWI_NAME, newSimmer.getName());
        contentValues.put(DbTableSwimmers.COL_SWI_FIRST_NAME, newSimmer.getFirstname());
        contentValues.put(DbTableSwimmers.COL_SWI_DATE_OF_BIRTH, newSimmer.getDateOfBirth());
        contentValues.put(DbTableSwimmers.COL_SWI_GENDER, newSimmer.getGender());
        contentValues.put(DbTableSwimmers.COL_CLU_CLUB_ID, newSimmer.getClubModel().getId());

        sqLiteDatabaseSwimLap.insert(DbTableSwimmers.TABLE_NAME, null, contentValues);
        Cursor cursor = sqLiteDatabaseSwimLap.query(DbTableSwimmers.TABLE_NAME,DbTableSwimmers.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    private boolean swimmerAlreadyInDb(int swimmerIdToSearch) {
        boolean isPresent;
        if(getSwimmerFromDb(swimmerIdToSearch)==null){
            isPresent = false;
        }else{
            isPresent = true;
        }
        return isPresent;
    }

    private SwimmerModel getSwimmerFromDb(int swimmerIdToGet) {
        String[] swimmerIdAsStrings = {String.valueOf(swimmerIdToGet)};
        SwimmerModel swimmer = null;
        Cursor cursor = sqLiteDatabaseSwimLap.query(DbTableSwimmers.TABLE_NAME, DbTableSwimmers.ALL_COLUMNS_AS_STRING_TAB,DbTableSwimmers.COL_CLU_CLUB_ID + " = ?", swimmerIdAsStrings, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            swimmer = getRowFromDbSwimmers(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return swimmer;
    }

    public void deleteSwimmerInDb(int swimmerIdToDelete) {
        sqLiteDatabaseSwimLap.delete(DbTableSwimmers.TABLE_NAME, DbTableSwimmers.COL_CLU_CLUB_ID + " = " + swimmerIdToDelete, null);
        //todo ON DELETE CASCADE FOR RECORDS EVENTS ??
    }

    public void updateSwimmerInDb(SwimmerModel modifiedSwimmer) {
        deleteSwimmerInDb(modifiedSwimmer.getIdFFN());
        addSwimmerInDb(modifiedSwimmer);
    }
}
