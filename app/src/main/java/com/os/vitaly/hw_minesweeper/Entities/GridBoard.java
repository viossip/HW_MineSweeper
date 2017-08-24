package com.os.vitaly.hw_minesweeper.Entities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.os.vitaly.hw_minesweeper.Controls.GameRunner;

/**
 * Created by ilya on 24/08/2017.
 */

public class GridBoard extends GridView {

    public GridBoard(Context context , AttributeSet attrs){
        super(context,attrs);

        GameRunner.getInstance().createGrid(context);

        setNumColumns(GameRunner.WIDTH);
        setAdapter(new GridAdapter());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return GameRunner.getInstance().WIDTH * GameRunner.getInstance().HEIGHT;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return GameRunner.getInstance().getCellAt(position,-1,-1,true);
        }
    }
}
