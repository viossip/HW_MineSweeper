package com.os.vitaly.hw_minesweeper.GameUI;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.os.vitaly.hw_minesweeper.Entities.User;
import com.os.vitaly.hw_minesweeper.R;

import java.util.ArrayList;

/**
 * Created by ilya on 22/09/2017.
 */

class TableAdapter extends ArrayAdapter<User> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<User> users;

    public TableAdapter(Context context, int layoutResourceId, ArrayList<User> users) {

        super(context, layoutResourceId, users);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.users = users;
    }

    public View getView(int position, View row, ViewGroup parent) {



            String name = users.get(position).getName();
            int time = users.get(position).getTime();
            int id = users.get(position).getID();


            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            TextView Txnumber = (TextView) row.findViewById(R.id.number);
            TextView Txname = (TextView) row.findViewById(R.id.name);
            TextView Txtime = (TextView) row.findViewById(R.id.score);
            String strScore = (String.format("%02d:%02d", ((time % 3600) / 60),
                    time % 60));
            Txnumber.setText(id + "");
            Txname.setText(name);
            Txtime.setText(strScore + "");

        return row;

    }
}