/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MeetingModel {

    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";
    private int id;
    private String name;
    private String city;
    private Date startDate;
    private Date stopDate;
    private int poolSize;
    private boolean byTeam;
    private int clubId, clubCode;

    private SeasonModel seasonModel;
    private ArrayList<ResultModel> allResults;

    public MeetingModel() {
        allResults = new ArrayList<ResultModel>();
    }


    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getClubCode() {
        return clubCode;
    }

    public void setClubCode(int clubCode) {
        this.clubCode = clubCode;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public SeasonModel getSeasonModel() {
        return seasonModel;
    }

    public boolean setSeasonModel(SeasonModel seasonModel) {
        boolean seasonIsAdded = false;
        if (seasonModel != null) {
            this.seasonModel = seasonModel;
            seasonIsAdded = true;
        }
        return seasonIsAdded;
    }


    private String convertDateToString(Date dateToConvert) {
        SimpleDateFormat formatter = new SimpleDateFormat(FFNEX_DATE_FORMAT);
        String dateInString = formatter.format(dateToConvert);
        return dateInString;

    }

    public Date convertStringToDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat(FFNEX_DATE_FORMAT);
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    public boolean isByTeam() {
        return byTeam;
    }

    public void setByTeam(boolean byTeam) {
        this.byTeam = byTeam;
    }

    public void addResult(ResultModel resultModel) {
        resultModel.setPoolSize(poolSize);
        allResults.add(resultModel);
    }

    public ResultModel getResult(int swimmerId, int eventId, int roundId) {
        ResultModel resultToReturn = null;
        for (int indexResult = 0; indexResult < allResults.size(); indexResult++) {
            int idSwimmer = allResults.get(indexResult).getSwimmerModel().getIdFFN();
            int idEvent = allResults.get(indexResult).getEventModel().getId();
            int idRound = allResults.get(indexResult).getEventModel().getRoundModel().getId();
            if (idSwimmer == swimmerId && idEvent == eventId && idRound == roundId) {
                resultToReturn = allResults.get(indexResult);
            }
        }
        return resultToReturn;
    }

    public ArrayList<ResultModel> getAllResults() {
        return allResults;
    }
}
