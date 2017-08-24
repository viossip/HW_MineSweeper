package com.os.vitaly.hw_minesweeper.Controls;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.os.vitaly.hw_minesweeper.Entities.Cell;

//import com.example.ilyavitaly.minesweeper.UI.Cell;

/**
 * Created by ilya on 23/08/2017.
 */
public class GameRunner {
     static int HEIGHT;
     static int WIDTH;
     static int Bomb_Number;
    private static GameRunner instance;
    private Context context;

    private Cell[][] Minesweepers ;

    public static GameRunner getInstance() {
        if( instance == null ){
            instance = new GameRunner (Bomb_Number,HEIGHT,WIDTH);
        }
        return instance;
    }
    public GameRunner(){}

    public GameRunner(int bomb_Number,int HEIGHT,int WIDTH ) {
        this.Bomb_Number=bomb_Number;
        this.HEIGHT=HEIGHT;
        this.WIDTH=WIDTH;
    }

    public void createGrid(Context context){
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = GameLogic.generator(Bomb_Number,WIDTH, HEIGHT);
        PrintGrid.print(GeneratedGrid,WIDTH,HEIGHT);
        setGrid(context,GeneratedGrid,);

    }
    private void setGrid( final Context context, final int[][] grid ){
        Minesweepers=new Cell[WIDTH][HEIGHT];
        for( int x = 0 ; x < WIDTH ; x++ )
            for (int y = 0; y < HEIGHT; y++) {
                if (Minesweepers[x][y] == null) {
                    Minesweepers[x][y] = new Cell(context, x, y);
                }
                Minesweepers[x][y].setValue(grid[x][y]);
                Minesweepers[x][y].invlidate
            }
    }
    }

