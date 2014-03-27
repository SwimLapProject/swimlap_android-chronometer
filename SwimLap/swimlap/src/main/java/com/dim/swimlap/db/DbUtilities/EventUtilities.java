/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.builder.DbHelper;
import com.dim.swimlap.models.EventModel;

import java.util.List;

public class EventUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbHelper dbHelper;

    public EventUtilities(SQLiteDatabase sqLiteDatabaseSwimLap) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.dbHelper = dbHelper;
    }

    public List<EventModel> getAllEventsFromAMeeting(int idMeeting) {
        return null;
    }

    public List<EventModel> getAllEventsFromASwimmer(int idSwimmer) {
        return null;
    }

    public List<EventModel> getAllEventsFromAMeetingForASwimmer(int idMeeting, int idSwimmer) {
        return null;
    }

    public List<EventModel> getAllEventsFromAMeetingForARace(int idMeeting, int idRace) {
        return null;
    }

    public void addAnEventInDb(EventModel event) {

    }

    public void addAllEventOfAMeeting(List<EventModel> events){

    }

    public void updateSwimTimeOfAnEvent(EventModel eventModel){
        deleteEventInDb(eventModel);
        addAnEventInDb(eventModel);
    }

    public void deleteEventInDb(EventModel eventModel) {

    }
}
