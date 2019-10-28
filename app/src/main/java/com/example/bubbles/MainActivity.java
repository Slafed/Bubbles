package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bubblesound);
        mediaPlayer.start();

    }

    public void campaign(View v){

        Intent intent = new Intent(this, LevelSelectActivity.class);
        startActivity(intent);
    }

    public void endless(View v){
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }
}