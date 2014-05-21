/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.simple;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import com.dim.swimlap.R;
import com.dim.swimlap.objects.Singleton;
import com.dim.swimlap.ui.CommunicationFragments;

;

public class FragmentNavSimple extends Fragment implements View.OnClickListener {
    private Button buttonStart, buttonStop;
    private Chronometer chronometer;
    private CommunicationFragments comm;
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
        View view = inflater.inflate(R.layout.fragment_nav_simple, container, false);
        comm = (CommunicationFragments) getActivity();


        chronometer = (Chronometer) view.findViewById(R.id.id_chrono_simple);

        buttonStart = (Button) view.findViewById(R.id.id_button_start_simple);
        buttonStart.setOnClickListener(this);

        buttonStop = (Button) view.findViewById(R.id.id_button_stop_simple);
        buttonStop.setOnClickListener(this);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_button_start_simple) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            comm.removeAllSimpleLaps();
        } else if (view.getId() == R.id.id_button_stop_simple) {
            chronometer.stop();
        }
    }
    public long getMillisecondsSimpleLap() {
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }
}
