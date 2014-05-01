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

import com.dim.swimlap.db.tables.DbTableSeasons;
import com.dim.swimlap.models.SeasonModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SeasonUtilities {
    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableSeasons table;

    public SeasonUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableSeasons dbTableSeasons) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableSeasons;
    }

    /* GETTERS */
    public SeasonModel getSeason_FromDb(String dateAsString) {
        SeasonModel seasonToFind = null;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SeasonModel seasonFromDb = getDataSeason_FromDb(cursor);
            if (seasonFromDb == null) {
                seasonToFind = new SeasonModel(0);
            } else {
                SimpleDateFormat format = new SimpleDateFormat(FFNEX_DATE_FORMAT);
                try {
                    Date startDate = format.parse(seasonFromDb.getStartDate());
                    Date stopDate = format.parse(seasonFromDb.getStopDate());
                    Date currentDate = format.parse(dateAsString);
                    if (startDate.before(currentDate) && stopDate.after(currentDate)) {
                        seasonToFind = seasonFromDb;
                    }

                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        return seasonToFind;
    }

    /* GET CONTENT */
    private SeasonModel getDataSeason_FromDb(Cursor cursor) {
        SeasonModel seasonModel = new SeasonModel(cursor.getInt(0));
        seasonModel.setName(cursor.getString(1));
        seasonModel.setStartDate(cursor.getString(2));
        seasonModel.setStartDate(cursor.getString(3));

        return seasonModel;
    }

    /* ADDER */
    public void addSeason_InDb(SeasonModel seasonModel) {
        ContentValues contentValues = new ContentValues();
        int startDateToBeId;
        if (seasonModel.getId() == 0) {
            startDateToBeId = Integer.valueOf(seasonModel.getStartDate().substring(0, 4));
        } else {
            startDateToBeId = seasonModel.getId();
        }
        contentValues.put(table.COL_SEA_ID, startDateToBeId);
        contentValues.put(table.COL_SEA_NAME, seasonModel.getName());
        contentValues.put(table.COL_SEA_DATE_START, seasonModel.getStartDate());
        contentValues.put(table.COL_SEA_DATE_STOP, seasonModel.getStopDate());

        sqLiteDatabaseSwimLap.insert(table.TABLE_NAME, null, contentValues);
    }

    /* DELETER */
    public void deleteSeason_InDb(SeasonModel seasonModel) {
        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, null, null);
    }

    /* UPDATER */
    public void updateSeason_InDb(SeasonModel seasonModel) {
        deleteSeason_InDb(seasonModel);
        addSeason_InDb(seasonModel);
    }

    /* VERIFY ENTRY */
    //todo
}
