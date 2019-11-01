package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LevelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        Button b = (Button) findViewById(R.id.setBut);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Denna", "inside setOnClickListener");
                startActivity(new Intent(LevelSelectActivity.this,SettingsActivity.class));

            }
        });
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
