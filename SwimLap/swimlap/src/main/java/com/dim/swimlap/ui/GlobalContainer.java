/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.ui.lap.FragmentDataLap;
import com.dim.swimlap.ui.lap.FragmentNavLap;
import com.dim.swimlap.ui.lap.FragmentTitleLap;
import com.dim.swimlap.ui.menu.FragmentDataMenu;
import com.dim.swimlap.ui.menu.FragmentNavMenu;
import com.dim.swimlap.ui.menu.FragmentTitleMenu;
import com.dim.swimlap.ui.settings.FragmentDataSettings;
import com.dim.swimlap.ui.settings.FragmentNavSettings;
import com.dim.swimlap.ui.settings.FragmentTitleSettings;

public class GlobalContainer extends FragmentActivity implements CommunicationFragments {

    private FragmentTransaction transaction;
    private FragmentDirect fragmentDirect;

    private FragmentTitleMenu fragmentTitleMenu;
    private FragmentNavMenu fragmentNavMenu;
    private FragmentDataMenu fragmentDataMenu;

    private FragmentTitleSettings fragmentTitleSettings;
    private FragmentNavSettings fragmentNavSettings;
    private FragmentDataSettings fragmentDataSettings;

    private FragmentTitleLap fragmentTitleLap;
    private FragmentNavLap fragmentNavLap;
    private FragmentDataLap fragmentDataLap;

    private int whichViewIsUsed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_container);
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        fragmentDirect = new FragmentDirect();
        fragmentTitleMenu = new FragmentTitleMenu();
        fragmentNavMenu = new FragmentNavMenu();
        fragmentDataMenu = new FragmentDataMenu();

        fragmentTitleSettings = new FragmentTitleSettings();
        fragmentNavSettings = new FragmentNavSettings();
        fragmentDataSettings = new FragmentDataSettings();

        fragmentTitleLap = new FragmentTitleLap();
        fragmentNavLap = new FragmentNavLap();
        fragmentDataLap = new FragmentDataLap();

        transaction.add(R.id.id_IN_fragment_direct, fragmentDirect);
        transaction.add(R.id.id_IN_fragment_title, fragmentTitleMenu);
        transaction.add(R.id.id_IN_fragment_nav, fragmentNavMenu);
        transaction.add(R.id.id_IN_fragment_data, fragmentDataMenu);
        whichViewIsUsed = 0;
        transaction.commit();

    }

    @Override
    public void changeFragment(int integerCodeView) {
        Toast.makeText(this.getApplicationContext(), "CONTAINER", Toast.LENGTH_SHORT).show();
        if (integerCodeView != whichViewIsUsed) {
            whichViewIsUsed = integerCodeView;
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction newTransaction = manager.beginTransaction();
            switch (whichViewIsUsed) {
                case 0: // menu
                    newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleMenu);
                    newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavMenu);
                    newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSettings);
                    break;
                case 1: //lap
                    newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleLap);
                    newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavLap);
                    newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataLap);
                    break;
                case 2: // simple chronometer
//                    newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSettings);
//                    newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSettings);
//                    newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSettings);
                    break;
                case 3: // meetings
//                    newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSettings);
//                    newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSettings);
//                    newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSettings);
                    break;
                case 4: // swimmers
//                    newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSettings);
//                    newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSettings);
//                    newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSettings);
                    break;
                case 5:
                    newTransaction.replace(R.id.id_IN_fragment_title, fragmentTitleSettings);
                    newTransaction.replace(R.id.id_IN_fragment_nav, fragmentNavSettings);
                    newTransaction.replace(R.id.id_IN_fragment_data, fragmentDataSettings);
                    break;
                default:
                    break;
            }
            newTransaction.addToBackStack(null);
            newTransaction.commit();
        }
    }
}
