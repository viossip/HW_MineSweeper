package com.os.vitaly.hw_minesweeper.GameUI;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.os.vitaly.hw_minesweeper.R;
import com.os.vitaly.hw_minesweeper.Services.GpsLocation;

import static com.os.vitaly.hw_minesweeper.R.layout.gps;

public class OutcomeActivity extends AppCompatActivity {
    private int[] WinLose ;
    Context mcontext;
    GpsLocation gps;
    private double latitude;
    private double longitude;
    private int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);
        ImageButton btnOk = (ImageButton) findViewById(R.id.btnOk);
        Bundle bundle = getIntent().getExtras();
        TextView text = (TextView) findViewById(R.id.title_outcome_text);
        final EditText nameEdit = (EditText) findViewById(R.id.nameEdit);
        TextView nameLabel= (TextView) findViewById(R.id.nameLabel);
        final ImageView outcomeAnimation = (ImageView) findViewById(R.id.outcome_animation);
        mcontext=this;
        WinLose = bundle.getIntArray("isWin");

        if (WinLose[0] == 1) {
            text.setText("you have won after " + WinLose[1] + ":" + WinLose[2]);
            nameEdit.setVisibility(View.VISIBLE);
            nameLabel.setVisibility(View.VISIBLE);
            outcomeAnimation.setBackgroundResource(R.drawable.animation_win);
            time = WinLose[1]*60 + WinLose[2];


        } else {
            text.setText("you have lose and your  time is : " + WinLose[1] + ":" + WinLose[2]);
            outcomeAnimation.setBackgroundResource(R.drawable.animation_loss);
        }

        AnimationDrawable animation = (AnimationDrawable) outcomeAnimation.getBackground();
        animation.start();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WinLose[0] == 1){
                    if (ContextCompat.checkSelfPermission(mcontext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(OutcomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
                    else{
                        // Toast.makeText(mcontext,"You need have granted permission",Toast.LENGTH_SHORT).show();
                        gps = new GpsLocation(mcontext, OutcomeActivity.this);
                        if (gps.canGetLocation()) {
                            latitude = gps.getLatitude();
                            longitude = gps.getLongitude();


                            Toast.makeText(getApplicationContext(), "your location is\n Lat: " + latitude + "\nLong : " + longitude, Toast.LENGTH_SHORT).show();
                            EditText nameEdit = (EditText)findViewById(R.id.nameEdit);
                            MainActivity.currentDB.insertData(nameEdit.getText().toString(), time, latitude+"",longitude+"");

                            Intent intent = new Intent(OutcomeActivity.this,HighscoresActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            gps.showSettingsAlert();
                            latitude = gps.getLatitude();
                            longitude = gps.getLongitude();
                        }



                        //go to result screen
                    }

                    //insert to db




                }

                }


        });
    }
}
