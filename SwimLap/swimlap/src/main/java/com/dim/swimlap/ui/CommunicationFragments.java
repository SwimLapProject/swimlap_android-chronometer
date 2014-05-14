/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;


import android.view.View;

import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;

import java.util.ArrayList;

public interface CommunicationFragments {

    public void changeFragment(int integerCodeFragment);

    public void getGlobalLap(View view);

    public void unLapLast(View view);

    public void inverseButtonsInLap();

    public void resetLap(View view);

    public void recordLap(View view);

    public void saveLapList(ArrayList<ResultModel> list);

    public ArrayList<ResultModel> giveBackLapList();

    public void replaceFragmentDataLap(int raceId);

    public void replaceFragmentsMeetingToDetails(MeetingModel meetingToDetails);

}
