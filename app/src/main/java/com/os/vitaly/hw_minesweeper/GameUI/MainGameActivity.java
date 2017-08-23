package com.os.vitaly.hw_minesweeper.GameUI;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.os.vitaly.hw_minesweeper.R;

public class MainGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageButton btnStart = (ImageButton) findViewById(R.id.buttonStartGame);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGameActivity.this, ChooseLvlActivity.class);
                MainGameActivity.this.startActivity(intent);
            }
        });

        ImageButton btnHelp = (ImageButton) findViewById(R.id.buttonHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGameActivity.this, HelpActivity.class);
                MainGameActivity.this.startActivity(intent);
            }
        });
    }
}
