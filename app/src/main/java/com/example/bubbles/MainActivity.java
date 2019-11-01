package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public int length;
    public boolean music = true;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppThemeBright);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.tutorial);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Denna", "inside setOnClickListener");
                startActivity(new Intent(MainActivity.this,Pop.class));

            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.bubblesound);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(music);
        mediaPlayer.start();



    }

    public void campaign(View v){

        Intent intent = new Intent(this, LevelSelectActivity.class);
        startActivity(intent);
    }

    public void endless(View v){
        Intent intent = new Intent(this, LevelActivity.class);
        intent.putExtra(LevelActivity.SELECTED_LEVEL, "0") ;
        startActivity(intent);
    }


    public void onPauseB(View v){
        if(music){
            mediaPlayer.pause();
            length = mediaPlayer.getCurrentPosition();
            music = false;
        }

        else{
            mediaPlayer.seekTo(length);
            mediaPlayer.start();
            music = true;
        }

    }

    public void onPause(){
        super.onPause();
        mediaPlayer.pause();
    }

    public void onResume(){
        super.onResume();

        if(music){
            mediaPlayer.start();
        }

    }

}