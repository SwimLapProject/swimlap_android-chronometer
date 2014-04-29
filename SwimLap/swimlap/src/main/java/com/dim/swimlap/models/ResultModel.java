/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import com.dim.swimlap.objects.FormatTimeAsString;

import java.util.ArrayList;

public class ResultModel {

    // CHARACTERISTICS
    private int id;
    private SwimmerModel swimmerModel;
    private EventModel eventModel;
    private float qualifyingTime;

    // DATA
    private float swimTime;
    private ArrayList<Float> laps;

    // UTILITIES
    private int currentLapSwimming;
    private int numberOfLap;
    private float lapPreviousMin, lapPreviousMax;
    private int poolSize;

    public ResultModel(SwimmerModel swimmerModel, EventModel eventModel, int poolSize, float qualifyingTime, int id) {
        this.id = id;
        this.swimmerModel = swimmerModel;
        this.eventModel = eventModel;
        this.qualifyingTime = qualifyingTime;
        this.poolSize = poolSize;
        laps = new ArrayList<Float>();

        buildContent();
    }

    public ResultModel(int id) {
    this.id = id;

    }



    public void buildContent() {
        numberOfLap = eventModel.getRaceModel().getDistance() / poolSize;
        currentLapSwimming = 0;
        lapPreviousMin = (qualifyingTime * 10000 / numberOfLap) * 75 / 100;
        lapPreviousMax = (qualifyingTime * 10000 / numberOfLap) * 125 / 100;
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
//        (split > lapPreviousMin) && (split < lapPreviousMax) &&
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

    public void recordLapsInDB() {
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

    public float getLapPreviousMin() {
        return lapPreviousMin;
    }

    public void setLapPreviousMin(float lapPreviousMin) {
        this.lapPreviousMin = lapPreviousMin;
    }

    public float getLapPreviousMax() {
        return lapPreviousMax;
    }

    public void setLapPreviousMax(float lapPreviousMax) {
        this.lapPreviousMax = lapPreviousMax;
    }

}
