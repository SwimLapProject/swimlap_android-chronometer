package com.dim.swimlap.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dim.swimlap.R;

import java.util.Date;

public class FragementLaps extends Fragment implements View.OnClickListener {

    private Button buttonLap;
    private TextView swimmerName, lastLap;
    private FragmentChrono fragmentChrono;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laps, container);

        buttonLap = (Button) view.findViewById(R.id.id_fragmentlaps_buttonlap);
        buttonLap.setOnClickListener(this);

        lastLap = (TextView) view.findViewById(R.id.id_fragmentlaps_textviewlastlap);

        fragmentChrono = (FragmentChrono) getFragmentManager().findFragmentById(R.id.id_globalcontainer_fragmentchrono);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_fragmentlaps_buttonlap) {
            long lap = fragmentChrono.getMillisecondsLap();
            Date date = new Date(lap);
            Toast.makeText(view.getContext(), "LAP: "+date.toString(), Toast.LENGTH_SHORT).show();
            lastLap.append("\n"+date.toString());
        }
    }
}
