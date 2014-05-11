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
import android.widget.Button;
import android.widget.LinearLayout;

import com.dim.swimlap.R;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.objects.Singleton;
import com.dim.swimlap.ui.CommunicationFragments;

import java.util.ArrayList;

;

public class FragmentNavLap extends Fragment implements View.OnClickListener {
    private LinearLayout linearLayout;
    private Singleton singleton;
    private CommunicationFragments communication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_lap, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.id_linearlayout_in_horizontalscrollview);
        singleton = Singleton.getInstance();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (singleton.buildMeetingOfTheDay(getActivity())) {
            ArrayList<EventModel> eventsOfTheDay = singleton.getAllEventsByOrderInMeeting();
            for (int indexEvent = 0; indexEvent < eventsOfTheDay.size(); indexEvent++) {
                RaceModel race = eventsOfTheDay.get(indexEvent).getRaceModel();
                String nameToPutInButton = race.getDistance() + " " + race.getStyle() + " " + race.getGender();
                int idRace = race.getId();

                Button button = new Button(this.getActivity());
                button.setText(nameToPutInButton);
                button.setTag("ButtonRace_" + idRace);
                button.setOnClickListener(this);
                if (idRace == singleton.getCurrentRaceId()) {
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_race_selected));
                } else {
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_race));

                }
                linearLayout.addView(button);
            }
        }
    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        String[] splitedTag = tag.split("_");
        int newRaceId = Integer.valueOf(splitedTag[1]);
        modifyButtonRaceSelected(newRaceId);

        singleton.setCurrentRaceId(newRaceId);
        communication = (CommunicationFragments) this.getActivity();
        communication.replaceFragmentDataLap(newRaceId);
    }

    public void modifyButtonRaceSelected(int newIdRace) {
        Button previousSelectedButton = (Button) linearLayout.findViewWithTag("ButtonRace_" + singleton.getCurrentRaceId());
        Button newSelectedButton = (Button) linearLayout.findViewWithTag("ButtonRace_" + newIdRace);
        previousSelectedButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_race));
        newSelectedButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_race_selected));
    }
}
