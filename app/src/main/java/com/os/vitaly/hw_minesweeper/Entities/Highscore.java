package com.os.vitaly.hw_minesweeper.Entities;

/**
 * Created by Vitaly on 12.08.2017.
 */

public class Highscore implements Comparable<Highscore>{

    public static final int MAX_HIGHSCORES = 10;
    private String name;
    private int minutes;
    private int seconds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public int compareTo(Highscore highscore) {
        if (this.getMinutes() > highscore.getMinutes())
            return 1;
        else if (this.getMinutes() == highscore.getMinutes()) {
            if (this.getSeconds() > highscore.getSeconds())
                return 1;
            else if (this.getSeconds() == highscore.getSeconds())
                return 0;
            else
                return -1;
        }
        else
            return -1;
    }

    public String getFixedTime() {
        String time;
        if (minutes < 10) {
            if (seconds < 10)
                time = ("0" + minutes + ":0" + seconds);
            else
                time = ("0" + minutes + ":" + seconds);
        }
        else {
            if (seconds < 10)
                time = (minutes + ":0" + seconds);
            else
                time = (minutes + ":" + seconds);
        }
        return time;
    }
}
