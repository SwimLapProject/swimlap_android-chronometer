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
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dim.swimlap.R;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.SwimmerModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentDataLap extends Fragment implements View.OnClickListener {

    private ListView listViewForLap;
    private BaseAdapter adapter;
    private List<View> viewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_lap, container, false);
        listViewForLap = (ListView) view.findViewById(R.id.id_listview_lap);
        LapAdapter adapter = new LapAdapter(this.getActivity(), buildEvent());
        listViewForLap.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    private ArrayList<DataLapForList> buildEvent() {
        ArrayList<DataLapForList> list = new ArrayList<DataLapForList>();
        for (int i = 0; i < 3; i++) {
            SwimmerModel swimmerModel = new SwimmerModel();
            swimmerModel.setName("Name" + i);
            swimmerModel.setFirstname("FirstName" + i);
            swimmerModel.setIdSwimmer(888888);
            swimmerModel.setDateOfBirth(String.valueOf(1987+i));
            EventModel eventModel = new EventModel();
            eventModel.setQualifyingTime(new Float(125.63));
            eventModel.setRaceId(i+10000);
            DataLapForList dataLapForList = new DataLapForList(swimmerModel, eventModel);
            list.add(dataLapForList);
        }
        return list;
    }

    @Override
    public void onClick(View view) {
//        long lap = fragmentDirect.getMillisecondsLap();
//        float time = (float) lap / 1000;
//        String timeAsString = String.valueOf(time);
//        textView.append(timeAsString + "\n");
//    }
    }
}
