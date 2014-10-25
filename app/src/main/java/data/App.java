package data;

import android.app.Application;

/**
 * Created by paweldylag on 25/10/14.
 */
public class App extends Application {

    private static UserData userData;

    @Override
    public void onCreate() {
        super.onCreate();
        userData = new UserData();
    }

    public static UserData getUserData() {
        return userData;
    }

    public static void setUserData(UserData n) {
        userData = n;
    }
}
