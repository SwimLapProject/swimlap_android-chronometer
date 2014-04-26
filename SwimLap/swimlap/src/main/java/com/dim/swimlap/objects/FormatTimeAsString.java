/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.objects;

public class FormatTimeAsString {

    public String makeString(float timeInMilli) {
        int minutes = Math.round(timeInMilli / 100000);
        int seconds = Math.round(timeInMilli - minutes) / 1000;
        int milliSec = (int) timeInMilli % 1000;

        return minutes+":"+ seconds + "." + milliSec;
    }
}
