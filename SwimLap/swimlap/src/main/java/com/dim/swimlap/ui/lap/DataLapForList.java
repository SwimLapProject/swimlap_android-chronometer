/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.lap;

import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;

public class DataLapForList {

    private SwimmerModel swimmerModel;
    private EventModel eventModel;
    private RaceModel raceModel;
    private ArrayList<Float> laps;
    private int currentLapSwimming;
    private int numberOfLap;
    private float lapPreviousMin, lapPreviousMax;
    private int poolSize;

    public DataLapForList(SwimmerModel swimmerModel, EventModel eventModel, int poolSize) {
        this.swimmerModel = swimmerModel;
        this.eventModel = eventModel;
        this.raceModel = new RaceModel(eventModel.getRaceId());
        this.poolSize = poolSize;
        numberOfLap = raceModel.getDistance() / poolSize;
        currentLapSwimming = 0;
        lapPreviousMin = (eventModel.getQualifyingTime() * 10000 / numberOfLap) * 75 / 100;
        lapPreviousMax = (eventModel.getQualifyingTime() * 10000 / numberOfLap) * 125 / 100;

        eventModel.buildLapTable(numberOfLap);
    }

    public SwimmerModel getSwimmerModel() {
        return swimmerModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public float checkLap(float lap) {
        float split;
        if (currentLapSwimming == 0) {
            split = lap;
        } else {
            split = lap - eventModel.getLaps().get(currentLapSwimming - 1);
        }

        // VERIFY IF THE TIME BETWEEN LAST LAP AND THE NEW ONE IS COHERENT AND IF ALL THE LAPS ARE NOT FILLED (RACE FINISH)
//        (split > lapPreviousMin) && (split < lapPreviousMax) &&
        if ((currentLapSwimming < numberOfLap)) {
            eventModel.addValueToLaps(currentLapSwimming, lap);
            currentLapSwimming++;
        } else {
            split = 0;
        }

        return split;
    }

    public int getnbSplitRemaining(){
        return numberOfLap-currentLapSwimming;
    }

    public String getSplitName() {
        String lapName = null;
        if (currentLapSwimming <= numberOfLap) {
            lapName = String.valueOf(poolSize * currentLapSwimming);
        }
        return lapName;
    }


    public void recordLapsInDB() {
    }


}
