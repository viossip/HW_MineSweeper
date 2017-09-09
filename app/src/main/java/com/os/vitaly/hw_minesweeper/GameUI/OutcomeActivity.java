package com.os.vitaly.hw_minesweeper.GameUI;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.os.vitaly.hw_minesweeper.R;

public class OutcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);
        ImageButton btnOk = (ImageButton) findViewById(R.id.btnOk);
        Bundle bundle = getIntent().getExtras();
        TextView text = (TextView) findViewById(R.id.title_outcome_text);
        EditText text1 = (EditText) findViewById(R.id.nameEdit);
        final ImageView outcomeAnimation = (ImageView) findViewById(R.id.outcome_animation);

        int[] WinLose = bundle.getIntArray("isWin");

        if (WinLose[0] == 1) {
            text.setText("you have won after " + WinLose[1] + ":" + WinLose[2]);
            text1.setVisibility(View.VISIBLE);
            outcomeAnimation.setBackgroundResource(R.drawable.animation_win);

        } else {
            text.setText("you have lose and your  time is : " + WinLose[1] + ":" + WinLose[2]);
            outcomeAnimation.setBackgroundResource(R.drawable.animation_loss);
        }

        AnimationDrawable animation = (AnimationDrawable) outcomeAnimation.getBackground();
        animation.start();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OutcomeActivity.this, ChooseLvlActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                OutcomeActivity.this.startActivity(intent);
            }
        });
    }
}
