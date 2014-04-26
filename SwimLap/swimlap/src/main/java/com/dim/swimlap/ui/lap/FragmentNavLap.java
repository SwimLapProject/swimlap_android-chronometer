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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dim.swimlap.R;
;

public class FragmentNavLap extends Fragment implements View.OnClickListener {
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_lap, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.id_linearlayout_in_horizontalscrollview);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 1; i < 20; i++) {
            Button button = new Button(this.getActivity());
            button.setText("button" + i);
            button.setTag("button" + i);
            button.setOnClickListener(this);
            if (i == 3) {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_race_selected));
            } else {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_race));

            }
            linearLayout.addView(button);
        }
    }


    @Override
    public void onClick(View view) {
        String b = (String) view.getTag();
        Toast.makeText(view.getContext(), b, Toast.LENGTH_SHORT).show();

    }
}
