package com.os.vitaly.hw_minesweeper.GameUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.os.vitaly.hw_minesweeper.Controls.GameRunner;
import com.os.vitaly.hw_minesweeper.Controls.GameListener;
import com.os.vitaly.hw_minesweeper.R;

public class GameActivity extends AppCompatActivity implements GameListener {
    public static ChooseLvlActivity.Level lvl;
    public TextView timeText;
    public  TextView minesNumber;
    public GameRunner gm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        lvl = ChooseLvlActivity.Level.values()[bundle.getInt("level")];
        setContentView(R.layout.activity_game);

        timeText = (TextView) findViewById(R.id.time_value);
        minesNumber = (TextView) findViewById(R.id.mines_left_text_view2);



        ImageButton btnExit = (ImageButton) findViewById(R.id.btnExitGame);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                GameActivity.this.startActivity(intent);
                finish();
            }
        });

        ImageButton btnStartNewGame = (ImageButton) findViewById(R.id.btnStartNewGame);
        btnStartNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });

        gm = GameRunner.getInstance();
        gm.setLevel(lvl.getValue());
        gm.createGrid(this, true);

        gm.setGameListener(this);
        gm.startTimer();

        minesNumber.setText(String.valueOf(gm.getMines()));
    }



    public ChooseLvlActivity.Level getLevel() {
        return lvl;
    }

    private void restart() {
        this.recreate();
    }



    @Override
    public void timeChanged() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (gm.getMinutes() < 10) {
                    if (gm.getMinutes() < 10)
                        timeText.setText("0" + gm.getMinutes() + ":0" + gm.getSeconds());
                    else
                        timeText.setText("0" + gm.getMinutes() + ":" + gm.getSeconds());
                }
                else {
                    if (gm.getSeconds() < 10)
                        timeText.setText(gm.getMinutes() + ":0" + gm.getSeconds());
                    else
                        timeText.setText(gm.getMinutes() + ":" + gm.getSeconds());
                }
            }
        });
    }

    @Override
    public void onEndGame(boolean isWin) {
        Intent intent = new Intent(GameActivity.this, OutcomeActivity.class);
        Bundle bundle = new Bundle();
        if(isWin)
            bundle.putInt("isWin", 1);
        else
            bundle.putInt("isWin", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void minesUpdated() {
        int m = Integer.parseInt(minesNumber.getText().toString());
        minesNumber.setText(String.valueOf(--m));
    }
}
