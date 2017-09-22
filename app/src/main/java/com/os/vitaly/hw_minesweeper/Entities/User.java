package com.os.vitaly.hw_minesweeper.Entities;

/**
 * Created by ilya on 22/09/2017.
 */

public class User {
    String name;
    int time;
    int id;

    public User(String name, int time, int i) {
        this.name = name;
        this.time = time;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
    public int getID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setID(int id) {
        this.id = id;
    }

}
