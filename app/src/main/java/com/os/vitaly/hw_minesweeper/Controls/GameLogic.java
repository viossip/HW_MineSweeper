package com.os.vitaly.hw_minesweeper.Controls;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Vitaly on 12.08.2017.
 */

public class GameLogic {

    public static ArrayList<Point> positions = new ArrayList<Point>();
    public static  int grid[][];
    public static Random r;

    public static int[][] generator( int bomb , final int width , final int height){

        r = new Random();

        grid = new int[width][height];
        for( int x = 0 ; x< width ;x++ ){
            grid[x] = new int[height];
        }

        while( bomb > 0 ){
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            // -1 is the bomb
            if( grid[x][y] != -1 ){
                grid[x][y] = -1;
                bomb--;
            }
        }
        grid = calculateNeigbours(grid,width,height);
        ////////////////////////////////////////////
        savePositionsWithoutMines(grid, grid.length,grid[0].length);
        ///////////////////////////////////////////
        return grid;
    }

    public static int [][] addMine()
    {
        if (positions.size() > 0 ) {
            r = new Random();
            int pos = r.nextInt(positions.size());
            grid[positions.get(pos).x][positions.get(pos).y] = -1;
            grid = calculateNeigbours(grid, grid.length, grid[0].length);
            positions.remove(pos);
        }
        else
        {
            //Lose
        }
        return  grid;
    }

    private static void savePositionsWithoutMines(int[][] grid, int width, int height) {

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                if (grid[x][y] != -1) {
                    positions.add(new Point(x,y));
                }
            }
    }



    /*public static int[][] addMines(int [][] grid, int numOfMines){

        Random r = new Random();

        for (int i =0; i<numOfMines; i++){

            int x = r.nextInt(grid.length);
            int y = r.nextInt(grid[0].length);

            while (grid[x][y] == -1){
                x = r.nextInt(grid.length);
                y = r.nextInt(grid[0].length);
            }
            grid[x][y] = -1;
        }
        grid = calculateNeigbours(grid,grid.length,grid[0].length);
        return grid;
    }*/

    private static int[][] calculateNeigbours( int[][] grid , final int width , final int height){
        for( int x = 0 ; x < width ; x++){
            for( int y = 0 ; y < height ; y++){
                grid[x][y] = getNeighbourNumber(grid,x,y,width,height);
            }
        }

        return grid;
    }

    private static int getNeighbourNumber( final int grid[][] , final int x , final int y , final int width , final int height){
        if( grid[x][y] == -1 ){
            return -1;
        }

        int count = 0;

        if( mineAt(grid,x - 1 ,y + 1,width,height)) count++; // top-left
        if( mineAt(grid,x     ,y + 1,width,height)) count++; // top
        if( mineAt(grid,x + 1 ,y + 1,width,height)) count++; // top-right
        if( mineAt(grid,x - 1 ,y    ,width,height)) count++; // left
        if( mineAt(grid,x + 1 ,y    ,width,height)) count++; // right
        if( mineAt(grid,x - 1 ,y - 1,width,height)) count++; // bottom-left
        if( mineAt(grid,x     ,y - 1,width,height)) count++; // bottom
        if( mineAt(grid,x + 1 ,y - 1,width,height)) count++; // bottom-right

        return count;
    }

    private static boolean mineAt( final int [][] grid, final int x , final int y , final int width , final int height){
        if( x >= 0 && y >= 0 && x < width && y < height ){
            if( grid[x][y] == -1 ){
                return true;
            }
        }
        return false;
    }
}
