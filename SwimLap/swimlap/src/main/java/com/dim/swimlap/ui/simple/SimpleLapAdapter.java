/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.objects.FormatTimeAsString;
import com.dim.swimlap.ui.CommunicationFragments;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleLapAdapter extends ArrayAdapter {

    private final Context context;
    private FormatTimeAsString formatTime;
    private ViewHolder viewHolder;
    private ArrayList<String> raceNames;
    private CommunicationFragments comm;
    private HashMap<String, ArrayList<Float>> lapForRaces;


    public SimpleLapAdapter(Context context, ArrayList<String> raceNames, HashMap<String, ArrayList<Float>> lapForRaces) {
        super(context, R.layout.viewforlist_simple, raceNames);
        this.context = context;
        this.raceNames = raceNames;
        this.lapForRaces = lapForRaces;
        this.formatTime = new FormatTimeAsString();
        //todo put the name, and do takelap and reset in fragmentDataSimple

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        comm = (CommunicationFragments) getContext();

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.viewforlist_simple, parent, false);

            viewHolder = new ViewHolder();
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        // BUILD ELEMENTS OF ROW

        String raceName = raceNames.get(position);
        ArrayList<Float> laps = lapForRaces.get(raceName);
        int lastLapIndex = laps.size() - 1;
        String lastLap = "0.0000";
        int placeOfRace = position+1;
        if (laps.size() > 0) {
            lastLap = formatTime.makeString(laps.get(lastLapIndex));
        }
        String allLapsAsString = buildAllLapsString(laps);


        // BUTTON LAP
        viewHolder.buttonTakeLap = (Button) rowView.findViewById(R.id.id_button_simple_takelap);
        viewHolder.buttonTakeLap.setTag("SimpleTakelap_" + position);

        // BUTTON LAP
        viewHolder.buttonUnLap = (Button) rowView.findViewById(R.id.id_button_simple_unlap);
        viewHolder.buttonUnLap.setTag("SimpleUnlap_" + position);


        // TEXT VIEW

        viewHolder.textViewRaceName = (TextView) rowView.findViewById(R.id.id_textview_lap_simple_race_name);
        viewHolder.textViewRaceName.setText("Race "+ placeOfRace);


        viewHolder.textViewAllLaps = (TextView) rowView.findViewById(R.id.id_textview_simple_all_laps);
        viewHolder.textViewAllLaps.setTag("SimpleAll_" + position);
        viewHolder.textViewAllLaps.setText(allLapsAsString);


        viewHolder.textViewLastLap = (TextView) rowView.findViewById(R.id.id_textview__simple_last_lap);
        viewHolder.textViewLastLap.setTag("SimpleLast" + position);
        viewHolder.textViewLastLap.setText(lastLap);


        ScrollView scrollView = (ScrollView) rowView.findViewById(R.id.id_scrollview_simple_all_laps);
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);

        rowView.setTag(viewHolder);

        return rowView;
    }


    private static class ViewHolder {
        public static Button buttonTakeLap, buttonUnLap;
        public static TextView textViewRaceName, textViewLastLap, textViewAllLaps;
    }

    private String buildAllLapsString(ArrayList<Float> laps) {
        String allLapsAsString = "";
        for (int indexLap = 0; indexLap < laps.size(); indexLap++) {
            float split = 0;
            if (indexLap == 0) {
                split = laps.get(indexLap);
            } else {
                split = laps.get(indexLap) - laps.get(indexLap - 1);
            }
            int lapNumber = indexLap+1;
            allLapsAsString += lapNumber + " : "
                    + formatTime.makeString(split)
                    + " , "
                    + formatTime.makeString(laps.get(indexLap))
                    + "\n";
        }
        return allLapsAsString;
    }

}