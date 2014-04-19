/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.dim.swimlap.R;
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

public class GlobalContainer extends FragmentActivity implements CommunicationFragments {

    private FragmentTransaction transaction;
    private FragmentDirect fragmentDirect;

    private FragmentTitleMenu fragmentTitleMenu;
    private FragmentNavMenu fragmentNavMenu;
    private FragmentDataMenu fragmentDataMenu;

    private FragmentTitleLap fragmentTitleLap;
    private FragmentNavLap fragmentNavLap;
    private FragmentDataLap fragmentDataLap;

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

    private int actualView;
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
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
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

        transaction.add(R.id.id_IN_fragment_direct, fragmentDirect);
        transaction.add(R.id.id_IN_fragment_title, fragmentTitleMenu);
        transaction.add(R.id.id_IN_fragment_nav, fragmentNavMenu);
        transaction.add(R.id.id_IN_fragment_data, fragmentDataMenu);
        actualView = VIEW_MENU;
        transaction.commit();

    }

    @Override
    public void changeFragment(int code) {
        if (actualView == code) {
            // do nothing
        } else {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction newTransaction = manager.beginTransaction();
            if (code == VIEW_MENU) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleMenu);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavMenu);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataMenu);
            } else if (code == VIEW_LAP) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleLap);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavLap);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataLap);
            } else if (code == VIEW_SIMPLE) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSimple);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSimple);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSimple);
            } else if (code == VIEW_MEETING) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleMeeting);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavMeeting);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataMeeting);
            } else if (code == VIEW_SWIMMER) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSwimmer);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSwimmer);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSwimmer);
            } else if (code == VIEW_SETTING) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSettings);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSettings);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSettings);
            } else if (code == VIEW_RANKING_MEET) {

            } else if (code == VIEW_RANKING_SW) {

            } else {
                Toast.makeText(this.getApplicationContext(), "A problem appear to get the good fragment", Toast.LENGTH_SHORT).show();
            }
            actualView = code;
            newTransaction.addToBackStack(null);
            newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            newTransaction.commit();
        }

    }
}
