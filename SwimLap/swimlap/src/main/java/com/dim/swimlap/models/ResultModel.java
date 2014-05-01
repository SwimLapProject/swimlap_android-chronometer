/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import android.content.Context;

import com.dim.swimlap.db.recorder.RecordLapsInDb;
import com.dim.swimlap.objects.FormatTimeAsString;

import java.util.ArrayList;

public class ResultModel {

    // CHARACTERISTICS
    private int id;
    private SwimmerModel swimmerModel;
    private ArrayList<SwimmerModel> team;
    private EventModel eventModel;

    private int meetingId;
    // DATA
    private float qualifyingTime;
    private float swimTime;
    private ArrayList<Float> laps;

    // UTILITIES
    private int currentLapSwimming;
    private int numberOfLap;
    private float lapMin, lapMax;
    private int poolSize;
    private boolean isRelay;

    public ResultModel(int id) {
        this.id = id;
        // with this constructor elements must be added: swimmer or team, eventmodel , poolsize , qualifyingtime
    }

    public void addSwimmersInTeam(SwimmerModel swimmerModel) {
        if (team == null) {
            team = new ArrayList<SwimmerModel>();
            isRelay = true;
        }
        team.add(swimmerModel);
    }

    public void buildContent(float qualifyingTime, int poolSize, int meetingId) {
        this.meetingId = meetingId;
        this.poolSize = poolSize;
        // qualifying time must be in milliseconds
        this.qualifyingTime = qualifyingTime;
        numberOfLap = eventModel.getRaceModel().getDistance() / poolSize;
        currentLapSwimming = 0;
        lapMin = (qualifyingTime * 10000 / numberOfLap) * 75 / 100;
        lapMax = (qualifyingTime * 10000 / numberOfLap) * 125 / 100;
    }

    public boolean trueIfSomeLapsAreAlreadyTaken() {
        boolean lapsAllreadytaken = false;
        if (laps.size() != 0) {
            lapsAllreadytaken = true;
        }
        return lapsAllreadytaken;
    }

    public ArrayList<String> giveBackLapsToInsertInTextViewAllLaps() {
        FormatTimeAsString format = new FormatTimeAsString();
        ArrayList<String> allLapsAlreadyTaken = new ArrayList<String>();

        for (int lapIndex = 0; lapIndex < currentLapSwimming; lapIndex++) {
            String lapStringToAdd = String.valueOf(poolSize * lapIndex) + ": ";
            lapStringToAdd += format.makeString(giveSplit(laps.get(lapIndex), lapIndex));
            lapStringToAdd += format.makeString(laps.get(lapIndex));
            allLapsAlreadyTaken.add(lapStringToAdd);
        }
        return allLapsAlreadyTaken;
    }

    public String giveBackLastLap() {
        FormatTimeAsString format = new FormatTimeAsString();
        return format.makeString(laps.get(laps.size() - 1));
    }

    private float giveSplit(float lap, int currentPosition) {
        float split;
        if (currentPosition == 0) {
            split = lap;
        } else {
            split = lap - laps.get(currentPosition - 1);
        }
        return split;
    }

    public float checkLap(float lap) {
        float split = giveSplit(lap, currentLapSwimming);
        // VERIFY IF THE TIME BETWEEN LAST LAP AND THE NEW ONE IS COHERENT AND IF ALL THE LAPS ARE NOT FILLED (RACE FINISH)
//        (split > lapMin) && (split < lapMax) &&
        if ((currentLapSwimming < numberOfLap)) {
            laps.add(currentLapSwimming, lap);
            currentLapSwimming++;
        } else {
            split = 0;
        }

        return split;
    }

    public int getnbSplitRemaining() {
        return numberOfLap - currentLapSwimming;
    }

    public String getSplitName() {
        String lapName = null;
        if (currentLapSwimming <= numberOfLap) {
            lapName = String.valueOf(poolSize * currentLapSwimming);
        }
        return lapName;
    }

    public void resetLaps() {
        this.currentLapSwimming = 0;
        this.laps.clear();
    }

    public boolean isRelay() {
        return isRelay;
    }

    public void setIsRelay(boolean isRelay) {
        this.isRelay = isRelay;
    }

    public void recordLapsInDB(Context context) {
        RecordLapsInDb recorder = new RecordLapsInDb(context);
        recorder.recordLaps(this);
    }

    // GETTERS AND SETTERS
    public float getQualifyingTime() {
        return qualifyingTime;
    }

    public void setQualifyingTime(float qualifyingTime) {
        this.qualifyingTime = qualifyingTime;
    }

    public ArrayList<Float> getLaps() {
        return laps;
    }

    public void setLaps(ArrayList<Float> laps) {
        this.laps = laps;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public SwimmerModel getSwimmerModel() {
        return swimmerModel;
    }

    public void setSwimmerModel(SwimmerModel swimmerModel) {
        this.swimmerModel = swimmerModel;
        isRelay = false;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public float getSwimTime() {
        return swimTime;
    }

    public void setSwimTime(float swimTime) {
        this.swimTime = swimTime;
    }

    public int getCurrentLapSwimming() {
        return currentLapSwimming;
    }

    public void setCurrentLapSwimming(int currentLapSwimming) {
        this.currentLapSwimming = currentLapSwimming;
    }

    public int getNumberOfLap() {
        return numberOfLap;
    }

    public void setNumberOfLap(int numberOfLap) {
        this.numberOfLap = numberOfLap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<SwimmerModel> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<SwimmerModel> team) {
        this.team = team;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

}
