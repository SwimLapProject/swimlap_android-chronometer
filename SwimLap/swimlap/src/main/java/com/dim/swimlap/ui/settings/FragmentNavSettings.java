/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dim.swimlap.R;
import com.dim.swimlap.models.MeetingModel;
import com.dim.swimlap.parser.FFNexDataGetter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

;

public class FragmentNavSettings extends Fragment implements View.OnClickListener {

    private Button buttonScan;
    private String fileNameToParse;
    private MeetingModel meetingModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_settings, container, false);
        buttonScan = (Button) view.findViewById(R.id.id_button_scan_ffnex);
        buttonScan.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_button_scan_ffnex) {
            Toast.makeText(getActivity(), "Start parsing", Toast.LENGTH_SHORT).show();
            doParsing();
        }
    }

    public void doParsing() {

        FFNexDataGetter ffnexGetter = new FFNexDataGetter();
        try {
            ffnexGetter.createDirectory();
            String[] files = ffnexGetter.getFiles();
            final String[] items = files;

            if (files == null || files.length==0) {
                Toast.makeText(getActivity(), "NO file in swimlap directory", Toast.LENGTH_SHORT).show();

            } else {

                if (files.length > 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Exit!")
                            .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int item) {
                                    Toast.makeText(getActivity(), items[item], Toast.LENGTH_SHORT).show();
                                    fileNameToParse = items[item];
                                }
                            });
                    builder.create().show();
                } else if (files.length == 1) {
                    // if only one file
                    fileNameToParse = files[0];
                }
                Toast.makeText(getActivity(), "Find " + fileNameToParse, Toast.LENGTH_SHORT).show();

                File fileToParse = ffnexGetter.getFFNExFile(fileNameToParse);
                String stringXMLToParse = ffnexGetter.transformFileToString(fileToParse);
                meetingModel = ffnexGetter.getResultOfParsing(stringXMLToParse,getActivity());

                // RECORD IN DB
                ffnexGetter.recordParsedMeetingInDb(meetingModel,getActivity());
                if(ffnexGetter.recordParsingHasBeenDone(meetingModel.getId(),getActivity())){
                    ffnexGetter.moveFFNexParsed(fileNameToParse);
                    Toast.makeText(getActivity(), "MEETING !\n" + meetingModel.getName()+"\nHAS BEEN RECORDED IN PHONE", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (IOException e) {
            Toast.makeText(getActivity(), "A problem occured: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
