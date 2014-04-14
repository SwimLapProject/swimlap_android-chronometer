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
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.ui.CommunicationFragments;

public class FragmentDataMenu extends Fragment implements View.OnClickListener {
    private Button buttonSimple, buttonMeetings, buttonSwimmers, buttonSettings;
    private CommunicationFragments communicationFragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_menu, container, false);
        buttonSimple = (Button) view.findViewById(R.id.id_button_to_simplechronometer);
        buttonMeetings = (Button) view.findViewById(R.id.id_button_to_meetings);
        buttonSwimmers = (Button) view.findViewById(R.id.id_button_to_swimmers);
        buttonSettings = (Button) view.findViewById(R.id.id_button_to_settings);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        buttonSimple.setOnClickListener(this);
        buttonMeetings.setOnClickListener(this);
        buttonSwimmers.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);

        communicationFragments = (CommunicationFragments) this.getActivity();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.id_button_to_settings) {
            communicationFragments.changeFragment(5);
        }
    }
}
