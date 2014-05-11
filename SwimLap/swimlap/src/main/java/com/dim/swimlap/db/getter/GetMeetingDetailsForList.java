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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetMeetingDetailsForList {
    private Context context;
    private ArrayList<MeetingModel> meetings;

    public GetMeetingDetailsForList(Context context) {
        this.context = context;
    }

    public ArrayList<MeetingModel> getAllMeetings() {
        // GET DATE OF THE DAY TO KNOW IF A MEETING EXIST
        Date today = new Date();
        DbUtilitiesBuilder db1 = new DbUtilitiesBuilder(context);
        try {
            db1.open();
            ClubModel club = db1.getClubUtilities().getClub_FromDb();
            if (club == null) {
                // do nothing Toast in caller please
            } else {
                meetings = db1.getMeetingUtilities().getAllMeetings_FromDb();
                for(int indexMeeting =0; indexMeeting<meetings.size();indexMeeting++){

                    fillMeetingWithResult(meetings.get(indexMeeting));
                    fillMeetingWithSeason(meetings.get(indexMeeting));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();
        }

        return meetings;
    }

    private void fillMeetingWithResult(MeetingModel meetingToFill) {
        List<ResultModel> resultsInDB = new ArrayList<ResultModel>();
        DbUtilitiesBuilder db2 = new DbUtilitiesBuilder(context);

        try {
            db2.open();
            resultsInDB = db2.getResultUtilities().getAllResultsByTimeOrderByMeeting_FromDb(meetingToFill.getId());

            /** FOR EACH RESULT IN DB OF MEETING **/
            for (int indexResult = 0; indexResult < resultsInDB.size(); indexResult++) {
                ResultModel resultToFillThenToAdd = resultsInDB.get(indexResult);

                /** TEAM **/
                if (resultToFillThenToAdd.isRelay()) {
                    for (int indexSwimmer = 0; indexSwimmer < resultToFillThenToAdd.getTeam().size(); indexSwimmer++) {
                        int idSwimmerToFill = resultToFillThenToAdd.getTeam().get(indexSwimmer).getIdFFN();
                        resultToFillThenToAdd.getTeam().add(fillSwimmerWithClub(idSwimmerToFill,meetingToFill));
                    }
                } else {
                    /** SWIMMER **/
                    int idSwimmerToFill = resultToFillThenToAdd.getSwimmerModel().getIdFFN();
                    resultToFillThenToAdd.setSwimmerModel(fillSwimmerWithClub(idSwimmerToFill,meetingToFill));
                }
                /** EVENT **/
                int idEvent = resultToFillThenToAdd.getEventModel().getId();
                EventModel eventTofill = resultToFillThenToAdd.getEventModel();
                EventModel eventFromDb = db2.getEventUtilities().getEventModel_FromDb(idEvent, meetingToFill.getId());

                /** ADD EVENT AND BUILD OTHER ATTRIBUTES **/
                float qualifyingTime = resultToFillThenToAdd.getQualifyingTime();
                int poolSize = meetingToFill.getPoolSize();
                int meetingId = meetingToFill.getId();
                resultToFillThenToAdd.buildContent(qualifyingTime, poolSize, meetingId, eventFromDb);

                /** ADD EACH RESULT GETTED FROM DB IN MEETING */
                meetingToFill.addResult(resultToFillThenToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db2.close();
        }
    }

    private SwimmerModel fillSwimmerWithClub(int idSwimmerToFind, MeetingModel meetingToFill) {
        DbUtilitiesBuilder db3 = new DbUtilitiesBuilder(context);
        SwimmerModel swimmerToFill = null;
        try {
            db3.open();
            swimmerToFill = db3.getSwimmerUtilities().getSwimmer_FromDb(idSwimmerToFind);
            int clubCodeFromMeeting = meetingToFill.getClubCode();
            ClubModel clubFromDb = db3.getClubUtilities().getClub_FromDb();
            if (clubFromDb.getCodeFFN() == clubCodeFromMeeting) {

                swimmerToFill.getClubModel().setName(clubFromDb.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db3.close();
        }
        return swimmerToFill;
    }

    private void fillMeetingWithSeason(MeetingModel meetingToFill) {
        SeasonModel seasonOfMeeting = meetingToFill.getSeasonModel();
        String startDateOfMeeting = meetingToFill.getStartDate();
        DbUtilitiesBuilder db4 = new DbUtilitiesBuilder(context);

        try {
            db4.open();
            seasonOfMeeting = db4.getSeasonUtilities().getSeason_FromDb(startDateOfMeeting);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db4.close();
        }
    }
}
