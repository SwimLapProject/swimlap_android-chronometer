/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.db.getter;

import android.content.Context;

import com.dim.swimlap.db.builder.DbUtilitiesBuilder;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.models.SwimmerModel;

import java.util.Date;
import java.util.List;

public class GetResultsForLapView {

    private DbUtilitiesBuilder db;
    private MeetingModel meetingOfTheDay;

    public GetResultsForLapView(Context context) {
        db = new DbUtilitiesBuilder(context);
    }

    public MeetingModel getMeetingOfTheDay() {
        // GET DATE OF THE DAY TO KNOW IF A MEETING EXIST
        Date today = new Date();
        meetingOfTheDay = db.getMeetingUtilities().getMeetingWithDates(today);
        fillMeetingWithResult();
        fillMeetingWithSeason();
        return meetingOfTheDay;
    }

    private void fillMeetingWithResult() {
        List<ResultModel> resultsInDB = db.getResultUtilities().getAllResultsFromDb_ByMeeting(meetingOfTheDay.getId());
        /** FOR EACH RESULT IN DB OF MEETING **/
        for (int indexResult = 0; indexResult < resultsInDB.size(); indexResult++) {
            ResultModel resultToFillThenToAdd = resultsInDB.get(indexResult);
            /** TEAM **/
            if (resultToFillThenToAdd.isRelay()) {
                for (int indexSwimmer = 0; indexSwimmer < resultToFillThenToAdd.getTeam().size(); indexSwimmer++) {
                    SwimmerModel swimmerToFill = resultToFillThenToAdd.getTeam().get(indexSwimmer);
                    fillSwimmerWithClub(swimmerToFill);
                }
            } else {
                /** SWIMMER **/
                SwimmerModel swimmerToFill = resultToFillThenToAdd.getSwimmerModel();
                fillSwimmerWithClub(swimmerToFill);
            }
            /** EVENT **/
            int idEvent = resultToFillThenToAdd.getEventModel().getId();
            EventModel eventTofill = resultToFillThenToAdd.getEventModel();
            EventModel eventFromDb = db.getEventUtilities().getEventModel_FromDb(idEvent, meetingOfTheDay.getId());
            eventTofill = eventFromDb;

            /** ADD EACH RESULT GETTED FROM DB IN MEETING */
            meetingOfTheDay.addResult(resultToFillThenToAdd);
        }
    }

    private void fillSwimmerWithClub(SwimmerModel swimmerToFill) {
        int idSwimmerToFind = swimmerToFill.getIdFFN();
        swimmerToFill = db.getSwimmerUtilities().getSwimmer_FromDb(idSwimmerToFind);

        int clubCodeFromMeeting = swimmerToFill.getClubModel().getCodeFFN();
        ClubModel clubFromDb = db.getClubUtilities().getClub_FromDb();
        if (clubFromDb.getCodeFFN() == clubCodeFromMeeting) {
            swimmerToFill.getClubModel().setName(clubFromDb.getName());
        }
    }

    private void fillMeetingWithSeason() {
        SeasonModel seasonOfMeeting = meetingOfTheDay.getSeasonModel();
        String startDateOfMeeting = meetingOfTheDay.getStartDate();
        seasonOfMeeting = db.getSeasonUtilities().getSeason_FromDb(startDateOfMeeting);
    }
}
