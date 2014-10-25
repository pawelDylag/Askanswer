package com.aghacks.askanswer.data;

/**
 * Created by paweldylag on 25/10/14.
 */
public class Place {

    private String name;
    private int id;


    public Place (String name) {
        this.name = name;
        this.id = 0;
    }

    public Place (String name, int mi) {
        this.name = name;
        this.id = mi;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
