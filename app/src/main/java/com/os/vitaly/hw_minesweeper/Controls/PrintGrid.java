package com.os.vitaly.hw_minesweeper.Controls;

import android.util.Log;

/**
 * Created by ilya on 24/08/2017.
 */

class PrintGrid {
    public static void print(int[][] generatedGrid, int width, int height) {

            for( int x = 0 ; x < width ; x++ ){
                String printedText = "| ";
                for( int y = 0 ; y < height ; y++ ){
                    printedText += String.valueOf(generatedGrid[x][y]).replace("-1", "B") + " | ";
                }
                Log.e("",printedText);
            }
        }
    }

