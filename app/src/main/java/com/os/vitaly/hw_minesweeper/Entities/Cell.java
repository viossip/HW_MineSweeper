package com.os.vitaly.hw_minesweeper.Entities;

import android.content.Context;
import android.view.View;

/**
 * Created by Vitaly on 12.08.2017.
 */

public class  Cell extends View{

    public void setClicked() {
    }

    public int getValue() {
        return value;
    }

    public enum CellValue{
        Empty, Covered, Mine, Flag, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;
    }

    public Cell(Context context ){
        super(context);
    }
}
