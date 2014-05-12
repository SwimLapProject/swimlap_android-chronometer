/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.objects.Singleton;

public class FragmentDirect extends Fragment implements View.OnClickListener {

    private Button buttonStart, buttonStop;
    private Button buttonDirect, buttonBackToMenu;
    private Chronometer chronometer;
    private int currentView;
    private CommunicationFragments communicationFragments;
    private Singleton singleton;
    private static int
            VIEW_MENU = 0,
            VIEW_LAP = 1,
            VIEW_SIMPLE = 2,
            VIEW_MEETING = 3,
            VIEW_SWIMMER = 4,
            VIEW_SETTING = 5,
            VIEW_RANKING_MEET = 6,
            VIEW_RANKING_SW = 7;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direct, container, false);

        buttonDirect = (Button) view.findViewById(R.id.id_button_direct);
        buttonDirect.setOnClickListener(this);

        buttonBackToMenu = (Button) view.findViewById(R.id.id_button_back_to_menu);
        buttonBackToMenu.setOnClickListener(this);
        buttonBackToMenu.setVisibility(View.INVISIBLE);

        chronometer = (Chronometer) view.findViewById(R.id.id_chrono_digit);

        buttonStart = (Button) view.findViewById(R.id.id_button_start);
        buttonStart.setOnClickListener(this);
        buttonStart.setVisibility(View.INVISIBLE);


        buttonStop = (Button) view.findViewById(R.id.id_button_stop);
        buttonStop.setOnClickListener(this);
        buttonStop.setVisibility(View.INVISIBLE);

        currentView = 0;
        singleton=Singleton.getInstance();
        singleton.setChronoIsStarted(false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicationFragments = (CommunicationFragments) this.getActivity();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_button_start) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            singleton.setChronoIsStarted(true);
            changeButtonStartStop();
            communicationFragments.inverseButtonsInLap();
            Toast.makeText(view.getContext(), "START", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.id_button_stop) {
            chronometer.stop();
            singleton.setChronoIsStarted(false);
            changeButtonStartStop();
            communicationFragments.inverseButtonsInLap();
            Toast.makeText(view.getContext(), "STOP", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.id_button_direct) {
            communicationFragments.changeFragment(VIEW_LAP);
            buttonBackToMenu.setVisibility(View.VISIBLE);
            buttonDirect.setVisibility(View.INVISIBLE);
        } else if (view.getId() == R.id.id_button_back_to_menu) {
            communicationFragments.changeFragment(VIEW_MENU);
            buttonBackToMenu.setVisibility(View.INVISIBLE);
            buttonDirect.setVisibility(View.VISIBLE);
        }
    }

    public long getMillisecondsLap() {
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    public void changeStatusOfContent(int newStatus) {
        currentView = newStatus;
    }

    public int getCurrentView() {
        return currentView;
    }

    public void changeButtonStartStop() {
        if (singleton.isChronoStarted()) {
            buttonStart.setVisibility(View.INVISIBLE);
            buttonStop.setVisibility(View.VISIBLE);
        } else {
            buttonStart.setVisibility(View.VISIBLE);
            buttonStop.setVisibility(View.INVISIBLE);
        }
    }

    public void changeButtonDirect(int code) {
        if (code == VIEW_LAP) {
            buttonBackToMenu.setVisibility(View.VISIBLE);
            buttonDirect.setVisibility(View.INVISIBLE);

        } else {
            buttonBackToMenu.setVisibility(View.INVISIBLE);
            buttonDirect.setVisibility(View.VISIBLE);
        }
    }

}
