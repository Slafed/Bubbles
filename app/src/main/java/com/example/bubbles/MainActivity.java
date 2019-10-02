package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeBright);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}