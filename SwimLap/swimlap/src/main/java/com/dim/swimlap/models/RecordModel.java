/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

public class RecordModel {
    private int swimmerId;
    private int raceId;
    private float swimtime25;
    private float swimtime50;

    public int getSwimmerId() {
        return swimmerId;
    }

    public void setSwimmerId(int swimmerId) {
        this.swimmerId = swimmerId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public float getSwimtime25() {
        return swimtime25;
    }

    public void setSwimtime25(float swimtime25) {
        this.swimtime25 = swimtime25;
    }

    public float getSwimtime50() {
        return swimtime50;
    }

    public void setSwimtime50(float swimtime50) {
        this.swimtime50 = swimtime50;
    }
}
