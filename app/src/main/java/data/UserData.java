package data;

/**
 * Created by paweldylag on 25/10/14.
 */
public class UserData {

    // monitored place -> aktualnie otwarta sesja do nasluchiwania
    private Place monitoredPlace;
    private boolean askMode;

    public UserData () {
        this.monitoredPlace = null;
        askMode = false;
    }

    public Place getMonitoredPlace () {
        return this.monitoredPlace;
    }

    public void changeMonitoredPlace (Place n) {
        this.monitoredPlace = n;
    }

    public void changeAskMode(boolean state) {
        askMode = state;
    }



}
