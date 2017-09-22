package com.os.vitaly.hw_minesweeper.GameUI;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.os.vitaly.hw_minesweeper.R;

public class HighscoresActivity extends AppCompatActivity {
    private Intent intent;
    Fragment mapFragment;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

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
                MapFragment f2 = new MapFragment();
                fragmentTransaction.add(R.id.frame_container,f2);
                fragmentTransaction.commit();

            }
        });


//        ImageButton mapButton = (ImageButton) findViewById(R.id.btnMap);
//
//        mapButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //LinearLayout fragContainer = (LinearLayout) findViewById(R.id.fragment_place_holder);
//
//                LinearLayout ll = new LinearLayout(HighscoresActivity.this);
//                ll.setOrientation(LinearLayout.HORIZONTAL);
//
//                //MapFragment mapFragment = new MapFragment();
//                //getFragmentManager().beginTransaction().replace(R.id.fragment_place_holder, mapFragment).commit();
//                //fragContainer.addView(ll);
//
//                /*final FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                MapFragment mapFragment = new MapFragment();
//                transaction.replace(R.id.fragment_place_holder, mapFragment);
//                transaction.commit();*/
//            }
//        });

    }
}
