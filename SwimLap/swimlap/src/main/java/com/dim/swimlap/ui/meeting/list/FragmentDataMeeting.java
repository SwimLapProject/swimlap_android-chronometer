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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.db.getter.GetMeetingDetailsForList;
import com.dim.swimlap.db.getter.GetSwimmerDetailsForList;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.SwimmerModel;
;import java.util.ArrayList;

public class FragmentDataMeeting extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener{

    private ListView listViewForMeetings;
    private TextView textViewNoMeetingInLap;
    private MeetingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_model_list, container, false);


        listViewForMeetings = (ListView) view.findViewById(R.id.id_listview_model);
        listViewForMeetings.setOnScrollListener(this);

        textViewNoMeetingInLap = (TextView) view.findViewById(R.id.id_textview_no_model_in_db);

        GetMeetingDetailsForList getter = new GetMeetingDetailsForList(getActivity());
        ArrayList<MeetingModel> meetings = getter.getAllMeetings();


        if (meetings.size() == 0) {
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(view.getContext(), "swimmer", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        // do nothing
    }

    @Override
    public void onScroll(AbsListView absListView, int first, int count, int total) {

    }
}
