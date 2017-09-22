package com.os.vitaly.hw_minesweeper.Controls;

/**
 * Created by Vitaly on 14.09.2017.
 */

public class AccelerometerHandler implements Runnable, AccelerometerService.AccelerometerListener {

    private static final int MOTION_CHANGE = 3;
    private AccelerometerService accelerometerService;
    private GameRunner gameRanner;
    private boolean started;

    private float[] currentValues;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    public AccelerometerHandler(AccelerometerService accelerometerService, GameRunner gameRanner) {
        this.accelerometerService = accelerometerService;
        this.gameRanner = gameRanner;
        this.started = false;
    }

    @Override
    public void onAccelerometerSensorEvent(float[] values) {
        if (!started) {
            currentValues = values.clone();
            started = true;
        }

        //  TESTS   /////////////////////////////////////////////
        float x = values[0];
        float y = values[1];
        float z = values[2];

        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = mAccelCurrent - mAccelLast;

        if (delta > 6)
            gameRanner.getSeconds();

        mAccel = mAccel * 0.9f + delta; // perform low-cut filter

        /////////////////////////////////////////////////////////////
    }

    @Override
    public void run() {
        accelerometerService.startListening();
    }
}


