package com.os.vitaly.hw_minesweeper.Controls;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.os.vitaly.hw_minesweeper.Entities.Cell;
import com.os.vitaly.hw_minesweeper.GameUI.ChooseLvlActivity;
import com.os.vitaly.hw_minesweeper.GameUI.GameActivity;
import com.os.vitaly.hw_minesweeper.GameUI.MainActivity;

//import com.example.ilyavitaly.minesweeper.UI.Cell;

/**
 * Created by ilya on 23/08/2017.
 */
public class GameRunner {
    public String level;
//    GameActivity gm= new GameActivity();
    public static int HEIGHT=10;
    public static int WIDTH=10;
    public static int Bomb_Number=5;

    private static GameRunner instance;
    private Context context;

    private Cell[][] Minesweepers;

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


    public void createGrid(Context context,boolean check) {
        setLevel(GameActivity.lvl.getValue());
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = GameLogic.generator(Bomb_Number, WIDTH, HEIGHT);
        PrintGrid.print(GeneratedGrid, WIDTH, HEIGHT);
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
//            Toast.makeText(context, "Game won", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void flag(int x, int y) {
        boolean isFlagged = getCellAt( x, y).isFlagged();
        getCellAt( x, y).setFlagged(!isFlagged);
        getCellAt( x, y).invalidate();
    }

    private void onGameLost() {

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                getCellAt( x, y).setRevealed();
            }
        }
    }
}
