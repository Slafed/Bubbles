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
        /*if(v =  findViewById("button1"))
        {

        }*/
        Intent intent = new Intent(this, LevelActivity.class);

        startActivity(intent);

    }

}
