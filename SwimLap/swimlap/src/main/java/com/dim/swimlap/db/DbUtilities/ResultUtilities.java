/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.DbUtilities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dim.swimlap.db.tables.DbTableResults;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;
import java.util.List;

public class ResultUtilities {
    private SQLiteDatabase sqLiteDatabaseSwimLap;
    private DbTableResults table;

    public ResultUtilities(SQLiteDatabase sqLiteDatabaseSwimLap, DbTableResults dbTableResults) {
        this.sqLiteDatabaseSwimLap = sqLiteDatabaseSwimLap;
        this.table = dbTableResults;
    }


    /* GETTERS */
    public List<ResultModel> getAllResultsFromDb_ByMeeting(int idMeeting) {
        List<ResultModel> allResults = new ArrayList<ResultModel>();
        String condition = table.COL_MEE_ID_MEET + "=" + idMeeting;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ResultModel resultModel = getDataResult_FromDb(cursor);
            allResults.add(resultModel);
            cursor.moveToNext();
        }
        cursor.close();
        return allResults;
    }


    public List<ResultModel> getAllResultsBySwimmer_FromDb(int idFFNSwimmer) {
        List<ResultModel> allResults = new ArrayList<ResultModel>();
        String condition = table.COL_SWI_0_ID_FFN + "=" + idFFNSwimmer;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ResultModel resultModel = getDataResult_FromDb(cursor);
            allResults.add(resultModel);
            cursor.moveToNext();
        }
        cursor.close();
        return allResults;
    }

    public List<ResultModel> getAllResultsByRace_FromDb(int idRace) {
        List<ResultModel> allResults = new ArrayList<ResultModel>();
        String condition = table.COL_RES_ID_RACE + "=" + idRace;
        Cursor cursor = sqLiteDatabaseSwimLap.query(table.TABLE_NAME, table.ALL_COLUMNS_AS_STRING_TAB, condition, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ResultModel resultModel = getDataResult_FromDb(cursor);
            allResults.add(resultModel);
            cursor.moveToNext();
        }
        cursor.close();
        return allResults;
    }

    /* GET CONTENT */
    private ResultModel getDataResult_FromDb(Cursor cursor) {
        ResultModel resultModel = new ResultModel(cursor.getInt(0)); // resultId
        // if swimmer n°2 doesn't exist ==0 then get 1 swimmer else try to find 10 swimmers max
        if (cursor.getInt(2) == 0) {
            // result is not a relay
            resultModel.setSwimmerModel(new SwimmerModel(cursor.getInt(1))); // swimmerId0
        } else {
            // result is a relay loop from 1 to 10 to get all swimmers
            for (int i = 1; i < 11; i++) {
                resultModel.addSwimmersInTeam(new SwimmerModel(cursor.getInt(i)));
            }
        }
        //cursor.getInt(11) is meetingId
        EventModel eventModel = new EventModel(cursor.getInt(12)); //enventId
        eventModel.setRaceModel(new RaceModel(cursor.getInt(13)));  //raceId
        resultModel.setEventModel(eventModel); // ADD eventModel
        resultModel.setQualifyingTime(cursor.getFloat(14)); //qualifyinfTime
        resultModel.setSwimTime(cursor.getFloat(15));// swimTime
        // try to get the 60 laps max
        for (int i = 0; i < 60; i++) {
            resultModel.getLaps().add(i, cursor.getFloat(i + 7));
        }
        return resultModel;
    }

    /* ADDER */
    public void addResult_InDb(ResultModel resultModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(table.COL_RES_ID, resultModel.getId());
        if (! resultModel.isRelay()) {
            contentValues.put(table.COL_SWI_0_ID_FFN, resultModel.getSwimmerModel().getIdFFN());
        } else {
            contentValues.put(table.COL_SWI_0_ID_FFN, resultModel.getTeam().get(0).getIdFFN());
            contentValues.put(table.COL_SWI_1_ID_FFN, resultModel.getTeam().get(1).getIdFFN());
            contentValues.put(table.COL_SWI_2_ID_FFN, resultModel.getTeam().get(2).getIdFFN());
            contentValues.put(table.COL_SWI_3_ID_FFN, resultModel.getTeam().get(3).getIdFFN());
            contentValues.put(table.COL_SWI_4_ID_FFN, resultModel.getTeam().get(4).getIdFFN());
            contentValues.put(table.COL_SWI_5_ID_FFN, resultModel.getTeam().get(5).getIdFFN());
            contentValues.put(table.COL_SWI_6_ID_FFN, resultModel.getTeam().get(6).getIdFFN());
            contentValues.put(table.COL_SWI_7_ID_FFN, resultModel.getTeam().get(7).getIdFFN());
            contentValues.put(table.COL_SWI_8_ID_FFN, resultModel.getTeam().get(8).getIdFFN());
            contentValues.put(table.COL_SWI_9_ID_FFN, resultModel.getTeam().get(9).getIdFFN());
        }

        contentValues.put(table.COL_MEE_ID_MEET, resultModel.getMeetingId());
        contentValues.put(table.COL_EVE_ID_EVENT, resultModel.getEventModel().getId());
        contentValues.put(table.COL_RES_ID_RACE, resultModel.getEventModel().getRaceModel().getId());
        contentValues.put(table.COL_EVE_QUALIFYING_TIME, resultModel.getQualifyingTime());
        contentValues.put(table.COL_EVE_SWIM_TIME, resultModel.getSwimTime());

        contentValues.put(table.COL_EVE_SPLIT_25, resultModel.getLaps().get(0));
        contentValues.put(table.COL_EVE_SPLIT_50, resultModel.getLaps().get(1));
        contentValues.put(table.COL_EVE_SPLIT_75, resultModel.getLaps().get(2));
        contentValues.put(table.COL_EVE_SPLIT_100, resultModel.getLaps().get(3));
        contentValues.put(table.COL_EVE_SPLIT_125, resultModel.getLaps().get(4));
        contentValues.put(table.COL_EVE_SPLIT_150, resultModel.getLaps().get(5));
        contentValues.put(table.COL_EVE_SPLIT_175, resultModel.getLaps().get(6));
        contentValues.put(table.COL_EVE_SPLIT_200, resultModel.getLaps().get(7));
        contentValues.put(table.COL_EVE_SPLIT_225, resultModel.getLaps().get(8));
        contentValues.put(table.COL_EVE_SPLIT_250, resultModel.getLaps().get(9));

        contentValues.put(table.COL_EVE_SPLIT_275, resultModel.getLaps().get(10));
        contentValues.put(table.COL_EVE_SPLIT_300, resultModel.getLaps().get(11));
        contentValues.put(table.COL_EVE_SPLIT_325, resultModel.getLaps().get(12));
        contentValues.put(table.COL_EVE_SPLIT_350, resultModel.getLaps().get(13));
        contentValues.put(table.COL_EVE_SPLIT_375, resultModel.getLaps().get(14));
        contentValues.put(table.COL_EVE_SPLIT_400, resultModel.getLaps().get(15));
        contentValues.put(table.COL_EVE_SPLIT_425, resultModel.getLaps().get(16));
        contentValues.put(table.COL_EVE_SPLIT_450, resultModel.getLaps().get(17));
        contentValues.put(table.COL_EVE_SPLIT_475, resultModel.getLaps().get(18));
        contentValues.put(table.COL_EVE_SPLIT_500, resultModel.getLaps().get(19));
        contentValues.put(table.COL_EVE_SPLIT_525, resultModel.getLaps().get(20));
        contentValues.put(table.COL_EVE_SPLIT_550, resultModel.getLaps().get(21));
        contentValues.put(table.COL_EVE_SPLIT_575, resultModel.getLaps().get(22));
        contentValues.put(table.COL_EVE_SPLIT_600, resultModel.getLaps().get(23));
        contentValues.put(table.COL_EVE_SPLIT_625, resultModel.getLaps().get(24));
        contentValues.put(table.COL_EVE_SPLIT_650, resultModel.getLaps().get(25));
        contentValues.put(table.COL_EVE_SPLIT_675, resultModel.getLaps().get(26));
        contentValues.put(table.COL_EVE_SPLIT_700, resultModel.getLaps().get(27));
        contentValues.put(table.COL_EVE_SPLIT_725, resultModel.getLaps().get(28));
        contentValues.put(table.COL_EVE_SPLIT_750, resultModel.getLaps().get(29));
        contentValues.put(table.COL_EVE_SPLIT_775, resultModel.getLaps().get(30));
        contentValues.put(table.COL_EVE_SPLIT_800, resultModel.getLaps().get(31));
        contentValues.put(table.COL_EVE_SPLIT_825, resultModel.getLaps().get(32));
        contentValues.put(table.COL_EVE_SPLIT_850, resultModel.getLaps().get(33));
        contentValues.put(table.COL_EVE_SPLIT_875, resultModel.getLaps().get(34));
        contentValues.put(table.COL_EVE_SPLIT_900, resultModel.getLaps().get(35));
        contentValues.put(table.COL_EVE_SPLIT_925, resultModel.getLaps().get(36));
        contentValues.put(table.COL_EVE_SPLIT_950, resultModel.getLaps().get(37));
        contentValues.put(table.COL_EVE_SPLIT_975, resultModel.getLaps().get(38));
        contentValues.put(table.COL_EVE_SPLIT_1000, resultModel.getLaps().get(39));
        contentValues.put(table.COL_EVE_SPLIT_1025, resultModel.getLaps().get(40));
        contentValues.put(table.COL_EVE_SPLIT_1050, resultModel.getLaps().get(41));
        contentValues.put(table.COL_EVE_SPLIT_1075, resultModel.getLaps().get(42));
        contentValues.put(table.COL_EVE_SPLIT_1100, resultModel.getLaps().get(43));
        contentValues.put(table.COL_EVE_SPLIT_1125, resultModel.getLaps().get(44));
        contentValues.put(table.COL_EVE_SPLIT_1150, resultModel.getLaps().get(45));
        contentValues.put(table.COL_EVE_SPLIT_1175, resultModel.getLaps().get(46));
        contentValues.put(table.COL_EVE_SPLIT_1200, resultModel.getLaps().get(47));
        contentValues.put(table.COL_EVE_SPLIT_1225, resultModel.getLaps().get(48));
        contentValues.put(table.COL_EVE_SPLIT_1250, resultModel.getLaps().get(49));
        contentValues.put(table.COL_EVE_SPLIT_1275, resultModel.getLaps().get(50));
        contentValues.put(table.COL_EVE_SPLIT_1300, resultModel.getLaps().get(51));
        contentValues.put(table.COL_EVE_SPLIT_1325, resultModel.getLaps().get(52));
        contentValues.put(table.COL_EVE_SPLIT_1350, resultModel.getLaps().get(53));
        contentValues.put(table.COL_EVE_SPLIT_1375, resultModel.getLaps().get(54));
        contentValues.put(table.COL_EVE_SPLIT_1400, resultModel.getLaps().get(55));
        contentValues.put(table.COL_EVE_SPLIT_1425, resultModel.getLaps().get(56));
        contentValues.put(table.COL_EVE_SPLIT_1450, resultModel.getLaps().get(57));
        contentValues.put(table.COL_EVE_SPLIT_1475, resultModel.getLaps().get(58));
        contentValues.put(table.COL_EVE_SPLIT_1500, resultModel.getLaps().get(59));

        sqLiteDatabaseSwimLap.insert(table.TABLE_NAME, null, contentValues);
    }

    /* DELETER */
    public void deleteResultWithId_InDb(int idResult) {
        String condition = table.COL_RES_ID + " = " + idResult;
        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, condition, null);
    }

    public void deleteResultDependOnAttributes_InDb(int idSwimmer0, int idMeeting, int idEvent, int idRace) {
        String condition = table.COL_SWI_0_ID_FFN + " = " + idSwimmer0
                + " AND " + table.COL_MEE_ID_MEET + " = " + idMeeting
                + " AND " + table.COL_EVE_ID_EVENT + " = " + idEvent
                + " AND " + table.COL_RES_ID_RACE + " = " + idRace;
        sqLiteDatabaseSwimLap.delete(table.TABLE_NAME, condition, null);
    }

    /* UPDATER */
    public void updateResult_InDb(ResultModel resultModel) {
        deleteResultWithId_InDb(resultModel.getId());
        addResult_InDb(resultModel);
    }

    /* VERIFY ENTRY */
    //todo
}
