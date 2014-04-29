/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.RESULT;
import com.dim.swimlap.ui.lap.FragmentDataLap;
import com.dim.swimlap.ui.lap.FragmentNavLap;
import com.dim.swimlap.ui.lap.FragmentTitleLap;
import com.dim.swimlap.ui.meeting.FragmentDataMeeting;
import com.dim.swimlap.ui.meeting.FragmentNavMeeting;
import com.dim.swimlap.ui.meeting.FragmentTitleMeeting;
import com.dim.swimlap.ui.menu.FragmentDataMenu;
import com.dim.swimlap.ui.menu.FragmentNavMenu;
import com.dim.swimlap.ui.menu.FragmentTitleMenu;
import com.dim.swimlap.ui.settings.FragmentDataSettings;
import com.dim.swimlap.ui.settings.FragmentNavSettings;
import com.dim.swimlap.ui.settings.FragmentTitleSettings;
import com.dim.swimlap.ui.simple.FragmentDataSimple;
import com.dim.swimlap.ui.simple.FragmentNavSimple;
import com.dim.swimlap.ui.simple.FragmentTitleSimple;
import com.dim.swimlap.ui.swimmer.FragmentDataSwimmer;
import com.dim.swimlap.ui.swimmer.FragmentNavSwimmer;
import com.dim.swimlap.ui.swimmer.FragmentTitleSwimmer;

import java.util.ArrayList;

public class GlobalContainer extends FragmentActivity implements CommunicationFragments {

    private FragmentDirect fragmentDirect;

    private FragmentTitleMenu fragmentTitleMenu;
    private FragmentNavMenu fragmentNavMenu;
    private FragmentDataMenu fragmentDataMenu;

    private FragmentTitleLap fragmentTitleLap;
    private FragmentNavLap fragmentNavLap;
    private FragmentDataLap fragmentDataLap;
    private ArrayList<RESULT> savedLapList;

    private FragmentTitleSimple fragmentTitleSimple;
    private FragmentNavSimple fragmentNavSimple;
    private FragmentDataSimple fragmentDataSimple;

    private FragmentTitleMeeting fragmentTitleMeeting;
    private FragmentNavMeeting fragmentNavMeeting;
    private FragmentDataMeeting fragmentDataMeeting;

    private FragmentTitleSwimmer fragmentTitleSwimmer;
    private FragmentNavSwimmer fragmentNavSwimmer;
    private FragmentDataSwimmer fragmentDataSwimmer;

    private FragmentTitleSettings fragmentTitleSettings;
    private FragmentNavSettings fragmentNavSettings;
    private FragmentDataSettings fragmentDataSettings;

    private int currentView, lastView;
    private static int
            VIEW_MENU = 0,
            VIEW_LAP = 1,
            VIEW_SIMPLE = 2,
            VIEW_MEETING = 3,
            VIEW_SWIMMER = 4,
            VIEW_SETTING = 5,
            VIEW_RANKING_MEET = 6,
            VIEW_RANKING_SW = 7;

    public GlobalContainer() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_container);

        // FRAGMENT FOR THE VIEW MENU
        fragmentDirect = new FragmentDirect();
        fragmentTitleMenu = new FragmentTitleMenu();
        fragmentNavMenu = new FragmentNavMenu();
        fragmentDataMenu = new FragmentDataMenu();

        // FRAGMENT FOR THE VIEW LAP
        fragmentTitleLap = new FragmentTitleLap();
        fragmentNavLap = new FragmentNavLap();
        fragmentDataLap = new FragmentDataLap();

        // FRAGMENT FOR VIEW SIMPLE CHRONOMETER
        fragmentTitleSimple = new FragmentTitleSimple();
        fragmentNavSimple = new FragmentNavSimple();
        fragmentDataSimple = new FragmentDataSimple();

        // FRAGMENT FOR THE VIEW MEETING
        fragmentTitleMeeting = new FragmentTitleMeeting();
        fragmentNavMeeting = new FragmentNavMeeting();
        fragmentDataMeeting = new FragmentDataMeeting();

        //FRAGMENT FOR VIEW SWIMMER
        fragmentTitleSwimmer = new FragmentTitleSwimmer();
        fragmentNavSwimmer = new FragmentNavSwimmer();
        fragmentDataSwimmer = new FragmentDataSwimmer();

        // FRAGMENT FOR THE VIEW SETTING
        fragmentTitleSettings = new FragmentTitleSettings();
        fragmentNavSettings = new FragmentNavSettings();
        fragmentDataSettings = new FragmentDataSettings();


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.id_IN_fragment_direct, fragmentDirect);
        transaction.add(R.id.id_IN_fragment_title, fragmentTitleMenu);
        transaction.add(R.id.id_IN_fragment_nav, fragmentNavMenu);
        transaction.replace(R.id.id_IN_fragment_data, fragmentDataMenu);
        currentView = VIEW_MENU;
        transaction.commit();

    }

    @Override
    public void changeFragment(int code) {
        if (currentView == code) {
            // do nothing
        } else {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction newTransaction = manager.beginTransaction();
            if (code == VIEW_MENU) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleMenu);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavMenu);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataMenu);
                newTransaction.addToBackStack(null);

            } else if (code == VIEW_LAP) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleLap);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavLap);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataLap);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_SIMPLE) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSimple);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSimple);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSimple);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_MEETING) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleMeeting);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavMeeting);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataMeeting);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_SWIMMER) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSwimmer);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSwimmer);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSwimmer);
                newTransaction.addToBackStack(null);

            } else if (code == VIEW_SETTING) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSettings);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSettings);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSettings);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_RANKING_MEET) {
                newTransaction.addToBackStack(null);

            } else if (code == VIEW_RANKING_SW) {
                newTransaction.addToBackStack(null);

            } else {
                Toast.makeText(this.getApplicationContext(), "A problem appear to get the good fragment", Toast.LENGTH_SHORT).show();
            }
            lastView = currentView;
            currentView = code;
            fragmentDirect.changeButtonDirect(code);
            fragmentDirect.changeButtonStartStop();
            newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            newTransaction.commit();
        }
    }


    @Override
    public void getGlobalLap(View view) {
//        Toast.makeText(this.getApplicationContext(),"Global: "+ position, Toast.LENGTH_SHORT).show();
        float milli = fragmentDirect.getMillisecondsLap();
        fragmentDataLap.addLapToModel(view, milli);

    }

    @Override
    public void resetLap(View view) {
        fragmentDataLap.resetLap(view);
    }

    @Override
    public void recordLap(View view) {
        // record lap in db
    }

    @Override
    public void saveLapList(ArrayList<RESULT> list) {
        savedLapList = new ArrayList<RESULT>();
        savedLapList = (ArrayList<RESULT>) list.clone();
    }

    @Override
    public ArrayList<RESULT> giveBackLapList() {
        return savedLapList;
    }

    @Override
    public void inverseButtonsInLap(boolean chronoIsStarted) {
        fragmentDataLap.setChronoIsStarted(chronoIsStarted);
        if (currentView == VIEW_LAP) {
            fragmentDataLap.changeButtonLap(chronoIsStarted);
        } else {
            changeFragment(VIEW_LAP);
        }
    }


    @Override
    public void onBackPressed() {
        if (currentView == VIEW_MENU) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure to quit?\n You will lost current chronometer !")
                    .setCancelable(true)
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do nothing: don't quit application
                        }
                    })
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            fragmentDirect.changeButtonDirect(lastView);
            currentView = lastView;
            lastView = 0;
            super.onBackPressed();
        }

    }
}
