/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

public class EventModel {

    private int id;
    private int order; //order of events during meeting

    private RaceModel raceModel;
    private RoundModel roundModel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RaceModel getRaceModel() {
        return raceModel;
    }

    public void setRaceModel(RaceModel raceModel) {
        this.raceModel = raceModel;
    }

    public RoundModel getRoundModel() {
        return roundModel;
    }

    public void setRoundModel(RoundModel roundModel) {
        this.roundModel = roundModel;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
