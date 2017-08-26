package com.os.vitaly.hw_minesweeper.GameUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.os.vitaly.hw_minesweeper.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseLvlActivity extends AppCompatActivity {

    //ImageButton easyBtn, mediumBtn, hardBtn, backBtn;
    private List<ImageButton> buttonsInActivity;
    private myClickListener listener;

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

        listener = new myClickListener();
        buttonsInActivity = new ArrayList();
        getAllButtons((ViewGroup) findViewById(android.R.id.content));

        if (buttonsInActivity.size() > 0 ){
            for (ImageButton b : buttonsInActivity){
                b.setOnClickListener(listener);
            }
        }
    }

    private void getAllButtons(ViewGroup v) {
        for (int i = 0; i < v.getChildCount(); i++) {
            View child = v.getChildAt(i);
            if(child instanceof ViewGroup)
                getAllButtons((ViewGroup)child);
            else if(child instanceof ImageButton)
                buttonsInActivity.add((ImageButton)child);
        }
    }

    public class myClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ImageButton thisButton = (ImageButton) v;

            thisButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
            thisButton.setPadding(20, 20, 20, 20);

            if(v.getId() != R.id.buttonBack){
                Intent intent = new Intent(ChooseLvlActivity.this, GameActivity.class);
                Bundle bundle = new Bundle();

                bundle.putInt("level", (Level.valueOf(thisButton.getTag().toString())).ordinal());
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
                finish();
        }
    }
}
