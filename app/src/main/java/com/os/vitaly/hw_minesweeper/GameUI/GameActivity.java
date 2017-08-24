package com.os.vitaly.hw_minesweeper.GameUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.os.vitaly.hw_minesweeper.Controls.GameRunner;
import com.os.vitaly.hw_minesweeper.Entities.GridBoard;
import com.os.vitaly.hw_minesweeper.R;

public class GameActivity extends AppCompatActivity {
    ChooseLvlActivity.Level lvl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();
        lvl = ChooseLvlActivity.Level.values()[bundle.getInt("level")] ;

        GridBoard gb = (GridBoard)findViewById(R.id.gridBoard );
        gb.sttLevel(lvl);

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

        GameRunner.getInstance(lvl).createGrid(this);
    }
    public ChooseLvlActivity.Level getLevel(){
        return lvl;
    }
    private void restart() {
        this.recreate();
    }
}
