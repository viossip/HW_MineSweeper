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

//import com.example.ilyavitaly.minesweeper.UI.Cell;

/**
 * Created by ilya on 23/08/2017.
 */
public class GameRunner {
    String level;
    public static int HEIGHT;
    public static int WIDTH;
    public static int Bomb_Number;
    private static GameRunner instance;
    private Context context;
    GameActivity gm= new GameActivity();
    private Cell[][] Minesweepers;

    public static GameRunner getInstance() {
        if (instance == null) {

            instance = new GameRunner();
        }
        return instance;
    }

    public GameRunner() {

    }



    public void createGrid(Context context) {
        createLogic(gm);
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = GameLogic.generator(Bomb_Number, WIDTH, HEIGHT);
        PrintGrid.print(GeneratedGrid, WIDTH, HEIGHT);
        setGrid(context, GeneratedGrid);

    }

    private void createLogic(GameActivity gm) {
        if (gm.getLevel().getValue().equals("Easy")){
            HEIGHT=10;
            WIDTH=10;
            Bomb_Number=5;
        }
        if (gm.getLevel().getValue().equals("Medium")) {
            HEIGHT=10;
            WIDTH=10;
            Bomb_Number=10;
        }
        if (gm.getLevel().getValue().equals("Hard")) {
            HEIGHT=10;
            WIDTH=10;
            Bomb_Number=10;
        }
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

    public Cell getCellAt(int position, int x, int y, boolean ByPosition) {
        if (ByPosition) {
            int PosX = position % WIDTH;
            int PosY = position / WIDTH;
            return Minesweepers[PosX][PosY];
        }
        return Minesweepers[x][y];
    }

    public void click(int x, int y) {
        if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(-1, x, y, false).isClicked()) {
            getCellAt(-1, x, y, false).setClicked();

            if (getCellAt(-1, x, y, false).getValue() == 0) {
                for (int xt = -1; xt <= 1; xt++) {
                    for (int yt = -1; yt <= 1; yt++) {
                        if (xt != yt) {
                            click(x + xt, y + yt);
                        }
                    }
                }
            }

            if (getCellAt(-1, x, y, false).isBomb()) {
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
                if (getCellAt(-1,x, y,false).isRevealed() || getCellAt(-1,x, y,false).isFlagged()) {
                    notRevealed--;
                }

                if (getCellAt(-1,x, y,false).isFlagged() && getCellAt(-1,x, y,false).isBomb()) {
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
        boolean isFlagged = getCellAt(-1,x, y,false).isFlagged();
        getCellAt(-1,x, y,false).setFlagged(!isFlagged);
        getCellAt(-1,x, y,false).invalidate();
    }

    private void onGameLost() {

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                getCellAt(-1,x, y,false).setRevealed();
            }
        }
    }
}
