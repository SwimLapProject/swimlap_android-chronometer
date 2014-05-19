/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.swimmer.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.SwimmerModel;
import com.dim.swimlap.parser.FFNexMaker;

public class FragmentNavSwimmerDetails  extends Fragment implements View.OnClickListener{

    private Button buttonExportFFNexRecord;
    private SwimmerModel swimmer;

    public FragmentNavSwimmerDetails(SwimmerModel swimmer) {
        this.swimmer = swimmer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_model, container, false);
        buttonExportFFNexRecord = (Button) view.findViewById(R.id.id_button_nav_model);
        buttonExportFFNexRecord.setText("Export records");
        buttonExportFFNexRecord.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "DO SOMETHING", Toast.LENGTH_SHORT).show();


    }
}
