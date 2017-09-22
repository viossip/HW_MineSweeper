package com.os.vitaly.hw_minesweeper.GameUI;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.os.vitaly.hw_minesweeper.Entities.User;
import com.os.vitaly.hw_minesweeper.R;

import java.util.ArrayList;

/**
 * Created by ilya on 22/09/2017.
 */

public class ListFragment extends Fragment{
    private ArrayList<User> users = new ArrayList<>();


    public ListFragment() {

        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ListView myListView = (ListView)view.findViewById(R.id.List_view);
        Cursor res = MainActivity.currentDB.getAllData();
        if (res.getCount() == 0)
        {
            // show message

        }

        else
        {
            int counter = 1;

            while(res.moveToNext())
            {
                String name = res.getString(0);
                int time = res.getInt(3);
                users.add(new User(name,time,counter++));

            }




        }

        TableAdapter tableAdapter = new TableAdapter(getActivity(),R.layout.table_adapter,users);
        myListView.setAdapter(tableAdapter);



        return view;
    }
}
