/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dim.swimlap.ui.GlobalContainer;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Thread splash = new Thread() {
            int logoTimer;
            public void run() {
                try {
                    logoTimer = 0;
                    while (logoTimer < 1000) {
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    finish();
                    logoTimer=0;
                    Intent UI = new Intent(getApplicationContext(), GlobalContainer.class);
                    UI.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(UI);
                }
            }
        };
        splash.start();
    }
}