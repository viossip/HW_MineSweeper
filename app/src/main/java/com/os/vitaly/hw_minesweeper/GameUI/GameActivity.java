package com.os.vitaly.hw_minesweeper.GameUI;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.os.vitaly.hw_minesweeper.Controls.GameListener;
import com.os.vitaly.hw_minesweeper.Controls.GameRunner;
import com.os.vitaly.hw_minesweeper.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements GameListener {
    private static final int ANIMATION_DURATION = 3400;
    public static ChooseLvlActivity.Level lvl;
    public TextView timeText;
    public  TextView minesNumber;
    public GameRunner gm;
    private DisplayMetrics metrics;
    private int[] gameResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        lvl = ChooseLvlActivity.Level.values()[bundle.getInt("level")];
        setContentView(R.layout.activity_game);

        timeText = (TextView) findViewById(R.id.time_value);
        minesNumber = (TextView) findViewById(R.id.mines_left_text_view2);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ImageButton btnExit = (ImageButton) findViewById(R.id.btnExitGame);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gm.timerReset();
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                GameActivity.this.startActivity(intent);
                finish();
            }
        });

//        ImageButton btnStartNewGame = (ImageButton) findViewById(R.id.btnStartNewGame);
//        btnStartNewGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                restart();
//            }
//        });

        gm = GameRunner.getInstance();
        gm.setLevel(lvl.getValue());
        gm.createGrid(this, true);

        gm.setGameListener(this);
        gm.startTimer();

        minesNumber.setText(String.valueOf(gm.getMines()));
        ImageButton btnStartNewGame = (ImageButton) findViewById(R.id.btnStartNewGame);
        btnStartNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gm.timerReset();//reset timer
                restart();
            }
        });
    }

    public void onBackPressed() {
        gm.timerReset();
        Intent intent = new Intent(GameActivity.this, ChooseLvlActivity.class);
        GameActivity.this.startActivity(intent);

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
                    if (gm.getSeconds() < 10)
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
        gameResultList= new int [3];
        //Intent intent = new Intent(GameActivity.this, OutcomeActivity.class);

        //Bundle bundle = new Bundle();
        gameResultList[1]=gm.getMinutes();
        gameResultList[2]=gm.getSeconds();
        if(isWin) {
            gameResultList[0]=1;
            //bundle.putIntArray("isWin", gameResultList);
        }else {
            gameResultList[0]=0;
            //bundle.putIntArray("isWin", gameResultList);
            for (int i =0 ; i<10; i++)
                for(int j = 0; j<10;j++)
                    animateBoard(gm.getCellAt(i,j), ANIMATION_DURATION);
        }
        //intent.putExtras(bundle);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(GameActivity.this, OutcomeActivity.class);
                bundle.putIntArray("isWin", gameResultList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }, 2000);

        //startActivity(intent);
    }

    @Override
    public void minesUpdated() {
//        int m = Integer.parseInt(minesNumber.getText().toString());
        if (gm.getMines()>0)
        minesNumber.setText(String.valueOf((gm.getMines())));
        else {
            minesNumber.setText(String.valueOf((gm.getMines())));
            Toast.makeText(this, "you have no more flags", Toast.LENGTH_SHORT).show();
        }
    }

    //  Animation of game board when mine explodes.
    public void animateBoard(View view, long duration) {
        Random random = new Random();
        boolean randBool = random.nextBoolean();

        ObjectAnimator moveHorizantal = ObjectAnimator.ofFloat(view, "x", view.getX(), metrics.widthPixels * (random.nextBoolean() ? 1 : -1));
        ObjectAnimator moveVertical = ObjectAnimator.ofFloat(view, "y", view.getY(), metrics.widthPixels * (random.nextBoolean() ? 1 : -1));
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", randBool == true ? 0f : 360f, randBool == true ? 360f : 0f);

        rotate.setInterpolator(new DecelerateInterpolator());
        moveVertical.setInterpolator(new DecelerateInterpolator());
        moveHorizantal.setInterpolator(new DecelerateInterpolator());

        rotate.setDuration(duration);
        moveVertical.setDuration(duration);
        moveHorizantal.setDuration(duration);

        rotate.start();
        moveVertical.start();
        moveHorizantal.start();
    }
}
