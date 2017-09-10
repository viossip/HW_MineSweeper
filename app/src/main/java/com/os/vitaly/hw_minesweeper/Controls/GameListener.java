package com.os.vitaly.hw_minesweeper.Controls;

/**
 * Created by Vitaly on 25.08.2017.
 */

public interface GameListener {
    void timeChanged();
    void onEndGame(boolean isWin);
    void minesUpdated();

}
