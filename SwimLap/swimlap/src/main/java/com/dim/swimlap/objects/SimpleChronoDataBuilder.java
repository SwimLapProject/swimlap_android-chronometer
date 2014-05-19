/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.objects;

import com.dim.swimlap.data.RaceData;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SeasonModel;
import com.dim.swimlap.models.SwimmerModel;

import java.util.List;

public class SimpleChronoDataBuilder {

    private MeetingModel meetingForSimple;
    private static final int DEFAULT_ID = 888888;
    private static final String DEFAULT_STRING = "Simple";
    private static final String DEFAULT_DATE = "1901-01-01";
    private static final float DEFAULT_TIME = 60000; //1 minute

    private ClubModel club;


    public SimpleChronoDataBuilder(ClubModel clubModel, int poolSize, boolean byTeam) {
        this.club = clubModel;
        meetingForSimple = new MeetingModel();
        meetingForSimple.setId(DEFAULT_ID);
        meetingForSimple.setName(DEFAULT_STRING);
        meetingForSimple.setCity(DEFAULT_STRING);
        DateTransformer transformer = new DateTransformer();
        meetingForSimple.setStartDate(transformer.getTodayAsString());
        meetingForSimple.setStopDate(transformer.getTodayAsString());
        meetingForSimple.setSeasonModel(new SeasonModel(transformer.getTodayAsString()));
        meetingForSimple.setClubCode(club.getCodeFFN());
        meetingForSimple.setPoolSize(poolSize);
        meetingForSimple.setByTeam(byTeam);
        buildResults();
    }


    private void buildResults() {
        List<ResultModel> results = meetingForSimple.getAllResults();
        RaceData raceData = new RaceData();
        raceData.makeData();

        for (int indexRace = 1; indexRace < 250; indexRace++) {
            // IF RACE EXIST THEN CREATE ONE RESULT
            if (raceData.raceIdExists(indexRace)) {
                String gender = raceData.giveGender(indexRace);
                int nbRelayer = raceData.giveNbRelayer(indexRace);

                // RESULT BUILD
                ResultModel resultForEachKindOfRace = new ResultModel(indexRace);

                // SET EVENT
                EventModel eventDefaultByRace = new EventModel(indexRace, 60); // 60 is default round id and is heat
                // BUILD CONTENT MEAN  set poolSize, qualifyingTime, numberOfLap, currentLapSwimming,lapMin,lapMax
                // BUT NEED EVENT ALREADY SETTED !!
                resultForEachKindOfRace.buildContent(DEFAULT_TIME, meetingForSimple.getPoolSize(), meetingForSimple.getId(), eventDefaultByRace);

                // SET SWIMMER
                SwimmerModel swimmerDefault = new SwimmerModel(DEFAULT_ID);
                // CLUB OF SWIMMER
                swimmerDefault.setClubModel(this.club);

                swimmerDefault.setName(DEFAULT_STRING);
                swimmerDefault.setFirstname(DEFAULT_STRING);
                swimmerDefault.setDateOfBirth(DEFAULT_DATE);
                swimmerDefault.setGender(gender);
                if (nbRelayer > 1) {
                    resultForEachKindOfRace.setIsRelay(true);
                    for (int i = 0; i < nbRelayer; i++) {
                        resultForEachKindOfRace.addSwimmersInTeam(swimmerDefault);
                    }
                } else {
                    resultForEachKindOfRace.setIsRelay(false);
                    resultForEachKindOfRace.setSwimmerModel(swimmerDefault);
                }
                resultForEachKindOfRace.setSwimmerModel(swimmerDefault);

                // FINALLY ADD EACH RESULT
                results.add(resultForEachKindOfRace);
            }
        }
    }

    public MeetingModel getBuiltMeetingForSimple(){
        return meetingForSimple;
    }
}
