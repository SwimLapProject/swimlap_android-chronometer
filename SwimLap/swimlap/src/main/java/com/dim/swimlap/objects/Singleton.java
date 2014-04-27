package com.dim.swimlap.objects;

import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.SwimmerModel;
import com.dim.swimlap.ui.lap.EventData;

import java.util.ArrayList;

public final class Singleton {

    private static volatile Singleton instance = null;
    private ArrayList<EventData> listOfEventData;
    private boolean listIsAlreadyBuilt;

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

    public ArrayList<EventData> buildEvent() {
        if (listIsAlreadyBuilt) {
            return getList();
        } else {
            return getEmptyEvents();
        }
    }

    private ArrayList<EventData> getEmptyEvents() {

        listOfEventData = new ArrayList<EventData>();
        for (int i = 0; i < 3; i++) {
            SwimmerModel swimmerModel = new SwimmerModel();
            swimmerModel.setName("Name" + i);
            swimmerModel.setFirstname("FirstName" + i);
            swimmerModel.setIdSwimmer(888888);
            swimmerModel.setDateOfBirth(String.valueOf(1987 + i));
            EventModel eventModel = new EventModel();
            eventModel.setQualifyingTime(new Float(60789));
            eventModel.setRaceId(4);
            EventData eventData = new EventData(swimmerModel, eventModel, 25);
            listOfEventData.add(eventData);
        }
        listIsAlreadyBuilt = true;
        return listOfEventData;
    }

    private ArrayList<EventData> getList() {
        return listOfEventData;
    }

    public void addLapInList(int eventPosition, int lapPosition, float lapToAdd) {
        listOfEventData.get(eventPosition).getEventModel().getLaps().add(lapPosition, lapToAdd);
    }

    public EventData getEventData(int eventPosition){
        return listOfEventData.get(eventPosition);
    }


}

