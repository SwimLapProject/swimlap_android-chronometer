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
import com.dim.swimlap.db.tables.DbTableMeetings;
import com.dim.swimlap.db.tables.DbTableRecords;
import com.dim.swimlap.models.MeetingModel;

import java.util.ArrayList;
import java.util.List;

public class MeetingUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbHelper dbHelper;

    public MeetingUtilities(SQLiteDatabase sqLiteDatabaseSwimLap) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.dbHelper = dbHelper;
    }


    public List<MeetingModel> getAllMeetingsFromDb() {
        List<MeetingModel> allMeetings = new ArrayList<MeetingModel>();
        Cursor cursor = sqLiteDatabaseSwimLap.query(DbTableMeetings.TABLE_NAME, DbTableMeetings.ALL_COLUMNS_AS_STRING_TAB, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MeetingModel meeting = getDataMeetingFromDb(cursor);
            allMeetings.add(meeting);
            cursor.moveToNext();
        }
        cursor.close();
        return allMeetings;
    }

    public MeetingModel getAMeetingByIdFromDb(int idMeeting) {
        MeetingModel meetingModel = new MeetingModel();
        String condition = DbTableMeetings.COL_MEE_ID_MEET + "=" + idMeeting;
        Cursor cursor = sqLiteDatabaseSwimLap.query(DbTableMeetings.TABLE_NAME, DbTableRecords.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cursor.moveToNext();
        }
        cursor.close();
        return meetingModel;
    }

    public MeetingModel getAMeetingByNameFromDb(String meetingName) {
        MeetingModel meetingModel = new MeetingModel();
        String condition = DbTableMeetings.COL_MEE_MEETING_NAME + "=" + meetingName;
        Cursor cursor = sqLiteDatabaseSwimLap.query(DbTableMeetings.TABLE_NAME, DbTableRecords.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cursor.moveToNext();
        }
        cursor.close();
        return meetingModel;
    }


    private MeetingModel getDataMeetingFromDb(Cursor cursor) {
        MeetingModel meetingModel = new MeetingModel();
        meetingModel.setIdMeeting(cursor.getInt(0));
        meetingModel.setName(cursor.getString(1));
        meetingModel.setCity(cursor.getString(2));
        meetingModel.setStartDate(cursor.getString(3));
        meetingModel.setStopDate(cursor.getString(4));
        meetingModel.setPoolSize(cursor.getInt(5));
        meetingModel.setAgegroupId(cursor.getInt(6));
        meetingModel.setSeasonId(cursor.getInt(7));
        return meetingModel;
    }

    public void addMeetingInDb(MeetingModel meetingModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbTableMeetings.COL_MEE_ID_MEET, meetingModel.getIdMeeting());
        contentValues.put(DbTableMeetings.COL_MEE_MEETING_NAME, meetingModel.getName());
        contentValues.put(DbTableMeetings.COL_MEE_CITY, meetingModel.getCity());
        contentValues.put(DbTableMeetings.COL_MEE_START_DATE, meetingModel.getStartDate());
        contentValues.put(DbTableMeetings.COL_MEE_STOP_DATE, meetingModel.getStopDate());
        contentValues.put(DbTableMeetings.COL_MEE_POOL_SIZE, meetingModel.getPoolSize());
        contentValues.put(DbTableMeetings.COL_AGE_AGEGROUP_ID, meetingModel.getAgegroupId());
        contentValues.put(DbTableMeetings.COL_SEA_ID, meetingModel.getSeasonId());
        sqLiteDatabaseSwimLap.insert(DbTableMeetings.TABLE_NAME, null, contentValues);
    }

    public void deleteMeetingInDb(int idMeeting) {
        sqLiteDatabaseSwimLap.delete(DbTableMeetings.TABLE_NAME, DbTableMeetings.COL_MEE_ID_MEET + " = " + idMeeting, null);
    }

    public void updateMeeting(MeetingModel meetingModel) {
        deleteMeetingInDb(meetingModel.getIdMeeting());
        addMeetingInDb(meetingModel);
    }

}