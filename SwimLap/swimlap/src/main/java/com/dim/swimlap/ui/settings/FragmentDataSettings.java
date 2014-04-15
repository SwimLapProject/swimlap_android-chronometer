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
import android.widget.DatePicker;

import com.dim.swimlap.R;

public class FragmentDataSettings extends Fragment {

    private DatePicker datePickerStart, datePickerStop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_settings, container, false);
        datePickerStart = (DatePicker) view.findViewById(R.id.id_datepicker_startdate);
        datePickerStop = (DatePicker) view.findViewById(R.id.id_datepicker_stopdate);

        return view;
    }
}
