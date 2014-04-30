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

import com.dim.swimlap.db.tables.DbTableMeetings;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.SeasonModel;

import java.util.ArrayList;
import java.util.List;

public class MeetingUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableMeetings table;

    public MeetingUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableMeetings dbTableMeetings) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableMeetings;
    }

    /* GETTERS */
    public List<MeetingModel> getAllMeetings_FromDb() {
        List<MeetingModel> allMeetings = new ArrayList<MeetingModel>();
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MeetingModel meeting = getDataMeeting_FromDb(cursor);
            allMeetings.add(meeting);
            cursor.moveToNext();
        }
        cursor.close();
        return allMeetings;
    }

    public MeetingModel getOneMeetingById_FromDb(int idMeeting) {
        MeetingModel meetingModel = new MeetingModel();
        String condition = table.COL_MEE_ID + "=" + idMeeting;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            meetingModel = getDataMeeting_FromDb(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return meetingModel;
    }

    public MeetingModel getAMeetingByName_FromDb(String meetingName) {
        MeetingModel meetingModel = null;
        String condition = table.COL_MEE_MEETING_NAME + "=" + meetingName;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            meetingModel = getDataMeeting_FromDb(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return meetingModel;
    }

    /* GET CONTENT */
    private MeetingModel getDataMeeting_FromDb(Cursor cursor) {
        MeetingModel meetingModel = new MeetingModel();
        meetingModel.setId(cursor.getInt(0));
        meetingModel.setName(cursor.getString(1));
        meetingModel.setCity(cursor.getString(2));
        meetingModel.setStartDate(cursor.getString(3));
        meetingModel.setStopDate(cursor.getString(4));
        meetingModel.setPoolSize(cursor.getInt(5));
        meetingModel.setSeasonModel(new SeasonModel(cursor.getInt(6)));
        return meetingModel;
    }

    /* ADDER */
    public void addMeeting_InDb(MeetingModel meetingModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(table.COL_MEE_ID, meetingModel.getId());
        contentValues.put(table.COL_MEE_MEETING_NAME, meetingModel.getName());
        contentValues.put(table.COL_MEE_CITY, meetingModel.getCity());
        contentValues.put(table.COL_MEE_START_DATE, meetingModel.getStartDate());
        contentValues.put(table.COL_MEE_STOP_DATE, meetingModel.getStopDate());
        contentValues.put(table.COL_MEE_POOL_SIZE, meetingModel.getPoolSize());
        contentValues.put(table.COL_SEA_ID, meetingModel.getSeasonModel().getId());
        sqLiteDatabaseSwimLap.insert(table.TABLE_NAME, null, contentValues);
    }

    /* DELETER */
    public void deleteMeeting_InDb(int idMeeting) {
        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, table.COL_MEE_ID + " = " + idMeeting, null);
    }

    /* UPDATER */
    public void updateMeeting_InDb(MeetingModel meetingModel) {
        deleteMeeting_InDb(meetingModel.getId());
        addMeeting_InDb(meetingModel);
    }
    /* VERIFY ENTRY */
    //todo
}
