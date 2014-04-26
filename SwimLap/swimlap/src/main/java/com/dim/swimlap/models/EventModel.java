/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import java.util.ArrayList;

public class EventModel {
    private int eventId;
    private int swimmerIdFFN;
    private int meetingId;
    private int raceId;

    // MEETING SPECIFICATIONS
    private int idRound;
    private int order; //order of events during meeting

    // SWIMMER SPECIFICATIONS
    private float qualifyingTime;
    private float swimTime;
    private int agegroupId;

    // RACE SPECIFICATION
    private ArrayList<Float> laps;
//    private float split25;
//    private float split50;
//    private float split75;
//    private float split100;
//    private float split125;
//    private float split150;
//    private float split175;
//    private float split200;
//    private float split225;
//    private float split250;
//    private float split275;
//    private float split300;
//    private float split325;
//    private float split350;
//    private float split375;
//    private float split400;
//    private float split425;
//    private float split450;
//    private float split475;
//    private float split500;
//    private float split525;
//    private float split550;
//    private float split575;
//    private float split600;
//    private float split625;
//    private float split650;
//    private float split675;
//    private float split700;
//    private float split725;
//    private float split750;
//    private float split775;
//    private float split800;
//    private float split825;
//    private float split850;
//    private float split875;
//    private float split900;
//    private float split925;
//    private float split950;
//    private float split975;
//    private float split1000;
//    private float split1025;
//    private float split1050;
//    private float split1075;
//    private float split1100;
//    private float split1125;
//    private float split1150;
//    private float split1175;
//    private float split1200;
//    private float split1225;
//    private float split1250;
//    private float split1275;
//    private float split1300;
//    private float split1325;
//    private float split1350;
//    private float split1375;
//    private float split1400;
//    private float split1425;
//    private float split1450;
//    private float split1475;
//    private float split1500;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSwimmerIdFFN() {
        return swimmerIdFFN;
    }

    public void setSwimmerIdFFN(int swimmerIdFFN) {
        this.swimmerIdFFN = swimmerIdFFN;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getIdRound() {
        return idRound;
    }

    public void setIdRound(int idRound) {
        this.idRound = idRound;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public float getQualifyingTime() {
        return qualifyingTime;
    }

    public void setQualifyingTime(float qualifyingTime) {
        // TIME MUST ALWAYS BE IN milliseconds
        this.qualifyingTime = qualifyingTime;
    }

    public float getSwimTime() {
        return swimTime;
    }

    public void setSwimTime(float swimTime) {
        this.swimTime = swimTime;
    }

    public int getAgegroupId() {
        return agegroupId;
    }

    public void setAgegroupId(int agegroupId) {
        this.agegroupId = agegroupId;
    }

    public void buildLapTable(int numberOfLaps) {
        laps = new ArrayList<Float>(numberOfLaps);
    }

    public void addValueToLaps(int indexOfLap, float lap) {
        laps.add(indexOfLap, lap);
    }

    public ArrayList<Float> getLaps() {
        return laps;
    }

}
