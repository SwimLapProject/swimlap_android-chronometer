package com.dim.swimlap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.dim.swimlap.fragments.FragementLaps;
import com.dim.swimlap.fragments.FragmentChrono;
import com.dim.swimlap.fragments.FragmentRaces;


public class GlobalContainer extends Activity implements AdapterView.OnItemClickListener{

    private FragmentChrono fragmentChrono;
    private FragmentRaces fragmentRaces;
    private FragementLaps fragementLaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_container);

        fragmentChrono = (FragmentChrono) getFragmentManager().findFragmentById(R.id.id_globalcontainer_fragmentchrono);
        fragmentRaces = (FragmentRaces) getFragmentManager().findFragmentById(R.id.id_globalcontainer_fragmentraces);

        insertLapsFragment();
    }

    protected void insertLapsFragment() {
        fragementLaps = (FragementLaps) getFragmentManager().findFragmentById(R.id.id_globalcontainer_fragmentcontent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
