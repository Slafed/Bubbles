package com.example.bubbles;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class LevelActivity extends AppCompatActivity {

    private CanvasView canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        canvas = (CanvasView) findViewById(R.id.gameCanvas);
    }

    public void clearCanvas(View v) {
        canvas.clearCanvas();
    }
}
