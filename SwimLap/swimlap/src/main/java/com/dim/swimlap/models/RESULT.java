/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import com.dim.swimlap.objects.FormatTimeAsString;

import java.util.ArrayList;

public class RESULT {

    // CHARACTERISTICS
    private int id;
    public SwimmerModel swimmerModel;
    public EventModel eventModel;
    public MeetingModel meetingModel;
    private float qualifyingTime;

    // DATA
    private float swimTime;
    private ArrayList<Float> laps;

    // UTILITIES
    private int currentLapSwimming;
    private int numberOfLap;
    private float lapPreviousMin, lapPreviousMax;

    public RESULT(SwimmerModel swimmerModel, EventModel eventModel, MeetingModel meetingModel,float qualifyingTime) {
        this.swimmerModel = swimmerModel;
        this.eventModel = eventModel;
        this.meetingModel = meetingModel;
        this.qualifyingTime =qualifyingTime;
        laps = new ArrayList<Float>();

        buildContent();
   }

    private void buildContent() {
        numberOfLap = eventModel.getRaceModel().getDistance() / meetingModel.getPoolSize();
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
            String lapStringToAdd = String.valueOf(meetingModel.getPoolSize() * lapIndex) + ": ";
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
            lapName = String.valueOf(meetingModel.getPoolSize() * currentLapSwimming);
        }
        return lapName;
    }

    public void resetLaps() {
        this.currentLapSwimming = 0;
        this.laps.clear();
    }

    public void recordLapsInDB() {
    }


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

}
