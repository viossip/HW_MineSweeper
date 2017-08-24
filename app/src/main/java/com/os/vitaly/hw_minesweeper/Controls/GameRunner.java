/*package com.example.ilyavitaly.minesweeper;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ilyavitaly.minesweeper.UI.Cell;

/**
 * Created by ilya on 23/08/2017.

public class GameRunner {

    private static GameRunner instance;
    private Context context;
    private Cell[][] MinesweeperGrid = new Cell[width][height];

    public static GameRunner getInstance() {
        if( instance == null ){
            instance = new GameRunner();
        }
        return instance;
    }

    private GameRunner(){ }

    public void createGrid(Context context){
        Log.e("GameEngine","createGrid is working");
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = Generator.generate(BOMB_NUMBER,WIDTH, HEIGHT);
        PrintGrid.print(GeneratedGrid,WIDTH,HEIGHT);
        setGrid(context,GeneratedGrid);
    }

    }*/

