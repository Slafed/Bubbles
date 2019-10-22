package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class Pop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        Log.d("Denna", "inside Pop onCreate");
        //commit
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        int width = dm.widthPixels;
        int height = dm.heightPixels;

        Log.d("Denna", "inside Pop onCreate: width: " + width + " height: " + height);
        getWindow().setLayout((int)(width*.8), (int)(height*.6));

    }
}
