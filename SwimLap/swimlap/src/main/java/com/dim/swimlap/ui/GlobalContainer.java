/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.objects.Singleton;
import com.dim.swimlap.ui.lap.FragmentDataLap;
import com.dim.swimlap.ui.lap.FragmentNavLap;
import com.dim.swimlap.ui.lap.FragmentTitleLap;
import com.dim.swimlap.ui.meeting.FragmentTitleMeeting;
import com.dim.swimlap.ui.meeting.detail.FragmentDataMeetingDetails;
import com.dim.swimlap.ui.meeting.detail.FragmentNavMeetingDetails;
import com.dim.swimlap.ui.meeting.list.FragmentDataMeetingList;
import com.dim.swimlap.ui.meeting.list.FragmentNavMeetingList;
import com.dim.swimlap.ui.menu.FragmentDataMenu;
import com.dim.swimlap.ui.menu.FragmentNavMenu;
import com.dim.swimlap.ui.menu.FragmentTitleMenu;
import com.dim.swimlap.ui.ranking.FragmentDataRanking;
import com.dim.swimlap.ui.ranking.FragmentNavRanking;
import com.dim.swimlap.ui.ranking.FragmentTitleRanking;
import com.dim.swimlap.ui.settings.FragmentDataSettings;
import com.dim.swimlap.ui.settings.FragmentNavSettings;
import com.dim.swimlap.ui.settings.FragmentTitleSettings;
import com.dim.swimlap.ui.simple.FragmentDataSimple;
import com.dim.swimlap.ui.simple.FragmentNavSimple;
import com.dim.swimlap.ui.simple.FragmentTitleSimple;
import com.dim.swimlap.ui.swimmer.FragmentTitleSwimmer;
import com.dim.swimlap.ui.swimmer.list.FragmentDataSwimmer;
import com.dim.swimlap.ui.swimmer.list.FragmentNavSwimmer;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalContainer extends FragmentActivity implements CommunicationFragments {

    private FragmentDirect fragmentDirect;

    private FragmentTitleMenu fragmentTitleMenu;
    private FragmentNavMenu fragmentNavMenu;
    private FragmentDataMenu fragmentDataMenu;

    private FragmentTitleLap fragmentTitleLap;
    private FragmentNavLap fragmentNavLap;
    private HashMap<Integer, FragmentDataLap> mapOfFragmentLap;
    private ArrayList<ResultModel> savedLapList;

    private FragmentTitleSimple fragmentTitleSimple;
    private FragmentNavSimple fragmentNavSimple;
    private FragmentDataSimple fragmentDataSimple;

    private FragmentTitleMeeting fragmentTitleMeeting;
    private FragmentNavMeetingList fragmentNavMeetingList;
    private FragmentDataMeetingList fragmentDataMeetingList;

    private FragmentTitleSwimmer fragmentTitleSwimmer;
    private FragmentNavSwimmer fragmentNavSwimmer;
    private FragmentDataSwimmer fragmentDataSwimmer;

    private FragmentTitleSettings fragmentTitleSettings;
    private FragmentNavSettings fragmentNavSettings;
    private FragmentDataSettings fragmentDataSettings;

    private FragmentTitleRanking fragmentTitleRanking;
    private FragmentNavRanking fragmentNavRanking;
    private FragmentDataRanking fragmentDataRanking;

    private int currentView, lastView;
    private Singleton singleton;

    private static int
            VIEW_MENU = 0,
            VIEW_LAP = 1,
            VIEW_SIMPLE = 2,
            VIEW_MEETING = 3,
            VIEW_SWIMMER = 4,
            VIEW_SETTING = 5,
            VIEW_RANKING = 6,
            VIEW_MEETING_DETAILS = 7;


    public GlobalContainer() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_container);
        singleton = Singleton.getInstance();
        singleton.buildMeetingOfTheDay(getApplicationContext());

        // FRAGMENT FOR THE VIEW MENU
        fragmentDirect = new FragmentDirect();
        fragmentTitleMenu = new FragmentTitleMenu();
        fragmentNavMenu = new FragmentNavMenu();
        fragmentDataMenu = new FragmentDataMenu();

        // FRAGMENT FOR THE VIEW LAP
        fragmentTitleLap = new FragmentTitleLap();
        fragmentNavLap = new FragmentNavLap();
        buildFragmentsForLapData();

