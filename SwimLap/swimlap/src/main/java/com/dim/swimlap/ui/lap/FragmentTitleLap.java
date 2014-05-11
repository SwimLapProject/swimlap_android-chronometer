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
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.objects.Singleton;

;

public class FragmentTitleLap extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title_lap, container, false);
        String name = Singleton.getInstance().getMeetingName();
        TextView textViewTitle = (TextView) view.findViewById(R.id.id_textview_title);
        textViewTitle.setText(name);
        return view;
    }


}
