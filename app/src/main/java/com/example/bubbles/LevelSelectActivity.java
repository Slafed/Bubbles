package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
    }

    public void selectedLevel(View v)
    {
        if(v == findViewById(R.id.button1))
        {
            String num ="1";
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra(LevelActivity.SELECTED_LEVEL, num) ;
            startActivity(intent);
        }
        else if(v == findViewById(R.id.button2))
        {
            String num ="2";
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra(LevelActivity.SELECTED_LEVEL, num) ;
            startActivity(intent);
        }
        else if(v == findViewById(R.id.button3))
        {
            String num ="3";
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra(LevelActivity.SELECTED_LEVEL, num) ;
            startActivity(intent);
        }
        else if(v == findViewById(R.id.button4))
        {
            String num ="4";
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra(LevelActivity.SELECTED_LEVEL, num) ;
            startActivity(intent);
        }
        else if(v == findViewById(R.id.button5))
        {
            String num ="5";
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra(LevelActivity.SELECTED_LEVEL, num) ;
            startActivity(intent);
        }


    }

}
