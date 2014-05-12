/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.meeting.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;
import java.util.HashMap;

public class SwimmersInMeetingAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<SwimmerModel> swimmers;
    private HashMap<Integer, String> racesBySwimmers;

    public SwimmersInMeetingAdapter(Context context, ArrayList<SwimmerModel> swimmers, HashMap<Integer, String> racesBySwimmers) {
        super(context, R.layout.viewforlist_details_meeting_swimmers, swimmers);
        this.context = context;
        this.swimmers = swimmers;
        this.racesBySwimmers = racesBySwimmers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.viewforlist_details_meeting_swimmers, parent, false);

            viewHolder = new ViewHolder();
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        String name = swimmers.get(position).getName();
        String firstName = swimmers.get(position).getFirstname();
        String allRacesBySwimmer = racesBySwimmers.get(swimmers.get(position).getIdFFN());

        viewHolder.tvName = (TextView) rowView.findViewById(R.id.id_textview_meeting_details_swimmerlist_name);
        viewHolder.tvName.setText(name);
        viewHolder.tvFirstName = (TextView) rowView.findViewById(R.id.id_textview_meeting_details_swimmerlist_firstname);
        viewHolder.tvFirstName.setText(firstName);
        viewHolder.tvRaces = (TextView) rowView.findViewById(R.id.id_textview_meeting_details_swimmerlist_races);
        viewHolder.tvRaces.setText(allRacesBySwimmer);

        rowView.setTag(viewHolder);

        return rowView;
    }

    private static class ViewHolder {
        public static TextView tvName, tvFirstName, tvRaces;
    }
}