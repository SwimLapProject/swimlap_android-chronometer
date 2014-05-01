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
import android.widget.EditText;

import com.dim.swimlap.R;
import com.dim.swimlap.db.recorder.RecordSettings;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.SeasonModel;

public class FragmentDataSettings extends Fragment implements View.OnClickListener {

    private DatePicker datePickerStart, datePickerStop;
    private Button buttonSeason, buttonClub;
    private EditText clubName, clubCodeffn, seasonName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_settings, container, false);
        // CLUB
        buttonClub = (Button) view.findViewById(R.id.id_button_modifyclub);
        buttonClub.setOnClickListener(this);
        clubName = (EditText) view.findViewById(R.id.id_edittext_clubname);
        clubCodeffn = (EditText) view.findViewById(R.id.id_edittext_club_codeffn);
        // SEASON
        buttonSeason = (Button) view.findViewById(R.id.id_button_modifyseason);
        buttonSeason.setOnClickListener(this);
        seasonName = (EditText) view.findViewById(R.id.id_edittext_seasonname);
        datePickerStart = (DatePicker) view.findViewById(R.id.id_datepicker_startdate);
        datePickerStop = (DatePicker) view.findViewById(R.id.id_datepicker_stopdate);

        return view;
    }


    @Override
    public void onClick(View view) {
        RecordSettings recordSettings = new RecordSettings(getActivity());

        if (view.getId() == R.id.id_button_modifyclub) {
            ClubModel clubModel = new ClubModel(0);
            clubModel.setName(clubName.getText().toString());
            int codeClubFFN = Integer.valueOf(clubCodeffn.getText().toString());
            clubModel.setCodeFFN(codeClubFFN);
            recordSettings.recordClub(clubModel);
        } else if (view.getId() == R.id.id_button_modifyseason) {
            SeasonModel seasonModel = new SeasonModel(0);
            seasonModel.setName(seasonName.getText().toString());
            String dateStart = getDateFromPicker(datePickerStart);
            seasonModel.setStartDate(dateStart);
            String dateStop = getDateFromPicker(datePickerStop);
            seasonModel.setStopDate(dateStop);
            recordSettings.recordSeason(seasonModel);
        }
    }

    public String getDateFromPicker(DatePicker picker) {
        return picker.getYear() + "-" + picker.getMonth() + "-" + picker.getDayOfMonth();
    }


}
