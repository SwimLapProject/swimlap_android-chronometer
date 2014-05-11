/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.swimmer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dim.swimlap.R;
;

public class FragmentTitleSwimmer extends Fragment implements View.OnClickListener {
    private TextView textViewTitle;
    private ImageView imageViewLogo;
    private Button buttonLink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title_swimmer, container, false);

        imageViewLogo = (ImageView) view.findViewById(R.id.id_imageview_logo_swimlap);

        textViewTitle = (TextView) view.findViewById(R.id.id_textview_title);

        buttonLink = (Button) view.findViewById(R.id.id_button_link);
        buttonLink.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
