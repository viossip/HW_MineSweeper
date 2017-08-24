package com.os.vitaly.hw_minesweeper.Controls;

import java.util.Random;

/**
 * Created by Vitaly on 12.08.2017.
 */

public class GameLogic {
    public static int[][] generator( int bomb , final int width , final int height){

        Random r = new Random();

        int [][] grid = new int[width][height];
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

        return grid;
    }

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
