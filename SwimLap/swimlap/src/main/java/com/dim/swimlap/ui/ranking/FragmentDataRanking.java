/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.ranking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.dim.swimlap.R;
import com.dim.swimlap.ui.ranking.level.FirstChild;

;

public class FragmentDataRanking extends Fragment {

    private ExpandableListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_ranking, container, false);

        listView = (ExpandableListView) view.findViewById(R.id.id_expandable_listview);
        FirstChild firstChild = new FirstChild(getActivity());
        listView.setAdapter(firstChild);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
