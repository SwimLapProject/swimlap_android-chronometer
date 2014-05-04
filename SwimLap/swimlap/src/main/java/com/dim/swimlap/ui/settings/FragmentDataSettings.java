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
import android.widget.TextView;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.db.getter.GetDataForSettings;
import com.dim.swimlap.db.recorder.RecordSettings;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.objects.DateTransformer;

import java.util.HashMap;

public class FragmentDataSettings extends Fragment implements View.OnClickListener {

    private DatePicker datePickerStart, datePickerStop;
    private Button buttonSeason, buttonClub;
    private EditText editTextclubName, editTextclubCodeffn, editTextSeasonName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_settings, container, false);
        // CLUB
        buttonClub = (Button) view.findViewById(R.id.id_button_modifyclub);
        buttonClub.setOnClickListener(this);
        editTextclubName = (EditText) view.findViewById(R.id.id_edittext_clubname);
        editTextclubCodeffn = (EditText) view.findViewById(R.id.id_edittext_club_codeffn);
        // SEASON
        buttonSeason = (Button) view.findViewById(R.id.id_button_modifyseason);
        buttonSeason.setOnClickListener(this);
        editTextSeasonName = (EditText) view.findViewById(R.id.id_edittext_seasonname);
        datePickerStart = (DatePicker) view.findViewById(R.id.id_datepicker_startdate);
        datePickerStop = (DatePicker) view.findViewById(R.id.id_datepicker_stopdate);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateDataInUI();
    }

    @Override
    public void onClick(View view) {
        RecordSettings recordSettings = new RecordSettings(getActivity());
        DateTransformer transformer = new DateTransformer();
        if (view.getId() == R.id.id_button_modifyclub) {
            int codeClubFFN = Integer.valueOf(editTextclubCodeffn.getText().toString());
            ClubModel clubModel = new ClubModel(0, codeClubFFN);
            clubModel.setName(editTextclubName.getText().toString());
            recordSettings.recordClub(clubModel);
            Toast.makeText(view.getContext(), "Club Recorded", Toast.LENGTH_SHORT).show();


        } else if (view.getId() == R.id.id_button_modifyseason) {

            String dateStart = transformer.getDateAsStringWithFFNexFormat(datePickerStart.getYear(), datePickerStart.getMonth(), datePickerStart.getDayOfMonth());
            String dateStop = transformer.getDateAsStringWithFFNexFormat(datePickerStop.getYear(), datePickerStop.getMonth(), datePickerStop.getDayOfMonth());
            SeasonModel seasonModel = new SeasonModel(dateStart);
            seasonModel.setName(editTextSeasonName.getText().toString());
            seasonModel.setStartDate(dateStart);
            seasonModel.setStopDate(dateStop);
            recordSettings.recordSeason(seasonModel);
            Toast.makeText(view.getContext(), "Season Recorded", Toast.LENGTH_SHORT).show();

        }
    }

    public String getDateFromPicker(DatePicker picker) {
        return picker.getYear() + "-" + picker.getMonth() + "-" + picker.getDayOfMonth();
    }

    public void updateDataInUI() {
        DateTransformer transformer = new DateTransformer();
        GetDataForSettings getter = new GetDataForSettings(getActivity());

        ClubModel club = getter.getClubRecordedInDb();
        editTextclubName.setText(club.getName(), TextView.BufferType.EDITABLE);
        editTextclubCodeffn.setText(String.valueOf(club.getCodeFFN()),TextView.BufferType.EDITABLE);

        SeasonModel season = getter.getCurrentSeasonInDb();
        editTextSeasonName.setText(season.getName(),TextView.BufferType.EDITABLE);
        HashMap<String, Integer> start = transformer.getDateAsMap(season.getStartDate());
        datePickerStart.updateDate(start.get("year"), start.get("month"), start.get("day"));
        HashMap<String, Integer> stop = transformer.getDateAsMap(season.getStopDate());
        datePickerStop.updateDate(stop.get("year"), stop.get("month"), stop.get("day"));
    }


}
