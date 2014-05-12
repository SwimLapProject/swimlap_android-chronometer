/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.parser;

import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.models.ResultModel;
import com.dim.swimlap.models.SwimmerModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FFNexMaker {

    public void makeFFNex(MeetingModel meeting) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            /** FFNEX in document **/
            Element FFNEX = document.createElement("FFNEX");
            FFNEX.setAttribute("version", "1.0.15");
            FFNEX.setAttribute("type", "performance");
            document.appendChild(FFNEX);

            /** MEETS in ffnex **/
            Element MEETS = document.createElement("MEETS");
            FFNEX.appendChild(MEETS);

            /** MEET in meets **/
            Element MEET = document.createElement("MEET");
            MEET.setAttribute("id", String.valueOf(meeting.getId()));
            MEET.setAttribute("name", meeting.getName());
            MEET.setAttribute("city", meeting.getCity());
            MEET.setAttribute("startdate", meeting.getStartDate());
            MEET.setAttribute("stopdate", meeting.getStopDate());
            MEETS.appendChild(MEET);

            /** POOL in meet **/
            Element POOL = document.createElement("POOL");
            MEET.setAttribute("size", String.valueOf(meeting.getPoolSize()));
            MEET.appendChild(POOL);

            /** CLUBS in meet **/
            Element CLUBS = document.createElement("CLUBS");
            MEET.appendChild(CLUBS);

            /** CLUB in clubs **/
            Element CLUB = document.createElement("CLUB");
            CLUB.setAttribute("id", String.valueOf(meeting.getClubId()));
            CLUB.setAttribute("name", meeting.getAllResults().get(0).getSwimmerModel().getClubModel().getName());
            CLUB.setAttribute("code", String.valueOf(meeting.getClubCode()));
            CLUBS.appendChild(CLUB);

            /** SWIMMERS  in meet **/
            Element SWIMMERS = document.createElement("SWIMMERS");
            MEET.appendChild(SWIMMERS);

            ArrayList<SwimmerModel> swimmers = meeting.getAllSwimmersInMeetting();

            /** SWIMMER in swimmers **/
            for (int indexSwimmer = 0; indexSwimmer < swimmers.size(); indexSwimmer++) {
                SwimmerModel swimmer = swimmers.get(indexSwimmer);

                Element SWIMMER = document.createElement("SWIMMER");
                SWIMMER.setAttribute("id", String.valueOf(swimmer.getIdFFN()));
                SWIMMER.setAttribute("firstname", swimmer.getFirstname());
                SWIMMER.setAttribute("lastname", swimmer.getName());
                SWIMMER.setAttribute("gender", swimmer.getGender());
                SWIMMER.setAttribute("birthdate", swimmer.getDateOfBirth());
                SWIMMER.setAttribute("clubid", String.valueOf(meeting.getClubId()));
                SWIMMERS.appendChild(SWIMMER);
            }
            /** RESULTS in meet**/
            Element RESULTS = document.createElement("RESULTS");
            MEET.appendChild(RESULTS);

            ArrayList<ResultModel> allResults = meeting.getAllResults();

            /** RESULT in results **/
            for (int indexResult = 0; indexResult < allResults.size(); indexResult++) {
                ResultModel result = allResults.get(indexResult);

                Element RESULT = document.createElement("RESULT");
                RESULT.setAttribute("id", String.valueOf(result.getId()));
                String swimTime = transformLapInFFNexFormatTime(result.getSwimTime());

                RESULT.setAttribute("swimtime", swimTime);
                RESULT.setAttribute("raceid", String.valueOf(result.getEventModel().getRaceModel().getId()));
                RESULT.setAttribute("roundid", String.valueOf(result.getEventModel().getRoundModel().getId()));
                RESULT.setAttribute("clubid", String.valueOf(meeting.getClubId()));
                RESULTS.appendChild(RESULT);

                /** RELAY OR SOLO in result */
                if (result.isRelay()) {
                    ArrayList<SwimmerModel> team = result.getTeam();

                    Element RELAY = document.createElement("RELAY");
                    RESULT.appendChild(RELAY);

                    Element RELAYPOSITIONS = document.createElement("RELAYPOSITIONS");
                    RELAY.appendChild(RELAYPOSITIONS);
                    for (int indexSwimmer = 0; indexSwimmer < team.size(); indexSwimmer++) {
                        Element RELAYPOSITION = document.createElement("RELAYPOSITION");
                        RESULT.setAttribute("number", String.valueOf(indexSwimmer));
                        RESULT.setAttribute("swimmerid", String.valueOf(team.get(indexSwimmer).getIdFFN()));
                        RESULT.setAttribute("clubid", String.valueOf(team.get(indexSwimmer).getClubModel().getId()));
                        RELAYPOSITIONS.appendChild(RELAYPOSITION);
                    }
                } else {
                    Element SOLO = document.createElement("SOLO");
                    SOLO.setAttribute("swimmerid", String.valueOf(result.getSwimmerModel().getIdFFN()));
                    RESULT.appendChild(SOLO);
                }

                /** SPLITS in result **/
                if (result.getLaps().size() > 0) {
                    Element SPLITS = document.createElement("SPLITS");
                    RESULT.appendChild(RESULT);

                    /** SPLIT in splits **/
                    for (int indexLap = 0; indexLap < result.getLaps().size(); indexLap++) {
                        float lap = result.getLaps().get(indexLap);
                        if (lap != 0) {
                            String lapAsString = transformLapInFFNexFormatTime(lap);
                            Element SPLIT = document.createElement("SPLIT");
                            SPLIT.setAttribute("qualifyingtime", lapAsString);
                            String distance = String.valueOf(meeting.getPoolSize() * (indexLap + 1));
                            SPLIT.setAttribute("distance", distance);
                            SPLITS.appendChild(SPLIT);
                        }
                    }
                }
            }

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            Properties outFormat = new Properties();
            outFormat.setProperty(OutputKeys.INDENT, "yes");
            outFormat.setProperty(OutputKeys.METHOD, "xml");
            outFormat.setProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            outFormat.setProperty(OutputKeys.VERSION, "1.0");
            outFormat.setProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperties(outFormat);
            DOMSource domSource =
                    new DOMSource(document.getDocumentElement());
            OutputStream output = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(output);
            transformer.transform(domSource, result);
// what to do do now

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private String transformLapInFFNexFormatTime(float lap) {
        int milli = (int) lap % 1000;
        int seconds = (int) (lap - milli) / 1000;
        int minutes = 0;
        if (seconds > 60) {
            minutes = (int) Math.floor(seconds / 60);
            seconds = seconds - (minutes * 60);
        }
        milli = (int) Math.floor(milli / 10);
        String timeAsString = String.valueOf(minutes) + "." + String.valueOf(seconds) + String.valueOf(milli);
        return timeAsString;
    }
}

