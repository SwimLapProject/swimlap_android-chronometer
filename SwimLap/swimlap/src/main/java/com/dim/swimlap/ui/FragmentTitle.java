/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dim.swimlap.R;

;

public class FragmentTitle extends Fragment implements View.OnClickListener {
    private TextView textViewTitle;
    private ImageView imageViewLogo;
    private Button buttonLink;
    private CommunicationFragments communication;
    private int VIEW_RANKING = 6;
    private String title = "";

    public FragmentTitle(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title_of_all, container, false);
        imageViewLogo = (ImageView) view.findViewById(R.id.id_imageview_logo_swimlap);
        textViewTitle = (TextView) view.findViewById(R.id.id_textview_title);
        textViewTitle.setText(this.title);
        textViewTitle.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.title_moving));

        buttonLink = (Button) view.findViewById(R.id.id_button_link);
        buttonLink.setOnClickListener(this);
        communication = (CommunicationFragments) this.getActivity();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        communication.changeFragment(VIEW_RANKING);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        textViewTitle.setText(this.title);

    }
}