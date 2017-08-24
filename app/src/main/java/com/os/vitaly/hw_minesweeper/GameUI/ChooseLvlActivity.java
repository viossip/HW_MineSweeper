package com.os.vitaly.hw_minesweeper.GameUI;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.os.vitaly.hw_minesweeper.R;

public class ChooseLvlActivity extends AppCompatActivity {

    ImageButton easyBtn, mediumBtn, hardBtn, backBtn;

    public enum Level {
        Easy("Easy"), Medium("Medium"), Hard("Hard");
        private String value;
        Level(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lvl);

        easyBtn = (ImageButton)findViewById(R.id.buttonEasy);
        mediumBtn = (ImageButton)findViewById(R.id.buttonMedium);
        hardBtn = (ImageButton)findViewById(R.id.buttonHard);

        easyBtn.setOnClickListener(new myClickListener());
        mediumBtn.setOnClickListener(new myClickListener());
        hardBtn.setOnClickListener(new myClickListener());

        easyBtn.setTag(Level.Easy);
        mediumBtn.setTag(Level.Medium);
        hardBtn.setTag(Level.Hard);

        backBtn = (ImageButton)findViewById(R.id.buttonBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void startGameActivity(int lvl){
        Intent intent = new Intent(ChooseLvlActivity.this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("level", lvl);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public class myClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ImageButton thisButton = (ImageButton) v;

            thisButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
            thisButton.setPadding(10, 10, 10, 10);

            startGameActivity(((Level)thisButton.getTag()).ordinal());
        }
    }
}
