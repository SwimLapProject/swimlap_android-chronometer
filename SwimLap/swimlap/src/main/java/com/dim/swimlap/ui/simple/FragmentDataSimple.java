/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.objects.SimpleChronoDataBuilder;
import com.dim.swimlap.objects.Singleton;
import com.dim.swimlap.ui.lap.LapAdapter;
;import java.util.ArrayList;

public class FragmentDataSimple extends Fragment{

    private ListView listViewForLap;
    private TextView textViewNoMeetingInLap;
    private LapAdapter adapter;
    private int raceIdOfThisFragment;
    private SimpleChronoDataBuilder simple;
    private MeetingModel meeting;



    public FragmentDataSimple(int raceId,ClubModel club,int poolSize,boolean byTeam) {
        raceIdOfThisFragment = raceId;
        simple = new SimpleChronoDataBuilder(club,poolSize,byTeam);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_lap, container, false);
////        singleton = Singleton.getInstance();
////        boolean meetingOfTheDayIsBuilt = singleton.buildMeetingOfTheDay(getActivity());
//
//        listViewForLap = (ListView) view.findViewById(R.id.id_listview_lap);
//        textViewNoMeetingInLap = (TextView) view.findViewById(R.id.id_textview_no_meeting_in_lap);
//
//        if (!meetingOfTheDayIsBuilt) {
//            // this case appear when there no club indicate in settings
//            Toast.makeText(getActivity(), "You must complete settings please.", Toast.LENGTH_SHORT).show();
//            textViewNoMeetingInLap.setVisibility(View.VISIBLE);
//        } else {
//            textViewNoMeetingInLap.setVisibility(View.INVISIBLE);
//            ArrayList<ResultModel> resultsToDisplay = singleton.getResultsByRace(raceIdOfThisFragment);
//            adapter = new LapAdapter(this.getActivity(), resultsToDisplay);
//            listViewForLap.setAdapter(adapter);
//        }
        return view;
    }

    public void addLapToModel(View view, float lapInMilli) {
//        String[] tag = String.valueOf(view.getTag()).split("_");
//        int resultId = Integer.valueOf(tag[1]);
//        ResultModel resultInSingleton = singleton.getResultOfTheDay(resultId);
//
//        int nbSplitRemaining = resultInSingleton.getnbSplitRemaining();
//        if (nbSplitRemaining > 0) {
//            float lapDiff = resultInSingleton.checkLap(lapInMilli);
//            if (nbSplitRemaining == 1) {
//                resultInSingleton.setSwimTime(lapInMilli);
//            }
//            adapter.notifyDataSetChanged();
//        } else {
//            Toast.makeText(view.getContext(), "All laps taken !", Toast.LENGTH_SHORT).show();
//        }
    }

    public void unLapLast(View view) {
//        String[] tag = String.valueOf(view.getTag()).split("_");
//        int resultId = Integer.valueOf(tag[1]);
//        ResultModel resultInSingleton = singleton.getResultOfTheDay(resultId);
//        resultInSingleton.removeOneWhenUnLap();
//        adapter.notifyDataSetChanged();
    }

    public void changeButtonLap() {
//        adapter.notifyDataSetChanged();
    }


    public void resetLaps(View view) {
//        String[] tag = String.valueOf(view.getTag()).split("_");
//        int resultId = Integer.valueOf(tag[1]);
//        singleton.getResultOfTheDay(resultId).resetLaps();
//        adapter.notifyDataSetChanged();
    }

    public void recordLaps(View view) {
//        String[] tag = String.valueOf(view.getTag()).split("_");
//        int resultId = Integer.valueOf(tag[1]);
//        if(singleton.getResultOfTheDay(resultId).getnbSplitRemaining()>0){
//            Toast.makeText(view.getContext(), "Some laps are missing.\nSwimLap will delete others.", Toast.LENGTH_SHORT).show();
//            resetLaps(view);
//        }else{
//            singleton.getResultOfTheDay(resultId).recordLapsInDB(getActivity());
//            adapter.notifyDataSetChanged();
//            Toast.makeText(getActivity(), "Laps has been recorded in database.", Toast.LENGTH_SHORT).show();
//        }

    }
}
