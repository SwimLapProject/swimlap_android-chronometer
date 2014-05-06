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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.objects.FormatTimeAsString;
import com.dim.swimlap.objects.Singleton;

import java.util.ArrayList;

public class FragmentDataLap extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private ListView listViewForLap;
    private TextView textViewNoMeetingInLap;
    private LapAdapter adapter;
    private FormatTimeAsString formatTime;
    private boolean chronoIsStarted;
    private ArrayList<ResultModel> list;
    private Singleton singleton;
    private int raceIdOfThisFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_lap, container, false);
        singleton = Singleton.getInstance();
        boolean meetingOfTheDayIsBuilt = singleton.buildEvent(getActivity());

        listViewForLap = (ListView) view.findViewById(R.id.id_listview_lap);
        textViewNoMeetingInLap = (TextView) view.findViewById(R.id.id_textview_no_meeting_in_lap);
        textViewNoMeetingInLap.setVisibility(View.INVISIBLE);


        if (!meetingOfTheDayIsBuilt) {
            // this case appear when there no club indicate in settings
            Toast.makeText(getActivity(), "You must complete settings please.", Toast.LENGTH_SHORT).show();
            textViewNoMeetingInLap.setText("No meeting found today");
            textViewNoMeetingInLap.setVisibility(View.VISIBLE);
            listViewForLap.setVisibility(View.INVISIBLE);
        } else {
            raceIdOfThisFragment = singleton.getCurrentRaceId();
            listViewForLap.setVisibility(View.VISIBLE);
            textViewNoMeetingInLap.setVisibility(View.INVISIBLE);

            ArrayList<ResultModel> resultsToDisplay = singleton.getResultsByRace(raceIdOfThisFragment);
            adapter = new LapAdapter(this.getActivity(), resultsToDisplay, chronoIsStarted);
            listViewForLap.setAdapter(adapter);

        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewForLap.setOnScrollListener(this);
        formatTime = new FormatTimeAsString();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(view.getContext(), "fragment", Toast.LENGTH_SHORT).show();

    }

    public void addLapToModel(View view, float milli) {
        Integer position = getPositionOfView(view);

        View viewRow = listViewForLap.getChildAt(position);
        ResultModel ResultModel = singleton.getResultOfTheDay(position);

        int nbSplitRemaining = ResultModel.getnbSplitRemaining();
        if (nbSplitRemaining > 0) {
            float lapDiff = ResultModel.checkLap(milli);
            String splitAsString = formatTime.makeString(lapDiff);
            String lapAsString = formatTime.makeString(milli);
            String tripName = ResultModel.getSplitName();

            TextView textViewLast = (TextView) viewRow.findViewWithTag("TV_last_" + position);
            TextView textViewAll = (TextView) viewRow.findViewWithTag("TV_all_" + position);
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
            Toast.makeText(view.getContext(), "All laps taken !", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeButtonLap(boolean chronoIsStarted) {
        this.chronoIsStarted = chronoIsStarted;
        int firstVisibleRow = listViewForLap.getFirstVisiblePosition();
        int lastVisibleRow = listViewForLap.getLastVisiblePosition();
        doChangeButtonLap(firstVisibleRow, lastVisibleRow);

    }

    private void doChangeButtonLap(int firstVisible, int lastVisible) {

        for (int position = firstVisible; position <= lastVisible; position++) {
            View viewRow = listViewForLap.getChildAt(position);
            if (viewRow != null) {
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
    }


    public void setChronoIsStarted(boolean isStarted) {
        this.chronoIsStarted = isStarted;
    }

    public void resetLaps(View view) {
        int position = getPositionOfView(view);
        //reset data
        singleton.getResultOfTheDay(position).resetLaps();

        //reset view
        View viewRow = listViewForLap.getChildAt(position);
        TextView textViewAll = (TextView) viewRow.findViewById(R.id.id_textview_all_laps);
        textViewAll.setText("All laps");
        TextView textViewLast = (TextView) viewRow.findViewById(R.id.id_textview_last);
        textViewLast.setText("Last: 0:00.00");
        textViewLast.setTextColor(getResources().getColor(R.color.bluesea));

    }

    public void recordLaps(View view) {
        int position = getPositionOfView(view);
        //reset data
        singleton.getResultOfTheDay(position).recordLapsInDB(getActivity());

        //modify view
    }

    private int getPositionOfView(View view) {
        String posString = (String) view.getTag();
        posString = posString.substring(4);
        return Integer.valueOf(posString);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        // do nothing
    }

    @Override
    public void onScroll(AbsListView absListView, int first, int count, int total) {
        // in count there is the first so must minus one
        int last = first + count - 1;
        doChangeButtonLap(first, last);
    }
}