//        fragmentDataLap = new FragmentDataLap();

        // FRAGMENT FOR VIEW SIMPLE CHRONOMETER
        fragmentTitleSimple = new FragmentTitleSimple();
        fragmentNavSimple = new FragmentNavSimple();
        fragmentDataSimple = new FragmentDataSimple();

        // FRAGMENT FOR THE VIEW MEETING
        fragmentTitleMeeting = new FragmentTitleMeeting();
        fragmentNavMeetingList = new FragmentNavMeetingList();
        fragmentDataMeetingList = new FragmentDataMeetingList();

        //FRAGMENT FOR VIEW SWIMMER
        fragmentTitleSwimmer = new FragmentTitleSwimmer();
        fragmentNavSwimmer = new FragmentNavSwimmer();
        fragmentDataSwimmer = new FragmentDataSwimmer();

        // FRAGMENT FOR THE VIEW SETTING
        fragmentTitleSettings = new FragmentTitleSettings();
        fragmentNavSettings = new FragmentNavSettings();
        fragmentDataSettings = new FragmentDataSettings();

        // FRAGMENT FOR RANKING
        fragmentTitleRanking = new FragmentTitleRanking();
        fragmentNavRanking = new FragmentNavRanking();
        fragmentDataRanking = new FragmentDataRanking();

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
                if (singleton.buildMeetingOfTheDay(getApplicationContext())) {
                    newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleLap);
                    newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavLap);
                    addFragmentDataLapDependOnRaceId(newTransaction, singleton.getCurrentRaceId());
                } else {
                    Toast.makeText(getApplicationContext(), "No meeting Today.\nUse Simple Chronometer.", Toast.LENGTH_SHORT).show();
                    code = -1;
                }
            } else if (code == VIEW_SIMPLE) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSimple);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSimple);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSimple);
                newTransaction.addToBackStack(null);
            } else if (code == VIEW_MEETING) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleMeeting);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavMeetingList);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataMeetingList);
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
            } else if (code == VIEW_RANKING) {
                newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleRanking);
                newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavRanking);
                newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataRanking);
                newTransaction.addToBackStack(null);

            } else {
                Toast.makeText(this.getApplicationContext(), "A problem appear to get the good fragment", Toast.LENGTH_SHORT).show();
            }
            if (code != -1) {
                lastView = currentView;
                currentView = code;
                fragmentDirect.changeButtonDirect(code);
                fragmentDirect.changeButtonStartStop();
                newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                newTransaction.commit();
            }
        }
    }

    @Override
    public void replaceFragmentDataLap(int raceId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction newTransaction = manager.beginTransaction();
        addFragmentDataLapDependOnRaceId(newTransaction, raceId);
        newTransaction.addToBackStack(null);
        newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        newTransaction.commit();
    }

    private void addFragmentDataLapDependOnRaceId(FragmentTransaction newTransaction, int newRaceId) {
        FragmentDataLap fragmentLapToAdd = mapOfFragmentLap.get(newRaceId);
        newTransaction.replace(R.id.id_IN_fragment_data, fragmentLapToAdd);
    }

    @Override
    public void getGlobalLap(View view) {
        float lapInMilli = fragmentDirect.getMillisecondsLap();

        String[] tag = String.valueOf(view.getTag()).split("_");
        int raceIdClicked = Integer.valueOf(tag[2]);

        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(raceIdClicked);
        fragmentDataLap.addLapToModel(view, lapInMilli);

    }

    @Override
    public void resetLap(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int raceId = Integer.valueOf(tag[2]);
        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(raceId);
        fragmentDataLap.resetLaps(view);
    }

    @Override
    public void recordLap(View view) {
        String[] tag = String.valueOf(view.getTag()).split("_");
        int raceIdClicked = Integer.valueOf(tag[2]);

        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(raceIdClicked);
        fragmentDataLap.recordLaps(view);
    }

    @Override
    public void saveLapList(ArrayList<ResultModel> list) {
        savedLapList = new ArrayList<ResultModel>();
        savedLapList = (ArrayList<ResultModel>) list.clone();
    }

    @Override
    public ArrayList<ResultModel> giveBackLapList() {
        return savedLapList;
    }


    @Override
    public void inverseButtonsInLap() {
        FragmentDataLap fragmentDataLap = mapOfFragmentLap.get(singleton.getCurrentRaceId());
        if (currentView == VIEW_LAP) {
            fragmentDataLap.changeButtonLap();
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
        } else if (currentView == VIEW_LAP) {
            // do nothing
        } else {
            fragmentDirect.changeButtonDirect(lastView);
            currentView = lastView;
            lastView = 0;
            super.onBackPressed();
        }

    }

    public void buildFragmentsForLapData() {

        if (mapOfFragmentLap == null) {
            mapOfFragmentLap = new HashMap<Integer, FragmentDataLap>();

        } else {
            mapOfFragmentLap.clear();
        }
        if (singleton.buildMeetingOfTheDay(getApplicationContext())) {
            ArrayList<EventModel> events = singleton.getAllEventsByOrderInMeeting();
            for (int indexEvent = 0; indexEvent < events.size(); indexEvent++) {
                int raceId = events.get(indexEvent).getRaceModel().getId();
                FragmentDataLap fragmentToAdd = new FragmentDataLap(raceId);
                mapOfFragmentLap.put(raceId, fragmentToAdd);
            }
        }
    }

    public void replaceFragmentsMeetingToDetails(MeetingModel meetingToDetails) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction newTransaction = manager.beginTransaction();
        lastView = currentView;
        currentView = VIEW_MEETING_DETAILS;

        FragmentNavMeetingDetails fragmentNav = new FragmentNavMeetingDetails(meetingToDetails);
        FragmentDataMeetingDetails fragmentData = new FragmentDataMeetingDetails(meetingToDetails);

        newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNav);
        newTransaction.replace(R.id.id_IN_fragment_data, fragmentData);

        newTransaction.addToBackStack(null);
        newTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        newTransaction.commit();
    }
}