package com.os.vitaly.hw_minesweeper.Controls;

import android.content.Context;

import com.os.vitaly.hw_minesweeper.Entities.Cell;
import com.os.vitaly.hw_minesweeper.GameUI.GameActivity;
import com.os.vitaly.hw_minesweeper.Services.GpsLocation;

import java.util.Timer;
import java.util.TimerTask;

//import com.example.ilyavitaly.minesweeper.UI.Cell;

/**
 * Created by ilya on 23/08/2017.
 */
public class GameRunner {
    private int flagsCount = 0;
    public static boolean TimerOn=true;
    public String level;

    public int seconds = 0;
    public int minutes = 0;
    public Timer t;
//    GameActivity gm= new GameActivity();
    public static int HEIGHT=10;
    public static int WIDTH=10;
    public static int Bomb_Number=5;
    int flagCount;
    private GpsLocation gpsLocation;
    private static GameRunner instance;
    private Context context;

    private Cell[][] Minesweepers;
    private GameListener gameListener;
    private ServiceListener serviceListener;
    public static GameRunner getInstance() {
        if (instance == null) {
            instance = new GameRunner();
        }
        return instance;
    }

    public GameRunner() {
    }

    public void setLevel(String lvl) {
        level = lvl;
        if (level != null) {
            if (level.equals("Easy")) {
                HEIGHT = 10;
                WIDTH = 10;
                Bomb_Number = 5;
            }
            if (level.equals("Medium")) {
                HEIGHT = 10;
                WIDTH = 10;
                Bomb_Number = 10;
            }
            if (level.equals("Hard")) {
                HEIGHT = 5;
                WIDTH = 5;
                Bomb_Number = 10;
            }
        }
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }
    public void setServiceListener(ServiceListener serviceListener){this.serviceListener=serviceListener;}

    public void startTimer() {
        t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                seconds += 1;
                if (seconds == 60) {
                    seconds = 0;
                    minutes = minutes + 1;
                }
                gameListener.timeChanged();
            }

        }, 0, 1000);
    }

    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds;
    }


    public void createGrid(Context context,boolean check) {
        setLevel(GameActivity.lvl.getValue());
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = GameLogic.generator(Bomb_Number, WIDTH, HEIGHT);

        setGrid(context, GeneratedGrid);

    }

    private void setGrid(final Context context, final int[][] grid) {
        Minesweepers = new Cell[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++)
            for (int y = 0; y < HEIGHT; y++) {
                if (Minesweepers[x][y] == null) {
                    Minesweepers[x][y] = new Cell(context, x, y);
                }
                Minesweepers[x][y].setValue(grid[x][y]);
                Minesweepers[x][y].invalidate();
            }
    }

public Cell getCellAt(int position) {
    int x = position % WIDTH;
    int y = position / WIDTH;

    return Minesweepers[x][y];
}

    public Cell getCellAt( int x , int y ){
        return Minesweepers[x][y];
    }

    public void click(int x, int y) {
        if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt( x, y).isClicked()) {
            getCellAt( x, y).setClicked();

            if (getCellAt( x, y).getValue() == 0) {
                for (int xt = -1; xt <= 1; xt++) {
                    for (int yt = -1; yt <= 1; yt++) {
                        if (xt != yt) {
                            click(x + xt, y + yt);
                        }
                    }
                }
            }

            if (getCellAt( x, y).isBomb()) {
                onGameLost();
            }
        }

        checkEnd();
    }

    private boolean checkEnd() {
        int bombNotFound = Bomb_Number;
        int notRevealed = WIDTH * HEIGHT;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (getCellAt( x, y).isRevealed() || getCellAt( x, y).isFlagged()) {
                    notRevealed--;
                }

                if (getCellAt( x, y).isFlagged() && getCellAt( x, y).isBomb()) {
                    bombNotFound--;
                }
            }
        }

        if (bombNotFound == 0 && notRevealed == 0) {
            gpsLocation= new GpsLocation();
            gpsLocation.getIsGPSTrackingEnabled();

            gameListener.onEndGame(true);
            timerReset();

//            Toast.makeText(context, "Game won", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void flag(int x, int y) {
        boolean isFlagged = getCellAt(x, y).isFlagged();
        if (isFlagged)
            flagCount--;
        if (Bomb_Number - flagCount > 0) {
            getCellAt(x, y).setFlagged(!isFlagged);
        }
        if (!isFlagged && Bomb_Number - flagCount > 0)
            flagCount++;
        getCellAt(x, y).invalidate();
        gameListener.minesUpdated();
    }

    private void onGameLost() {
        gameListener.onEndGame(false);
        timerReset();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                getCellAt( x, y).setRevealed();
            }
        }
    }

    public void timerReset() {
        t.cancel();
        minutes=0;
        seconds=0;
        flagCount=0;
    }

    public int getMines() {

        return Bomb_Number-flagCount;
    }
}
