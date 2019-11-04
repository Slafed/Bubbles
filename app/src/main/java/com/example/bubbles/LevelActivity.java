package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import static java.lang.Integer.parseInt;

public class LevelActivity extends AppCompatActivity {

    private CanvasView canvas;
    public static final String SELECTED_LEVEL = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int num = parseInt(intent.getStringExtra(SELECTED_LEVEL));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new CanvasView((this), num));
/*
        //canvas = (CanvasView) findViewById(R.id.gameCanvas);
*/
        if(num == 1)
        {

        }
    }
/*
    public void clearCanvas(View v) {
        canvas.clearCanvas();
    }

    public void start(View v) {
        canvas.start();
    }*/
}
