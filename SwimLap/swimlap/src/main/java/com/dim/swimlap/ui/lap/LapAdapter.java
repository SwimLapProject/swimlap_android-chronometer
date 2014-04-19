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
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.ui.CommunicationFragments;
import com.dim.swimlap.ui.FragmentDirect;

import java.util.ArrayList;

public class LapAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<DataLapForList> eventArrayList;

    public LapAdapter(Context context, ArrayList<DataLapForList> eventArrayList) {
        super(context, R.layout.viewforlist_data_lap, eventArrayList);
        this.context = context;
        this.eventArrayList = eventArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.viewforlist_data_lap, parent, false);


        // BUILD ELEMENT OF ROW

        // TEXT VIEW
        TextView textViewDetails = (TextView) rowView.findViewById(R.id.id_textview_lapdetails);
        // BUTTON LAP
        Button buttonLap = (Button) rowView.findViewById(R.id.id_button_lap);
        buttonLap.setVisibility(View.VISIBLE);
        buttonLap.setTag("lap_" + String.valueOf(eventArrayList.get(position).getEventModel().getRaceId()));
        buttonLap.setBackgroundResource(R.drawable.button_basic_with_shadow);
        buttonLap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getLap(view);
            }
        });
        //BUTTON RECORD
        Button buttonLapRecord = (Button) rowView.findViewById(R.id.id_button_record_lap);
        buttonLapRecord.setVisibility(View.INVISIBLE);
        buttonLapRecord.setTag("record_" + String.valueOf(eventArrayList.get(position).getEventModel().getRaceId()));
        buttonLapRecord.setBackgroundResource(R.drawable.button_basic_with_shadow);
        buttonLapRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getLap(view);
            }
        });
        //BUTTON RESET
        Button buttonLapReset = (Button) rowView.findViewById(R.id.id_button_reset_lap);
        buttonLapReset.setVisibility(View.INVISIBLE);
        buttonLapReset.setTag("reset_"+String.valueOf(eventArrayList.get(position).getEventModel().getRaceId()));
        buttonLapReset.setBackgroundResource(R.drawable.button_basic_with_shadow);
        buttonLapReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getLap(view);
            }
        });
        // FILL ELEMENT WITH DATA FROM DB
        String name = eventArrayList.get(position).getSwimmerModel().getName();
        String firstName = eventArrayList.get(position).getSwimmerModel().getFirstname();
        String dateOfBird = eventArrayList.get(position).getSwimmerModel().getDateOfBirth();
        String qualifyingTime = String.valueOf(eventArrayList.get(position).getEventModel().getQualifyingTime());
        textViewDetails.setText(name + "  " + firstName + "  " + dateOfBird+" : "+qualifyingTime);

        return rowView;
    }

//    @Override
//    public void onClick(View view) {
//        String tag = (String) view.getTag();
//        String type = tag.substring(0,4);
//        if(type.equals("lap_")){
//
//        }
//    }
    public void getLap(View view){
        String tag = (String) view.getTag();
        String type = tag.substring(0,4);
        String eventCode = tag.substring(4);
        if(type.equals("lap_")){
            Toast.makeText(view.getContext(),tag, Toast.LENGTH_SHORT).show();

        }
//    }
    }
}

