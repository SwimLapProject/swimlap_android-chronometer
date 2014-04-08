/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.dim.swimlap.R;

public class FragmentDirect extends Fragment implements View.OnClickListener {

    private Button buttonStart, buttonStop, buttonDirect;
    private Chronometer chronometer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direct, container);

        buttonDirect = (Button) view.findViewById(R.id.id_button_direct);
        buttonDirect.setOnClickListener(this);

        chronometer = (Chronometer) view.findViewById(R.id.id_chrono_digit);
        chronometer.setFormat("MM:SS");

        buttonStart = (Button) view.findViewById(R.id.id_button_start);
        buttonStart.setOnClickListener(this);

        buttonStop = (Button) view.findViewById(R.id.id_button_stop);
        buttonStop.setOnClickListener(this);
        buttonStop.setVisibility(View.INVISIBLE);

        return view;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_button_start) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            buttonStop.setVisibility(View.VISIBLE);
            buttonStart.setVisibility(View.INVISIBLE);
            Toast.makeText(view.getContext(), "START", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.id_button_stop) {
            chronometer.stop();
            buttonStop.setVisibility(View.INVISIBLE);
            buttonStart.setVisibility(View.VISIBLE);
            Toast.makeText(view.getContext(), "STOP", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.id_button_direct) {
            Toast.makeText(view.getContext(), "GO", Toast.LENGTH_SHORT).show();
        }
    }

    public long getMillisecondsLap() {
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }
}
