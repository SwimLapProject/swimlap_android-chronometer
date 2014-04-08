/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.content;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.dim.swimlap.R;
import com.dim.swimlap.ui.FragmentDirect;

import java.util.Date;

public class FragementData extends Fragment implements View.OnClickListener {

    private Button buttonLap;
    private TextView swimmerName, lastLap;
    private FragmentDirect fragmentDirect;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//         View view = inflater.inflate(R.layout.fragment_laps, container);
//
//        buttonLap = (Button) view.findViewById(R.id.id_fragmentlaps_buttonlap);
//        buttonLap.setOnClickListener(this);
//
//        lastLap = (TextView) view.findViewById(R.id.id_fragmentlaps_textviewlastlap);
//
//        fragmentDirect = (FragmentDirect) getFragmentManager().findFragmentById(R.id.id_globalcontainer_fragmentchrono);
//        return view;
//    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.id_fragmentlaps_buttonlap) {
//            long lap = fragmentDirect.getMillisecondsLap();
//            Date date = new Date(lap);
//            Toast.makeText(view.getContext(), "LAP: "+date.toString(), Toast.LENGTH_SHORT).show();
//            lastLap.append("\n"+date.toString());
//        }
    }
}
