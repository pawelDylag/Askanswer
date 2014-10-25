package com.aghacks.askanswer.data;

import java.io.Serializable;

/**
 * Created by paweldylag on 25/10/14.
 */
public class UserData implements Serializable{

    // monitored place -> aktualnie otwarta sesja do nasluchiwania
    private String monitoredPlaceName;
    private int monitoredId;
    private boolean askMode;

    public UserData () {
        monitoredId = 0;
        monitoredPlaceName = null;
        askMode = false;
    }

    public Place getMonitoredPlace () {
        return new Place(monitoredPlaceName, monitoredId);
    }

    public void changeMonitoredPlace (Place n) {
        this.monitoredPlaceName = n.getName();
        this.monitoredId = n.getId();
    }

    public void changeAskMode(boolean state) {
        askMode = state;
    }

}
