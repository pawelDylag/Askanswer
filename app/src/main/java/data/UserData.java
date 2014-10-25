package data;

import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * Created by paweldylag on 25/10/14.
 */
public class UserData implements Serializable{

    // monitored place -> aktualnie otwarta sesja do nasluchiwania
    private String monitoredPlaceName;
    private int monitoredPlaceMinor;
    private int monitoredPlaceMajor;
    private boolean askMode;

    public UserData () {
        monitoredPlaceMinor = 0;
        monitoredPlaceMajor = 0;
        monitoredPlaceName = null;
        askMode = false;
    }

    public Place getMonitoredPlace () {
        return new Place(monitoredPlaceName, monitoredPlaceMinor, monitoredPlaceMajor);
    }

    public void changeMonitoredPlace (Place n) {
        this.monitoredPlaceName = n.getName();
        this.monitoredPlaceMajor = n.getMajor();
        this.monitoredPlaceMinor = n.getMinor();
    }

    public void changeAskMode(boolean state) {
        askMode = state;
    }

}
