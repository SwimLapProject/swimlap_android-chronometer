/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.objects;

import android.content.Context;

import com.dim.swimlap.db.getter.GetResultsForLapView;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;

import java.util.ArrayList;

public final class Singleton {

    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";

    private static volatile Singleton instance = null;
    private MeetingModel meetingOfTheDay;
    private int currentRaceId;
    private ArrayList<EventModel> allEventsByOrder;

    private Singleton() {
        super();
    }

    public final static Singleton getInstance() {
        if (Singleton.instance == null) {
            synchronized (Singleton.class) {
                if (Singleton.instance == null) {
                    Singleton.instance = new Singleton();
                }
            }
        }
        return Singleton.instance;
    }

    public boolean buildEvent(Context context) {
        boolean meetingOfTheDayIsBuilt = false;
        if (meetingOfTheDay != null) {
            meetingOfTheDayIsBuilt = true;
        } else {
            GetResultsForLapView getter = new GetResultsForLapView(context);
            meetingOfTheDay = getter.getMeetingOfTheDay();

        }
        if (meetingOfTheDay != null) {
            meetingOfTheDayIsBuilt = true;
            builRaceOrder();
            if (currentRaceId == 0) {
                setFirstIdRaceOfResults();
            }
        }

        return meetingOfTheDayIsBuilt;
    }

    public ResultModel getResultOfTheDay(int eventPosition) {
        return meetingOfTheDay.getAllResults().get(eventPosition);
    }

    public ArrayList<ResultModel> getResultsByRace(int raceId) {
        ArrayList<ResultModel> resultsForIdRace = new ArrayList<ResultModel>();
        for (int indexResult = 0; indexResult < meetingOfTheDay.getAllResults().size(); indexResult++) {
            if (meetingOfTheDay.getAllResults().get(indexResult).getEventModel().getRaceModel().getId() == raceId) {
                resultsForIdRace.add(meetingOfTheDay.getAllResults().get(indexResult));
            }
        }
        return resultsForIdRace;
    }

    public ArrayList<EventModel> getAllRaceFromEventsInMeeting() {
        return allEventsByOrder;
    }

    private void builRaceOrder() {
        allEventsByOrder = new ArrayList<EventModel>();
        ArrayList<ResultModel> allResultsInMeeting = getAllResultsOfDay();

        for (int indexResult = 0; indexResult < allResultsInMeeting.size(); indexResult++) {
            boolean thisRaceIsAlreadyAdded = false;
            int idRaceInTheResult = allResultsInMeeting.get(indexResult).getEventModel().getRaceModel().getId();
            int orderInMeeting = allResultsInMeeting.get(indexResult).getEventModel().getOrder();
            for (int indexRace = 0; indexRace < allEventsByOrder.size(); indexRace++) {
                int idRaceAlreadyInList = allEventsByOrder.get(indexRace).getRaceModel().getId();
                if (idRaceAlreadyInList == idRaceInTheResult) {
                    thisRaceIsAlreadyAdded = true;
                }
            }
            int positionToInsert = 0;
            if (!thisRaceIsAlreadyAdded) {
                for (int indexOrder = allEventsByOrder.size() - 1; indexOrder >= 0; indexOrder--) {
                    int orderInEventsList = allEventsByOrder.get(indexOrder).getOrder();
                    if (orderInMeeting < orderInEventsList) {
                        allEventsByOrder.add(indexOrder + 1, allResultsInMeeting.get(indexOrder).getEventModel());
                        positionToInsert = indexOrder;
                    }
                }
                allEventsByOrder.add(positionToInsert, allResultsInMeeting.get(indexResult).getEventModel());
                int i;
            }
        }
    }


    public ArrayList<ResultModel> getAllResultsOfDay() {
            return meetingOfTheDay.getAllResults();
    }


    public void setFirstIdRaceOfResults() {
        if (getAllResultsOfDay().size() > 0) {
            currentRaceId = getAllResultsOfDay().get(0).getEventModel().getRaceModel().getId();
        }
    }

    public int getCurrentRaceId() {
        return currentRaceId;
    }

    public void setCurrentRaceId(int currentRaceId) {
        this.currentRaceId = currentRaceId;
    }
}

