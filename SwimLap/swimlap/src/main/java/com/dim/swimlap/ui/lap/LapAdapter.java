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
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.objects.FormatTimeAsString;

import java.util.ArrayList;

public class LapAdapter extends ArrayAdapter {
    private final Context context;
    public final ArrayList<ResultModel> resultModelList;
    private FormatTimeAsString formatTime;
    private boolean chronoIsStarted;

    public LapAdapter(Context context, ArrayList<ResultModel> resultModelList, boolean chronoIsStarted) {
        super(context, R.layout.viewforlist_data_lap, resultModelList);
        this.context = context;
        this.resultModelList = resultModelList;
        this.formatTime = new FormatTimeAsString();
        this.chronoIsStarted = chronoIsStarted;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.viewforlist_data_lap, parent, false);

            viewHolder = new ViewHolder();
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        // BUILD ELEMENT OF ROW

        // BUTTON LAP
        viewHolder.buttonTakeLap = (Button) rowView.findViewById(R.id.id_button_take_lap);
        viewHolder.buttonTakeLap.setTag("lap_" + position);
        viewHolder.buttonTakeLap.setBackgroundResource(R.drawable.button_basic_with_shadow);

        //BUTTON RECORD
        viewHolder.buttonRecordLap = (Button) rowView.findViewById(R.id.id_button_record_lap);
        viewHolder.buttonRecordLap.setTag("rec_" + position);
        viewHolder.buttonRecordLap.setBackgroundResource(R.drawable.button_basic_with_shadow);

        //BUTTON RESET
        viewHolder.buttonResetLap = (Button) rowView.findViewById(R.id.id_button_reset_lap);
        viewHolder.buttonResetLap.setTag("res_" + position);
        viewHolder.buttonResetLap.setBackgroundResource(R.drawable.button_basic_with_shadow);


        // TEXT VIEW
        viewHolder.textViewSwimmerDetails = (TextView) rowView.findViewById(R.id.id_textview_lap_swimmerdetails);
        viewHolder.textViewQualifiedTime = (TextView) rowView.findViewById(R.id.id_textview_qualifiedTime);


        // FILL ELEMENT WITH DATA FROM DB
        String name = resultModelList.get(position).getSwimmerModel().getName();
        String firstName = resultModelList.get(position).getSwimmerModel().getFirstname();
        String yearOfBirth = resultModelList.get(position).getSwimmerModel().getDateOfBirth().substring(0, 4);
        float qualifyingTime = resultModelList.get(position).getQualifyingTime();
        String qualifTimeAsString = formatTime.makeString(qualifyingTime);

        viewHolder.textViewSwimmerDetails.setText(name + "  " + firstName + "  " + yearOfBirth);
        viewHolder.textViewQualifiedTime.setText(qualifTimeAsString);

        viewHolder.textViewAllLaps = (TextView) rowView.findViewById(R.id.id_textview_all_laps);
        viewHolder.textViewAllLaps.setTag("TV_all_" + position);

        viewHolder.textViewLastLap = (TextView) rowView.findViewById(R.id.id_textview_last);
        viewHolder.textViewLastLap.setTag("TV_last_" + position);

        if (chronoIsStarted) {
            viewHolder.buttonTakeLap.setVisibility(View.VISIBLE);
            viewHolder.buttonResetLap.setVisibility(View.INVISIBLE);
            viewHolder.buttonRecordLap.setVisibility((View.INVISIBLE));
        } else {
            viewHolder.buttonTakeLap.setVisibility(View.INVISIBLE);
            viewHolder.buttonResetLap.setVisibility(View.VISIBLE);
            viewHolder.buttonRecordLap.setVisibility((View.VISIBLE));
        }

        if (resultModelList.get(position).trueIfSomeLapsAreAlreadyTaken()) {
            ArrayList<String> listOfLineToInsertInAllLaps = resultModelList.get(position).giveBackLapsToInsertInTextViewAllLaps();
            String stringToInsert = null;
            for (int indexLap = 0; indexLap < listOfLineToInsertInAllLaps.size(); indexLap++) {
                stringToInsert += listOfLineToInsertInAllLaps.get(indexLap);
            }
            viewHolder.textViewAllLaps.setText(stringToInsert);
            viewHolder.textViewLastLap.setText(resultModelList.get(position).giveBackLastLap());
        }

        rowView.setTag(viewHolder);

        return rowView;
    }


    private static class ViewHolder {
        public static Button buttonTakeLap, buttonResetLap, buttonRecordLap;
        public static TextView textViewSwimmerDetails, textViewQualifiedTime, textViewAllLaps, textViewLastLap;

    }

}

