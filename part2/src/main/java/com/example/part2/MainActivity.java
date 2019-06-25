package com.example.part2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.net.Uri;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button vibrationBtn;
    Button systemBeeBtn;
    Button customBeepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrationBtn=(Button)findViewById(R.id.btn_vibration);
        systemBeeBtn=(Button)findViewById(R.id.btn_system_beep);
        customBeepBtn=(Button)findViewById(R.id.btn_custom_sound);


        vibrationBtn.setOnClickListener(this);
        systemBeeBtn.setOnClickListener(this);
        customBeepBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v==vibrationBtn){

            Vibrator vib=(Vibrator)getSystemService(VIBRATOR_SERVICE);
              vib.vibrate(1000);
        }

        else if(v==systemBeeBtn){

            Uri notification = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone=RingtoneManager.getRingtone(getApplicationContext(),notification);
            ringtone.play();


        }

        else if(v==customBeepBtn){
            MediaPlayer player=MediaPlayer.create(this,R.raw.fallbackring);
            player.start();
        }


    }



}
