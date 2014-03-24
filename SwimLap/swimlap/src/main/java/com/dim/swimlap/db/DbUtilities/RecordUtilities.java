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
import com.dim.swimlap.db.tables.DbTableRecords;
import com.dim.swimlap.db.tables.DbTableSwimmers;
import com.dim.swimlap.models.RecordModel;

public class RecordUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;

    public RecordUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbHelper dbHelper) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
    }

    public RecordModel getAllRecordsFromASwimmer(int idSwimmer) {
        RecordModel recordModel = new RecordModel();
        recordModel.setSwimmerId(idSwimmer);

        String[] swimmerIdAsStrings = {String.valueOf(idSwimmer)};
        Cursor cursor = sqLiteDatabaseSwimLap.query("table_records", DbTableRecords.ALL_COLUMNS_AS_STRING_TAB, DbTableRecords.COL_RAC_RACE_ID + " = ?", swimmerIdAsStrings, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            recordModel.setSwimtimePool25(cursor.getInt(1), cursor.getFloat(2));
            recordModel.setSwimtimePool50(cursor.getInt(1), cursor.getFloat(3));
            cursor.moveToNext();
        }
        cursor.close();
        return recordModel;
    }

    public void updateARecordInDb(int idSwimmer, int idRace, float newRecordTime, int poolSize) {
        ContentValues contentValues = new ContentValues();
        RecordModel recordModelAllReadyInDB = getAllRecordsFromASwimmer(idSwimmer);
        if (poolSize == 25 && (recordModelAllReadyInDB.getSwimtimePool25(idRace) > newRecordTime || recordModelAllReadyInDB.getSwimtimePool25(idRace) == 0)) {
            contentValues.put(DbTableRecords.COL_SWI_SWIMMER_ID, idSwimmer);
            contentValues.put(DbTableRecords.COL_RAC_RACE_ID, idRace);
            contentValues.put(DbTableRecords.COL_REC_SWIMTIME_25, newRecordTime);
        }
        if (poolSize == 50 && (recordModelAllReadyInDB.getSwimtimePool50(idRace) > newRecordTime || recordModelAllReadyInDB.getSwimtimePool50(idRace) == 0)) {
            contentValues.put(DbTableRecords.COL_SWI_SWIMMER_ID, idSwimmer);
            contentValues.put(DbTableRecords.COL_RAC_RACE_ID, idRace);
            contentValues.put(DbTableRecords.COL_REC_SWIMTIME_25, newRecordTime);
        }
        String condition = DbTableRecords.COL_SWI_SWIMMER_ID + "=" + idSwimmer + " AND " + DbTableRecords.COL_RAC_RACE_ID + "=" + idRace;
        sqLiteDatabaseSwimLap.update(DbTableRecords.TABLE_NAME, contentValues, condition, null);

    }

    private float[] getARecordFromDb(int idSwimmer, int idRace, int poolSize) {
        float[] record = new float[2];
        String condition = DbTableRecords.COL_SWI_SWIMMER_ID + "=" + idSwimmer + " AND " + DbTableRecords.COL_RAC_RACE_ID + "=" + idRace;
        Cursor cursor = sqLiteDatabaseSwimLap.query("table_records", DbTableRecords.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            record[0] = cursor.getFloat(2);
            record[1] = cursor.getFloat(3);
            cursor.moveToNext();
        }
        cursor.close();
        return record;
    }

    public void deleteRecordInDb(int swimmerIdToDelete) {
        sqLiteDatabaseSwimLap.delete(DbTableSwimmers.TABLE_NAME, DbTableSwimmers.COL_CLU_CLUB_ID + " = " + swimmerIdToDelete, null);
    }

}
