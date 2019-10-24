package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class LevelActivity extends AppCompatActivity {

    private CanvasView canvas;
    public static final String SELECTED_LEVEL = "level number";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new CanvasView((this)));
/*
        //canvas = (CanvasView) findViewById(R.id.gameCanvas);
*/
    }
/*
    public void clearCanvas(View v) {
        canvas.clearCanvas();
    }

    public void start(View v) {
        canvas.start();
    }*/
}
