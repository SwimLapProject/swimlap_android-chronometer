/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.dim.swimlap.R;
import com.dim.swimlap.ui.CommunicationFragments;

;

public class FragmentDataMenu extends Fragment implements View.OnClickListener {
    private Button buttonSimple, buttonMeetings, buttonSwimmers, buttonSettings;
    private CommunicationFragments communicationFragments;
    private static int
            VIEW_SIMPLE = 2,
            VIEW_MEETING = 3,
            VIEW_SWIMMER = 4,
            VIEW_SETTING = 5,
            VIEW_RANKING_MEET = 6,
            VIEW_RANKING_SW = 7;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_menu, container, false);
        buttonSimple = (Button) view.findViewById(R.id.id_button_to_simplechronometer);
        buttonMeetings = (Button) view.findViewById(R.id.id_button_to_meetings);
        buttonSwimmers = (Button) view.findViewById(R.id.id_button_to_swimmers);
        buttonSettings = (Button) view.findViewById(R.id.id_button_to_settings);
        progressBar = (ProgressBar) view.findViewById(R.id.id_progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonSimple.setOnClickListener(this);
        buttonMeetings.setOnClickListener(this);
        buttonSwimmers.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);

        communicationFragments = (CommunicationFragments) this.getActivity();
    }

    @Override
    public void onClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        if (view.getId() == R.id.id_button_to_settings) {
            communicationFragments.changeFragment(VIEW_SETTING);
        } else if (view.getId() == R.id.id_button_to_swimmers) {
            communicationFragments.changeFragment(VIEW_SWIMMER);
        } else if (view.getId() == R.id.id_button_to_meetings) {
            communicationFragments.changeFragment(VIEW_MEETING);
        } else if (view.getId() == R.id.id_button_to_simplechronometer) {
            communicationFragments.changeFragment(VIEW_SIMPLE);
        }
    }
}
