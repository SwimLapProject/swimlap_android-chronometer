/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Races {
    private int distance;
    private String style;
    private Integer numberofLap;
    private List<Map<String,Long>> lapNamesBy25, lapNamesBy50;

    public Races(int distance,String style){
        this.distance = distance;
        this.style=style;
    }

    public void build50MetersPoolRace(){
        numberofLap =(Integer) distance /50;
        lapNamesBy50 = new ArrayList<Map<String,Long>>(numberofLap);
    }
    public void build25MetersPoolRace(){
        numberofLap =(Integer) distance /25;
        lapNamesBy25 = new ArrayList<Map<String,Long>>(numberofLap);
    }



}
