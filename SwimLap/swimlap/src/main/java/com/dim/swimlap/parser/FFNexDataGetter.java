/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.parser;

import android.os.Environment;

import com.dim.swimlap.models.MeetingModel;

import org.xmlpull.v1.XmlPullParserException;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FFNexDataGetter {

    private File ffnexDir;
    private String[] files;

    public void createDirectory() throws IOException {
        File root = Environment.getExternalStorageDirectory();
        File swimLapDir = new File(root, "swimlap");
        swimLapDir.mkdir();
        ffnexDir = new File(swimLapDir,"ffnex");
        ffnexDir.mkdir();
    }

    public String[] getFiles() {
        files = ffnexDir.list();
        return files;
    }

    public File getFFNExFile(String name) {
        File ffnex =  new File(ffnexDir, name);
        return ffnex;
    }

    public String transformFileToString(File fileXML) throws IOException {
        FileInputStream fis = new FileInputStream(fileXML);
        byte[] buffer = new byte[(int) fileXML.length()];
        new DataInputStream(fis).readFully(buffer);
        fis.close();
        String stringFFNex = new String(buffer, "UTF-8");
        return stringFFNex;

    }

    public MeetingModel getResultOfParsing(String stringXML) throws IOException, XmlPullParserException {
        FFNexParser parser = new FFNexParser();
        parser.parseIt(stringXML);
        MeetingModel meetingModel = parser.getBackMeetingModel();
        return meetingModel;
    }




}
