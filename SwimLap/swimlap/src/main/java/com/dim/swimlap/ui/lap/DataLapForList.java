/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.ui.lap;

import com.dim.swimlap.models.EventModel;
import com.dim.swimlap.models.SwimmerModel;

public class DataLapForList {

    private SwimmerModel swimmerModel;
    private EventModel eventModel;

    public DataLapForList(SwimmerModel swimmerModel, EventModel eventModel) {
        this.swimmerModel = swimmerModel;
        this.eventModel = eventModel;
    }

    public SwimmerModel getSwimmerModel() {
        return swimmerModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }
}
