package com.os.vitaly.hw_minesweeper.GameUI;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.os.vitaly.hw_minesweeper.R;

/*public enum Level {
    Easy("Easy"), Normal("Normal"), Hard("Hard");
    */
public class ChooseLvlActivity extends AppCompatActivity {

    private ImageButton buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lvl);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        buttonBack = (ImageButton)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
