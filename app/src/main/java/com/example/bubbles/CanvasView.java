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
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import javax.security.auth.DestroyFailedException;

public class CanvasView extends SurfaceView {

    private SurfaceHolder holder;
    private CanvasThread canvasThread;

    private Paint circlePaint;
    private Paint scorePaint;
    private Paint winPaint;

    //private float x = 700f; // 0 - (getWidth - 100)
    //private float y = 2200f; //2200
    private int timer = 0;
    private int permaTimer = 0;
    private int timeLimit = 10;
    private  float radius = 100f;
    private int bubbleCount = 0;
    private ArrayList<Bubble> bubblesArray = new ArrayList<Bubble>();
    private int score = 0;
    private int winCondition = 200;

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
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.parseColor("#FFFFFF"));

        scorePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scorePaint.setTextSize(100);

        winPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        winPaint.setTextSize(50);
        if(set == null)
            return;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.parseColor("#5EB7FC"));
        permaTimer++;
        timeLimit = 3600;

        if (score<winCondition && timeLimit-permaTimer > 0) {
            String scorStr = "Score: " + score;
            String winStr = "Reach " + winCondition + " points to win";
            String loseStr = "You Lose In: " + (timeLimit-permaTimer)/60 + " seconds";
            canvas.drawText(scorStr, 150, 125, scorePaint);
            canvas.drawText(winStr, 150, 1500, winPaint);
            canvas.drawText(loseStr,  150, 1700, winPaint);
            timer++;

            if (timer >= 20) {
                bubbleCount++;

                int randX = (int) Math.floor(Math.random() * getWidth() - 700) + 700;

                bubblesArray.add(new Bubble(randX, 2200));


                timer = 0;
            }


            for (int i = 0; i < bubblesArray.size(); i++) {
                if (bubblesArray.get(i).getY() + radius > 0) {
                    move(bubblesArray.get(i), canvas);
                } else {
                    score -= 50;
                    Bubble blub = bubblesArray.get(i);
                    bubblesArray.remove(i);
                    try {
                        blub.destroy();
                    } catch (DestroyFailedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        else if(score>winCondition)
        {
            String winText = "You Win! With " + (timeLimit-permaTimer)/60 + " second to spare";
            canvas.drawText(winText, 350, 700, scorePaint);

        }
        else if(timeLimit-permaTimer <=0){
            canvas.drawText("You Lose! :(", 350, 700, scorePaint);
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

            canvas.drawCircle(b.getX(), b.getY(), radius, circlePaint);

    }

    public boolean onTouchEvent(MotionEvent event){
        boolean value = super.onTouchEvent(event);

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:{

                float tempX = event.getX();
                float tempY = event.getY();

                for(int i = 0; i < bubblesArray.size(); i++)
                {
                    float x = bubblesArray.get(i).getX();
                    float y = bubblesArray.get(i).getY();

                    double dx = Math.pow(tempX - x, 2);
                    double dy = Math.pow(tempY - y, 2);

                    if(dx + dy < Math.pow(radius,2))
                    {
                        score+=10;

                        Bubble popPointer = bubblesArray.get(i);
                        bubblesArray.remove(i);
                        try {
                            popPointer.destroy();
                        } catch (DestroyFailedException e) {

                        }

                        return true;
                    }
                }



                return value;
            }

            case MotionEvent.ACTION_MOVE:{
               return true;
            }
        }
        return value;
    }

}

// AHMER REMEMBER 17:07
