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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.SwimmerModel;
import com.dim.swimlap.objects.FormatTimeAsString;

import java.util.ArrayList;;

public class FragmentDataLap extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listViewForLap;
    private LapAdapter adapter;
    private FormatTimeAsString formatTime;
    private boolean chronoIsStarted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_lap, container, false);
        listViewForLap = (ListView) view.findViewById(R.id.id_listview_lap);
        adapter = new LapAdapter(this.getActivity(), buildEvent(),chronoIsStarted);
        listViewForLap.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewForLap.setOnItemClickListener(this);
        formatTime = new FormatTimeAsString();
    }


    private ArrayList<DataLapForList> buildEvent() {
        ArrayList<DataLapForList> list = new ArrayList<DataLapForList>();
        for (int i = 0; i < 3; i++) {
            SwimmerModel swimmerModel = new SwimmerModel();
            swimmerModel.setName("Name" + i);
            swimmerModel.setFirstname("FirstName" + i);
            swimmerModel.setIdSwimmer(888888);
            swimmerModel.setDateOfBirth(String.valueOf(1987 + i));
            EventModel eventModel = new EventModel();
            eventModel.setQualifyingTime(new Float(60789));
            eventModel.setRaceId(4);
            DataLapForList dataLapForList = new DataLapForList(swimmerModel, eventModel, 25);
            list.add(dataLapForList);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(view.getContext(), "fragment", Toast.LENGTH_SHORT).show();

    }

    public void addLapToModel(View view, float milli) {
        FormatTimeAsString formatTime = new FormatTimeAsString();
        String posString = (String) view.getTag();
        posString = posString.substring(4);
        Integer posInt = Integer.valueOf(posString);

        View viewRow = listViewForLap.getChildAt(posInt);
        DataLapForList dataLapForList = adapter.eventArrayList.get(posInt);
        int nbSplitRemaining = dataLapForList.getnbSplitRemaining();
        if (nbSplitRemaining > 0) {
            float lapDiff = dataLapForList.checkLap(milli);
            String splitAsString = formatTime.makeString(lapDiff);
            String lapAsString = formatTime.makeString(milli);
            String tripName = dataLapForList.getSplitName();

            TextView textViewLast = (TextView) viewRow.findViewWithTag("TV_last_" + posString);
            TextView textViewAll = (TextView) viewRow.findViewWithTag("TV_all_" + posString);
            textViewAll.append("\n" + tripName + ": " + splitAsString + " - " + lapAsString);

            ScrollView scrollView = (ScrollView) viewRow.findViewById(R.id.id_scrollview_laps);
            scrollView.fullScroll(ScrollView.FOCUS_DOWN);

            if (nbSplitRemaining == 1) {
                textViewLast.setTextColor(getResources().getColor(R.color.redstop));
                textViewLast.setText("FINAL : " + lapAsString);
            } else {
                textViewLast.setText(lapAsString);
            }
        } else {
            Toast.makeText(view.getContext(), "All lap taken !", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeButtonLap(boolean chronoIsStarted) {
        for (int position = 0; position <= listViewForLap.getLastVisiblePosition(); position++) {
            View viewRow = listViewForLap.getChildAt(position);
            if (chronoIsStarted) {
                viewRow.findViewById(R.id.id_button_take_lap).setVisibility(View.VISIBLE);
                viewRow.findViewById(R.id.id_button_reset_lap).setVisibility(View.INVISIBLE);
                viewRow.findViewById(R.id.id_button_record_lap).setVisibility((View.INVISIBLE));
            } else {
                viewRow.findViewById(R.id.id_button_take_lap).setVisibility(View.INVISIBLE);
                viewRow.findViewById(R.id.id_button_reset_lap).setVisibility(View.VISIBLE);
                viewRow.findViewById(R.id.id_button_record_lap).setVisibility((View.VISIBLE));
            }

        }
    }

    public void setChronoIsStarted(boolean isStarted) {
        this.chronoIsStarted = isStarted;
    }
}
