package com.os.vitaly.hw_minesweeper.Controls;

import android.content.Context;
import android.hardware.SensorEvent;

import com.os.vitaly.hw_minesweeper.GameUI.GameActivity;

/**
 * Created by Vitaly on 14.09.2017.
 */

public class AccelerometerHandler implements Runnable, AccelerometerService.AccelerometerListener {

    private AccelerometerService accelerometerService;
    private GameRunner gameRanner;
    private Context context;
    private boolean started;
    private float x,y,z;
    private long lastTime;
    private final int TIME_THRESHOLD = 1000, MOTION_DELTA = 3;

    private float[] initialValues;
    private boolean initialPlacement = true;

    public AccelerometerHandler(AccelerometerService accelerometerService, GameRunner gameRanner, Context context) {
        this.accelerometerService = accelerometerService;
        this.gameRanner = gameRanner;
        this.context = context;
        this.started = false;
    }

    @Override
    public void onAccelerometerSensorEvent(SensorEvent event) {

        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        if (!started) {
            initialValues = event.values.clone();
            started = true;
        }

        if(System.currentTimeMillis() - lastTime > TIME_THRESHOLD){

            if (Math.abs(x - initialValues[0]) >= MOTION_DELTA) {
                if (x > initialValues[0])  // Tilted to left.
                    gameRanner.updateMines();
                else if (x < initialValues[0]) // Tilted to right.
                    gameRanner.updateMines();
                initialPlacement = false;
            }
            else if (Math.abs(y - initialValues[1]) >= MOTION_DELTA) {
                if (y > initialValues[1]) // Tilted to bottom.
                    gameRanner.updateMines();
                else if (y < initialValues[1]) // Tilted to top.
                    gameRanner.updateMines();
                initialPlacement = false;
            }
            else { //  Back to initial.
                if (!initialPlacement)
                    initialPlacement = true;
            }
            lastTime = System.currentTimeMillis();
        }
    }

    @Override
    public void run() {
        accelerometerService.startListening();
    }
}


