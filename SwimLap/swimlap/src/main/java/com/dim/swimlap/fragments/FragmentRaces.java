/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dim.swimlap.R;
import com.dim.swimlap.graphics.HorizontalScrollViewAdapter;

public class FragmentRaces extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_races, container);
        listView = (ListView) view.findViewById(R.id.id_fragmentraces_listview);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void addContentList(){

       //HorizontalScrollViewAdapter hsva = new HorizontalScrollViewAdapter(getActivity(),);

    }
}
