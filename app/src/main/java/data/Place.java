package data;

/**
 * Created by paweldylag on 25/10/14.
 */
public class Place {

    private String name;
    private int minor;
    private int major;

    public Place (String name) {
        this.name = name;
        this.minor = 0;
        this.major = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }
}
