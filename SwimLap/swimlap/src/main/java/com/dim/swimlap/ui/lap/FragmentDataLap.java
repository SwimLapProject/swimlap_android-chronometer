/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.lap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dim.swimlap.R;

public class FragmentDataLap extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_lap, container, false);
        return view;
    }

    @Override
    public void onClick(View view) {
//        long lap = fragmentDirect.getMillisecondsLap();
//        float time = (float) lap / 1000;
//        String timeAsString = String.valueOf(time);
//        textView.append(timeAsString + "\n");
//    }
    }
}
