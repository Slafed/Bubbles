package com.example.bubbles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends SurfaceView {

    private SurfaceHolder holder;
    private CanvasThread canvasThread;

    private Paint paint;

    private int x = 200;
    private int y = 1000;
    public CanvasView(Context context) {
        super(context);

        init(null);

        canvasThread = new CanvasThread(this);

        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder)
            {
                canvasThread.setRunning(true);
                canvasThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
            {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder)
            {
                boolean retry = true;
                canvasThread.setRunning(false);
                while(retry)
                {
                    try
                    {
                        //checks if thread is destroyed
                        canvasThread.join();
                        retry = false;
                    }
                    catch (InterruptedException e)
                    {
                    }

                }
            }
        });
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context,attrs);

        init(attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStylesAttr) {
        super(context,attrs);

        init(attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStylesAttr, int defStyleRes) {
        super(context,attrs);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set)
    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#FFFFFF"));
        if(set == null)
            return;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
       canvas.drawColor(Color.GREEN);
       x+=5;
       y-=8;
       canvas.drawCircle(x,y,200, paint);

    }
}

