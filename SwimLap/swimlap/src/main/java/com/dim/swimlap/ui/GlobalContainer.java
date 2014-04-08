/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.dim.swimlap.R;

public class GlobalContainer extends Activity implements AdapterView.OnItemClickListener {

    private FragmentDirect fragmentDirect;
    private FragmentContent fragmentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_container);

        fragmentDirect = (FragmentDirect) getFragmentManager().findFragmentById(R.id.id_IN_fragment_direct);
        fragmentContent = (FragmentContent) getFragmentManager().findFragmentById(R.id.id_IN_fragment_content);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
