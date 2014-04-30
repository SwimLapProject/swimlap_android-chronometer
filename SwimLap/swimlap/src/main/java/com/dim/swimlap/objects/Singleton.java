package com.dim.swimlap.objects;

import com.dim.swimlap.models.ResultModel;

import java.util.ArrayList;

public final class Singleton {

    private static volatile Singleton instance = null;
    private ArrayList<ResultModel> listOf_ResultModel;
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

    public ArrayList<ResultModel> buildEvent() {
        if (listIsAlreadyBuilt) {
            return getList();
        } else {
            return getEmptyEvents();
        }
    }

    private ArrayList<ResultModel> getEmptyEvents() {
        listOf_ResultModel = new ArrayList<ResultModel>();

//        for (int i = 0; i < 3; i++) {
//            SwimmerModel swimmerModel = new SwimmerModel();
//            swimmerModel.setName("Name" + i);
//            swimmerModel.setFirstname("FirstName" + i);
//            swimmerModel.setIdFFN(888888);
//            swimmerModel.setDateOfBirth(String.valueOf(1987 + i));
//            swimmerModel.setClubModel(new ClubModel(1));
//
//            EventModel eventModel = new EventModel();
//            eventModel.setId(i);
//            eventModel.setOrder(10 * i);
//            eventModel.setRaceModel(new RaceModel(3));
//            eventModel.setRoundModel(new RoundModel(60));
//
//
//            MeetingModel meetingModel = new MeetingModel();
//            meetingModel.setId(1);
//            meetingModel.setName("Championnats Régionaux Hiver");
//            meetingModel.setStartDate("2014-04-29 00:00:00");
//            meetingModel.setStopDate("2014-04-30 00:00:00");
//            meetingModel.setPoolSize(25);
//            meetingModel.setByTeam(false);
//            meetingModel.setSeasonModel(new SeasonModel(1));
//
//
//            ResultModel ResultModel = new ResultModel(swimmerModel, eventModel, meetingModel,  new Float(127789));
//            listOf_ResultModel.add(ResultModel);
//        }
        listIsAlreadyBuilt = true;
        return listOf_ResultModel;
    }

    private ArrayList<ResultModel> getList() {
        return listOf_ResultModel;
    }


    public ResultModel getEventData(int eventPosition) {
        return listOf_ResultModel.get(eventPosition);
    }


}

