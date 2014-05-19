/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.meeting.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dim.swimlap.R;
import com.dim.swimlap.db.getter.GetMeetingDetailsForList;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.ui.CommunicationFragments;

import java.util.ArrayList;

;

public class FragmentDataMeetingList extends Fragment {

    private ListView listViewForMeetings;
    private TextView textViewNoMeetingInLap;
    private MeetingAdapter adapter;
    private ArrayList<MeetingModel> meetings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_model_list, container, false);
        final CommunicationFragments comm = (CommunicationFragments) this.getActivity();


        listViewForMeetings = (ListView) view.findViewById(R.id.id_listview_model);
        listViewForMeetings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                comm.replaceFragmentsMeetingToDetails(meetings.get(position));
            }
        });

        textViewNoMeetingInLap = (TextView) view.findViewById(R.id.id_textview_no_model_in_db);

        GetMeetingDetailsForList getter = new GetMeetingDetailsForList(getActivity());
        meetings = getter.getAllMeetings();


        if (meetings==null) {
            textViewNoMeetingInLap.setVisibility(View.VISIBLE);
        } else {
            textViewNoMeetingInLap.setVisibility(View.INVISIBLE);

            adapter = new MeetingAdapter(this.getActivity(), meetings);
            listViewForMeetings.setAdapter(adapter);

        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//        CommunicationFragments comm = (CommunicationFragments) this.getActivity();
//        comm.replaceFragmentsMeetingToDetails( meetings.get(position));
//    }

}
