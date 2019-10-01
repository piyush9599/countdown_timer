package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    boolean counterisactive=false;
    SeekBar timerseekbar;
    Button gobutton;
    CountDownTimer countdowntimer;
    public void resettimer(){
        textview.setText("0:30");
        timerseekbar.setProgress(30);
        timerseekbar.setEnabled(true);
        countdowntimer.cancel();
        gobutton.setText("GO");
        counterisactive=false;
    }
    public void buttonclicked(View view){
        if(counterisactive){
            resettimer();


        }else {
            counterisactive = true;
            timerseekbar.setEnabled(false);
            gobutton.setText("STOP!");
            Log.d("msg", "clicked");
             countdowntimer= new CountDownTimer(timerseekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updatetime((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.bell);
                    mediaplayer.start();
                    resettimer();
                }
            }.start();
        }
    }
    public void updatetime(int secondsleft){
        int minutes = secondsleft/60;
        int secound = secondsleft-(minutes*60);
        String secondstring = Integer.toString(secound);
        if(secound<=9){
            secondstring="0"+secondstring;
        }
        textview.setText(Integer.toString(minutes)+":"+ secondstring);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerseekbar  = findViewById(R.id.timerseekBar);
       textview= findViewById(R.id.countdowntextview);
       gobutton=findViewById(R.id.GoButton);
        timerseekbar.setMax(1600);
        timerseekbar.setProgress(30);

        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatetime(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
