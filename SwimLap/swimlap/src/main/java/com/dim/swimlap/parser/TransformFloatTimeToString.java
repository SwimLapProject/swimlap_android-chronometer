/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.parser;

public class TransformFloatTimeToString {

    public String getFFNexFormatTime(float lap) {
        int milli = (int) lap % 1000;
        int seconds = (int) (lap - milli) / 1000;
        int minutes = 0;
        if (seconds > 60) {
            minutes = (int) Math.floor(seconds / 60);
            seconds = seconds - (minutes * 60);
        }
        milli = (int) Math.floor(milli / 10);
        String timeAsString = String.valueOf(minutes) + "." + String.valueOf(seconds) + String.valueOf(milli);
        return timeAsString;
    }
}
