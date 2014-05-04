/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.objects;

import com.dim.swimlap.models.SeasonModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateTransformer {

    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";
    private SimpleDateFormat formater;

    public DateTransformer() {
        formater = new SimpleDateFormat(FFNEX_DATE_FORMAT);
    }

    public Date stringToDate(String dateAsString) {
        Date date = null;
        try {
            date = formater.parse(dateAsString);
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return date;
    }

    public String dateToString(Date date) {
        return formater.format(date);
    }

    public HashMap<String, Integer> getDateAsMap(String dateAsStringWithJanuaryEqualsOne) {
        String date = dateAsStringWithJanuaryEqualsOne;
        HashMap<String, Integer> dateTab = new HashMap<String, Integer>();
        if (date.length() == 10) {
            dateTab.put("year", Integer.valueOf(date.substring(0, 4)));
            // JANUARY is ZERO in java but not in ffnex
            int monthAdjustedForJava = Integer.valueOf(date.substring(5, 7)) - 1;
            dateTab.put("month", monthAdjustedForJava);
            dateTab.put("day", Integer.valueOf(date.substring(8, 9)));
        } else {
            dateTab.put("year", 0);
            dateTab.put("month", 0);
            dateTab.put("day", 0);
        }
        return dateTab;
    }

    public String getDateAsStringWithFFNexFormat(int year, int month, int dayOfMonth) {
        String dateAsTring = String.valueOf(year) + "-";
        if (month < 10) {
            dateAsTring += "0" + String.valueOf(month);
            ;
        } else {
            dateAsTring += String.valueOf(month);
        }
        dateAsTring += "-";
        if (dayOfMonth < 10) {
            dateAsTring += "0" + String.valueOf(dayOfMonth);
            ;
        } else {
            dateAsTring += String.valueOf(dayOfMonth);
        }
        return dateAsTring;
    }

    public String getTodayAsString(){
      return   formater.format(new Date());
    }

}
