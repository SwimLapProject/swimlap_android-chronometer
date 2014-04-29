/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.lap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.models.RESULT;
import com.dim.swimlap.objects.FormatTimeAsString;

import java.util.ArrayList;

public class LapAdapter extends ArrayAdapter {
    private final Context context;
    public final ArrayList<RESULT> resultList;
    private FormatTimeAsString formatTime;
    private boolean chronoIsStarted;

    public LapAdapter(Context context, ArrayList<RESULT> resultList, boolean chronoIsStarted) {
        super(context, R.layout.viewforlist_data_lap, resultList);
        this.context = context;
        this.resultList = resultList;
        this.formatTime = new FormatTimeAsString();
        this.chronoIsStarted = chronoIsStarted;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.viewforlist_data_lap, parent, false);

        // BUILD ELEMENT OF ROW

        // BUTTON LAP
        Button buttonLap = (Button) rowView.findViewById(R.id.id_button_take_lap);
        buttonLap.setVisibility(View.INVISIBLE);
        buttonLap.setTag("lap_" + position);
        buttonLap.setBackgroundResource(R.drawable.button_basic_with_shadow);

        //BUTTON RECORD
        Button buttonLapRecord = (Button) rowView.findViewById(R.id.id_button_record_lap);
        buttonLapRecord.setVisibility(View.VISIBLE);
        buttonLapRecord.setTag("rec_" + position);
        buttonLapRecord.setBackgroundResource(R.drawable.button_basic_with_shadow);

        //BUTTON RESET
        Button buttonLapReset = (Button) rowView.findViewById(R.id.id_button_reset_lap);
        buttonLapReset.setVisibility(View.VISIBLE);
        buttonLapReset.setTag("res_" + position);
        buttonLapReset.setBackgroundResource(R.drawable.button_basic_with_shadow);


        // TEXT VIEW
        TextView textViewSwimmerDetails = (TextView) rowView.findViewById(R.id.id_textview_lap_swimmerdetails);
        TextView textViewQualifiedTime = (TextView) rowView.findViewById(R.id.id_textview_qualifiedTime);

//        textViewSwimmerDetails.setId(resultList.get(position).getEventModel().getId());

        // FILL ELEMENT WITH DATA FROM DB
        String name = resultList.get(position).swimmerModel.getName();
        String firstName = resultList.get(position).swimmerModel.getFirstname();
        String dateOfBird = resultList.get(position).swimmerModel.getDateOfBirth();
        float qualifyingTime = resultList.get(position).getQualifyingTime();
        String qualifTimeAsString = formatTime.makeString(qualifyingTime);
        textViewSwimmerDetails.setText(name + "  " + firstName + "  " + dateOfBird);
        textViewQualifiedTime.setText(qualifTimeAsString);

        TextView tvAllLaps = (TextView) rowView.findViewById(R.id.id_textview_all_laps);
        tvAllLaps.setTag("TV_all_" + position);

        TextView tvLapLast = (TextView) rowView.findViewById(R.id.id_textview_last);
        tvLapLast.setTag("TV_last_" + position);

        if (chronoIsStarted) {
            buttonLap.setVisibility(View.VISIBLE);
            buttonLapReset.setVisibility(View.INVISIBLE);
            buttonLapRecord.setVisibility((View.INVISIBLE));
        } else {
            buttonLap.setVisibility(View.INVISIBLE);
            buttonLapReset.setVisibility(View.VISIBLE);
            buttonLapRecord.setVisibility((View.VISIBLE));
        }

        if (resultList.get(position).trueIfSomeLapsAreAlreadyTaken()) {
            ArrayList<String> listOfLineToInsertInAllLaps = resultList.get(position).giveBackLapsToInsertInTextViewAllLaps();
            String stringToInsert = null;
            for (int indexLap = 0; indexLap < listOfLineToInsertInAllLaps.size(); indexLap++) {
                stringToInsert += listOfLineToInsertInAllLaps.get(indexLap);
            }
            tvAllLaps.setText(stringToInsert);
            tvLapLast.setText(resultList.get(position).giveBackLastLap());

        }
        return rowView;
    }

}

