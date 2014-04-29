/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.dim.swimlap.R;

;

public class FragmentDataSettings extends Fragment implements View.OnClickListener {

    private DatePicker datePickerStart, datePickerStop;
    private Button buttonSeason, buttonClub;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_settings, container, false);
        datePickerStart = (DatePicker) view.findViewById(R.id.id_datepicker_startdate);
        datePickerStop = (DatePicker) view.findViewById(R.id.id_datepicker_stopdate);
        buttonClub = (Button) view.findViewById(R.id.id_button_modifyclub);
        buttonClub.setOnClickListener(this);
        buttonSeason = (Button) view.findViewById(R.id.id_button_modifyseason);
        buttonSeason.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_button_modifyclub) {

        } else if (view.getId() == R.id.id_button_modifyseason) {

        }
    }


}
