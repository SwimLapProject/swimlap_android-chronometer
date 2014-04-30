/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.parser;

import com.dim.swimlap.data.RaceData;
import com.dim.swimlap.models.ClubModel;
import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.RaceModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.RoundModel;
import com.dim.swimlap.models.SwimmerModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class FFNexParser {

    private MeetingModel meetingModel;
    private ArrayList<ClubModel> clubs;
    private ArrayList<EventModel> events;
    private ArrayList<SwimmerModel> swimmers;

    private int typeFFNEX;
    private static int
            FFNEX_TYPE_COMPETITION = 1,
            FFNEX_TYPE_ENGAGEMENT = 2,
            FFNEX_TYPE_RECORD = 3;

    public FFNexParser() {
        meetingModel = new MeetingModel();
        clubs = new ArrayList<ClubModel>();
        swimmers = new ArrayList<SwimmerModel>();
        events = new ArrayList<EventModel>();
    }

    public void parseIt(String ffnex) throws XmlPullParserException, IOException {

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(new StringReader(ffnex));
        int eventType = xpp.getEventType();

        int swimmerIdInResult = 0, resultIdInResult = 0, raceIdInResult = 0, roundIdInResult = 0;
        float qualifyingTimeInResult = 0;
        boolean isRelay = false;
        ArrayList<Integer> swIdInTeam = null;
        int nbRelayerIfRelay = 0;

        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_DOCUMENT) {
                System.out.println("$$$ Start document");

            } else if (eventType == XmlPullParser.START_TAG) {
//                System.out.println("$$$ Start tag " + xpp.getName());

                if (xpp.getName().equals("FFNEX")) {
//                    System.out.println("$$$ Start tag " + xpp.getName());
                    if (xpp.getAttributeValue(null, "type").equals("competition")) {
                        typeFFNEX = FFNEX_TYPE_COMPETITION;
                    } else if (xpp.getAttributeValue(null, "type").equals("engagement")) {
                        typeFFNEX = FFNEX_TYPE_ENGAGEMENT;
                    } else if (xpp.getAttributeValue(null, "type").equals("record")) {
                        typeFFNEX = FFNEX_TYPE_RECORD;
                    }

                } else if (xpp.getName().equals("MEET")) {
//                    System.out.println("$$$ Start tag " + xpp.getName());
                    meetingModel.setId(Integer.valueOf(xpp.getAttributeValue(null, "id")));
                    meetingModel.setName(xpp.getAttributeValue(null, "name"));
                    meetingModel.setStartDate(xpp.getAttributeValue(null, "startdate"));
                    meetingModel.setStopDate(xpp.getAttributeValue(null, "stopdate"));
                    meetingModel.setByTeam(Boolean.valueOf(xpp.getAttributeValue(null, "byteam")));

                } else if (xpp.getName().equals("POOL")) {
                    System.out.println("$$$ Start tag " + xpp.getName());
                    meetingModel.setPoolSize(Integer.valueOf(xpp.getAttributeValue(null, "size")));

                } else if (xpp.getName().equals("CLUB")) {
//                    System.out.println("$$$ Start tag " + xpp.getName());
                    ClubModel clubModel = new ClubModel(Integer.valueOf(xpp.getAttributeValue(null, "id")));
                    clubModel.setName(xpp.getAttributeValue(null, "name"));
                    clubModel.setCodeFFN(Integer.valueOf(xpp.getAttributeValue(null, "code")));
                    clubs.add(clubModel);

                } else if (xpp.getName().equals("SWIMMER")) {
//                    System.out.println("$$$ Start tag " + xpp.getName());
                    SwimmerModel swimmerModel = new SwimmerModel();
                    swimmerModel.setIdFFN(Integer.valueOf(xpp.getAttributeValue(null, "id")));
                    swimmerModel.setFirstname(xpp.getAttributeValue(null, "firstname"));
                    swimmerModel.setName(xpp.getAttributeValue(null, "lastname"));
                    swimmerModel.setGender(xpp.getAttributeValue(null, "gender"));
                    swimmerModel.setGender(xpp.getAttributeValue(null, "birthdate"));
                    int clubId = Integer.valueOf(xpp.getAttributeValue(null, "clubid"));
                    for (int i = 0; i < clubs.size(); i++) {
                        if (clubs.get(i).getId() == clubId) {
                            swimmerModel.setClubModel(clubs.get(i));
                        }
                    }
                    swimmers.add(swimmerModel);

                } else if (xpp.getName().equals("EVENT") && xpp.getAttributeValue(null, "type").equals("RACE")) {
//                    System.out.println("$$$ Start tag " + xpp.getName());

                    EventModel eventModel = new EventModel();
                    eventModel.setId(Integer.valueOf(xpp.getAttributeValue(null, "id")));
                    eventModel.setRaceModel(new RaceModel(Integer.valueOf(xpp.getAttributeValue(null, "raceid"))));
                    eventModel.setRoundModel(new RoundModel(Integer.valueOf(xpp.getAttributeValue(null, "roundid"))));
                    eventModel.setOrder(Integer.valueOf(xpp.getAttributeValue(null, "order")));
                    events.add(eventModel);

                } else if (xpp.getName().equals("RESULT")) {
                    System.out.println("Start tag " + xpp.getName());

                    resultIdInResult = Integer.valueOf(xpp.getAttributeValue(null, "id"));
                    raceIdInResult = Integer.valueOf(xpp.getAttributeValue(null, "raceid"));
                    roundIdInResult = Integer.valueOf(xpp.getAttributeValue(null, "roundid"));
                    qualifyingTimeInResult = Float.valueOf(xpp.getAttributeValue(null, "qualifyingtime"));
                } else if (xpp.getName().equals("SOLO")) {
                    System.out.println("$$$ Start tag " + xpp.getName());

                    /** BUILD THE WHOLE RESULT MODEL SINGLE SWIMMER WHICH WILL BE ADDED IN MEETING MODEL **/
                    swimmerIdInResult = Integer.valueOf(xpp.getAttributeValue(null, "swimmerid"));
                    isRelay = false;
                    /** ID **/
                    ResultModel result = new ResultModel(resultIdInResult);
                    /** SWIMMER **/
                    if (swimmers.size() != 0) {
                        for (int indexSw = 0; indexSw < swimmers.size(); indexSw++) {
                            if (swimmerIdInResult == swimmers.get(indexSw).getIdFFN()) {
                                result.setSwimmerModel(swimmers.get(indexSw));
                            }
                        }
                    } else {
                        // todo throw an exception which stop parsing but not application

                    }
                    /** EVENT **/
                    if (events.size() != 0) {
                        for (int indexEv = 0; indexEv < events.size(); indexEv++) {
                            if (raceIdInResult == events.get(indexEv).getRaceModel().getId() &&
                                    roundIdInResult == events.get(indexEv).getRoundModel().getId()) {
                                result.setEventModel(events.get(indexEv));
                            }
                        }
                    } else {
                        // todo throw an exception which stop parsing but not application
                    }
                    /** BUILD attributes with qualifying time in milliseconds and poolsize from meeting **/
                    result.buildContent(qualifyingTimeInResult * 100000, meetingModel.getPoolSize());

                    /** ADDING IN MEETING MODEL **/
                    meetingModel.addResult(result);


                } else if (xpp.getName().equals("RELAY")) {
//                    System.out.println("$$$ Start tag " + xpp.getName());
                    isRelay = true;
                    RaceData raceData = new RaceData();
                    raceData.makeData();
                    nbRelayerIfRelay = raceData.giveNbRelayer(raceIdInResult);
                    swIdInTeam = new ArrayList<Integer>();

                } else if (xpp.getName().equals("RELAYPOSITION")) {
//                    System.out.println("$$$ Start tag " + xpp.getName());

                    swIdInTeam.add(Integer.valueOf(xpp.getAttributeValue(null, "swimmerid")));

                    /** BUILD THE WHOLE RESULT MODEL WITH TEAM RELAY WHEN ALL SWIMMERS ARE FOUND **/
                    if (swIdInTeam.size() >= nbRelayerIfRelay) {
                        /** ID **/
                        ResultModel result = new ResultModel(resultIdInResult);
                        /** SWIMMERS IN TEAM **/
                        if (swimmers.size() != 0) {
                            for (int indexSw = 0; indexSw < swimmers.size(); indexSw++) {
                                for (int indexTeam = 0; indexTeam < swIdInTeam.size(); indexTeam++) {
                                    if (swIdInTeam.get(indexTeam) == swimmers.get(indexSw).getIdFFN()) {
                                        result.addSwimmersInTeam(swimmers.get(indexSw));
                                    }
                                }

                            }
                        } else {
                            // todo throw an exception which stop parsing but not application
                        }
                        /** EVENT **/
                        if (events.size() != 0) {
                            for (int indexEv = 0; indexEv < events.size(); indexEv++) {
                                if (raceIdInResult == events.get(indexEv).getRaceModel().getId() &&
                                        roundIdInResult == events.get(indexEv).getRoundModel().getId()) {
                                    result.setEventModel(events.get(indexEv));
                                }
                            }
                        } else {
                            // todo throw an exception which stop parsing but not application
                        }
                        /** BUILD attributes with qualifying time in milliseconds and poolsize from meeting **/
                        result.buildContent(qualifyingTimeInResult * 100000, meetingModel.getPoolSize());

                        /** ADDING IN MEETING MODEL **/
                        meetingModel.addResult(result);
                    }
                }


            } else if (eventType == XmlPullParser.END_TAG) {
//                System.out.println("End tag " + xpp.getName());
            } else if (eventType == XmlPullParser.TEXT) {
//                System.out.println("Text " + xpp.getText());
            }
            eventType = xpp.next();
        }
        //        System.out.println(" End document");
    }

    public MeetingModel getBackMeetingModel() {
        return meetingModel;
    }
}
