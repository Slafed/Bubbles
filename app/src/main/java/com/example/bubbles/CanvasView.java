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

import java.util.ArrayList;

public class CanvasView extends SurfaceView {

    private SurfaceHolder holder;
    private CanvasThread canvasThread;

    private Paint paint;

    private int x = 700; // 0 - (getWidth - 100)
    private int y = 2200; //2200
    private int timer = 0;
    private int bubbleCount = 0;
    private ArrayList<Bubble> bubblesArray = new ArrayList<Bubble>();

    // Bubblearray hold all new bubble, need a final variable for radius

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



        timer++;
        if(timer >= 20)
        {
            bubbleCount++;

            int randX = (int)Math.floor(Math.random()*getWidth()-700) + 700;

            bubblesArray.add(new Bubble(randX,2200));


            timer = 0;
        }



        for(int i = 0; i < bubblesArray.size(); i++)
        {
            move(bubblesArray.get(i), canvas);
        }
/*
        bubblesArray.add(new Bubble(x,2200));

        canvas.drawCircle(bubblesArray.get(0).getX(),y,100, paint);
*/
/*
        int rand = (int)Math.floor(Math.random()*101);

        if((x-100) < 0)
            x+= 10;
        else if((x+100) > getWidth())
            x-= 10;
        else if(rand < 50)
            x+=10;
        else
            x-=10;

        canvas.drawCircle(x,y,100, paint);
*/

    }

    public void move(Bubble b, Canvas canvas)
    {
        int rand = (int)Math.floor(Math.random()*101);
        b.moveY();
       /*
        float tempX = b.getX();
        float tempY = b.getY() - 10;
        */

        if((b.getX()-100) < 0)
            b.moveX(10);
        else if((b.getX()+100) > getWidth())
            b.moveX(-10);
        else if(rand < 50)
            b.moveX(10);
        else
            b.moveX(-10);

        canvas.drawCircle(b.getX(),b.getY(),100, paint);


    }

}

