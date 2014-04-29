/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;


import android.view.View;

import com.dim.swimlap.models.RESULT;

import java.util.ArrayList;

public interface CommunicationFragments {

    public void changeFragment(int integerCodeFragment);

    public void getGlobalLap(View view);

    public void inverseButtonsInLap(boolean chronoIsStarted);

    public void resetLap(View view);

    public void recordLap(View view);

    public void saveLapList(ArrayList<RESULT> list);

    public ArrayList<RESULT> giveBackLapList();


}
