package com.os.vitaly.hw_minesweeper.GameUI;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.os.vitaly.hw_minesweeper.R;

public class HighscoresActivity extends AppCompatActivity {



     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

         FragmentManager fragmentManager = getFragmentManager();
         FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
         ListFragment lf = new ListFragment();
         fragmentTransaction.add(R.id.frame_container,lf);
         fragmentTransaction.commit();
         ImageButton returnButton = (ImageButton)findViewById(R.id.btnReturn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton mapButton = (ImageButton)findViewById(R.id.btnMap);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frame_container)).commit();

                MapFragment mf= new MapFragment();
                fragmentTransaction.add(R.id.frame_container,mf);
                fragmentTransaction.commit();
            }
        });

         ImageButton tableButton = (ImageButton)findViewById(R.id.btnTable);
         tableButton.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view) {

                 FragmentManager fragmentManager = getFragmentManager();
                 FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                 getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frame_container)).commit();
                 ListFragment lf = new ListFragment();
                 fragmentTransaction.add(R.id.frame_container,lf);
                 fragmentTransaction.commit();
             }
         } );



    }


}
