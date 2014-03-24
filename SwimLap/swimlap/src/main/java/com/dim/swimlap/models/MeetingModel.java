/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MeetingModel {

    private int idMeeting;
    private String name;
    private String city;
    private Date startDate;
    private Date stopDate;
    private int size;
    private int agegroupId;
    private int seasonId;
    private int poolSize;
    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        return convertDateToString(startDate);
    }

    public void setStartDate(String startDate) {
        this.startDate = convertStringToDate(startDate);
    }

    public String getStopDate() {
        return convertDateToString(stopDate);
    }

    public void setStopDate(String stopDate) {
        this.stopDate = convertStringToDate(stopDate);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAgegroupId() {
        return agegroupId;
    }

    public void setAgegroupId(int agegroupId) {
        this.agegroupId = agegroupId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    private String convertDateToString(Date dateToConvert) {
        SimpleDateFormat formatter = new SimpleDateFormat(FFNEX_DATE_FORMAT);
        String dateInString = formatter.format(dateToConvert);
        return dateInString;

    }

    private Date convertStringToDate(String dateInString){
        SimpleDateFormat formatter = new SimpleDateFormat(FFNEX_DATE_FORMAT);
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }
}
