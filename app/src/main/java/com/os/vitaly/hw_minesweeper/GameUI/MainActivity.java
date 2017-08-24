package com.os.vitaly.hw_minesweeper.GameUI;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import com.os.vitaly.hw_minesweeper.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private myClickListener listener;
    private List<ImageButton> buttonsInActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

            switch (v.getId()){
                case R.id.buttonHelp:
                    intent = new Intent( MainActivity.this, HelpActivity.class);
                    break;
                case R.id.buttonStartGame:
                    intent = new Intent( MainActivity.this, ChooseLvlActivity.class);
                    break;
                case R.id.buttonAbout:
                    intent = new Intent( MainActivity.this, AboutActivity.class);
                    break;
                case R.id.buttonRecords:
                    intent = new Intent( MainActivity.this, HighscoresActivity.class);
                    break;
                case R.id.buttonExit:
                    finish();
                    System.exit(0);
                    break;
            }
            MainActivity.this.startActivity(intent);
        }
    }
}
