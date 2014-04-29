/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import com.dim.swimlap.data.RaceData;

public class RaceModel {

    private int id;
    private int distance;
    private String style;
    private boolean is_relay;
    private String gender;

    public RaceModel(int id) {
        this.id = id;
        buidContent();
    }

    private void buidContent() {
        RaceData raceData = new RaceData();
        distance = raceData.giveDistance(id);
        style = raceData.giveStyle(id);
        gender = raceData.giveGender(id);
        is_relay = style.substring(0, 4).equals("4x25")
                || style.substring(0, 4).equals("4x50")
                || style.substring(0, 4).equals("4x10")
                || style.substring(0, 4).equals("4x20")
                || style.substring(0, 4).equals("6x50")
                || style.substring(0, 4).equals("8x10")
                || style.substring(0, 4).equals("10x1");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isIs_relay() {
        return is_relay;
    }

    public void setIs_relay(boolean is_relay) {
        this.is_relay = is_relay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
