/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.objects;

import android.content.Context;

import com.dim.swimlap.db.getter.GetResultsForLapView;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;

public final class Singleton {

    private static final String FFNEX_DATE_FORMAT = "yyyy-MM-dd";

    private static volatile Singleton instance = null;
    private MeetingModel meetingModel;
    private boolean listIsAlreadyBuilt;

    private Singleton() {
        super();
    }

    public final static Singleton getInstance() {
        if (Singleton.instance == null) {
            synchronized (Singleton.class) {
                if (Singleton.instance == null) {
                    Singleton.instance = new Singleton();
                }
            }
        }
        return Singleton.instance;
    }

    public MeetingModel buildEvent(Context context) {
        if (meetingModel != null) {
            return meetingModel;
        } else {
            GetResultsForLapView getter = new GetResultsForLapView(context);
            meetingModel = getter.getMeetingOfTheDay();
            return meetingModel;
        }
    }

    public ResultModel getResultOfTheDay(int eventPosition) {
        return meetingModel.getAllResults().get(eventPosition);
    }


}

