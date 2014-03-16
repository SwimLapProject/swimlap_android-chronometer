/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.fragments;

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
import com.dim.swimlap.objects.ChronometerWithMilli;

public class FragmentChrono extends Fragment implements View.OnClickListener {

    private Button buttonStart, buttonStop;
    private Chronometer chronometer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chrono, container);

        buttonStart = (Button) view.findViewById(R.id.id_fragmentchrono_buttonstart);
        buttonStart.setOnClickListener(this);

        buttonStop = (Button) view.findViewById(R.id.id_fragmentchrono_buttonstop);
        buttonStop.setOnClickListener(this);
        buttonStop.setVisibility(View.INVISIBLE);

        chronometer = (Chronometer) view.findViewById(R.id.id_fragmentchrono_chronodigit);
//        chronometer.setFormat("MM:SS");
        return view;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_fragmentchrono_buttonstart) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            buttonStop.setVisibility(View.VISIBLE);
            buttonStart.setVisibility(View.INVISIBLE);
            Toast.makeText(view.getContext(),"START",Toast.LENGTH_SHORT).show();


        } else if (view.getId() == R.id.id_fragmentchrono_buttonstop) {
            chronometer.stop();
            buttonStop.setVisibility(View.INVISIBLE);
            buttonStart.setVisibility(View.VISIBLE);
            Toast.makeText(view.getContext(),"STOP",Toast.LENGTH_SHORT).show();

        }
    }

    public long getMillisecondsLap(){
        return SystemClock.elapsedRealtime() - chronometer.getBase();
                //return null;
    }
}
