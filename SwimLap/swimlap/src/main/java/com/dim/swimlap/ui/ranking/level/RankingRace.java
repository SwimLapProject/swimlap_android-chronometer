/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.ranking.level;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class RankingRace extends BaseExpandableListAdapter {
    private Context context;

    public RankingRace(Context context) {
        this.context = context;
    }

    @Override
    public Object getChild(int arg0, int arg1) {
        return arg1;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        ExpandableListView secondLevel = new ExpandableListView(context);
//        secondLevel.setAdapter(new RankingSwimmer(context));
//        secondLevel.setGroupIndicator(null);
//        return secondLevel;
        return null;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return 5;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return 5;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setText("->ThirdLevel");
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(20);
        tv.setBackgroundColor(Color.YELLOW);
        tv.setPadding(10, 7, 7, 7);
        return tv;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
