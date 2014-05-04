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
import com.dim.swimlap.objects.SimpleChronometerBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
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
        try {
            db.open();
            meetingOfTheDay = db.getMeetingUtilities().getMeetingWithDates(today);
            if (meetingOfTheDay == null) {
                ClubModel club = db.getClubUtilities().getClub_FromDb();
                SimpleChronometerBuilder simpleBuilder = new SimpleChronometerBuilder(club, 25, false);
                meetingOfTheDay = simpleBuilder.getBuiltMeetingForSimple();
            } else {
                fillMeetingWithResult();
                fillMeetingWithSeason();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return meetingOfTheDay;
    }

    private void fillMeetingWithResult() {
        List<ResultModel> resultsInDB = new ArrayList<ResultModel>();
        try {
            db.open();
            resultsInDB = db.getResultUtilities().getAllResultsFromDb_ByMeeting(meetingOfTheDay.getId());

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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    private void fillSwimmerWithClub(SwimmerModel swimmerToFill) {
        int idSwimmerToFind = swimmerToFill.getIdFFN();
        try {
            db.open();
            swimmerToFill = db.getSwimmerUtilities().getSwimmer_FromDb(idSwimmerToFind);

            int clubCodeFromMeeting = swimmerToFill.getClubModel().getCodeFFN();
            ClubModel clubFromDb = db.getClubUtilities().getClub_FromDb();
            if (clubFromDb.getCodeFFN() == clubCodeFromMeeting) {
                swimmerToFill.getClubModel().setName(clubFromDb.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    private void fillMeetingWithSeason() {
        SeasonModel seasonOfMeeting = meetingOfTheDay.getSeasonModel();
        String startDateOfMeeting = meetingOfTheDay.getStartDate();
        try {
            db.open();
            seasonOfMeeting = db.getSeasonUtilities().getSeason_FromDb(startDateOfMeeting);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
