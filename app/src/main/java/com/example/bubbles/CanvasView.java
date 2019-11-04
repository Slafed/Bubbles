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
    private Paint willLosePaint;
    private Paint losePaint;
    private Paint countPaint;
    private Paint eCountPaint;
    private Paint instrPaint;
    private Paint unitPaint;
    //private float x = 700f; // 0 - (getWidth - 100)
    //private float y = 2200f; //2200
    private int timer = 0;
    private float speed = 20;
    private float mSpeed = 20;
    private int permaTimer = 0;
    private int timeLimit = 0;
    private  float radius = 100f;
    private int bubbleCount = 0;
    private ArrayList<Bubble> bubblesArray = new ArrayList<Bubble>();
    private int score = 0;
    private int winCondition;
    private int levelNum = 0;
    private int bgrdOpac = 95;
    private int bgrdRed  = 0;
    private int bgrdGreen = 190;
    private int bgrdBlue = 255;
    // Bubblearray hold all new bubble, need a final variable for radius

    public CanvasView(Context context, int level) {
        super(context);
        levelNum=level;
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
        scorePaint.setColor(Color.parseColor("#22000000"));

        willLosePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        willLosePaint.setTextSize(50);
        willLosePaint.setColor(Color.parseColor("#22000000"));

        losePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        losePaint.setTextSize(70);
        losePaint.setColor(Color.parseColor("#22000000"));

        countPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        countPaint.setTextSize(500);
        countPaint.setColor(Color.parseColor("#22000000"));

        eCountPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        eCountPaint.setTextSize(700);
        eCountPaint.setColor(Color.parseColor("#22000000"));

        instrPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        instrPaint.setTextSize(70);
        instrPaint.setColor(Color.parseColor("#22000000"));

        unitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        unitPaint.setTextSize(40);
        unitPaint.setColor(Color.parseColor("#22000000"));

        if(set == null)
            return;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (levelNum == 1)
        {
            timeLimit = 3600;
            winCondition = 2000;

            if(permaTimer%6 == 0 && permaTimer>300)
            {
                if(bgrdGreen<255)
                    bgrdGreen++;
                if(bgrdBlue>0)
                    bgrdBlue--;
            }
        }
        else if (levelNum == 2)
        {
            timeLimit = 4200;
            winCondition=3000;

            if(permaTimer%6 == 0 && permaTimer>300)
            {
                /*if(permaTimer%12==0 && bgrdOpac<130)
                {
                    bgrdOpac++;
                }*/
                if(bgrdRed<153)
                    bgrdRed+=2;
                if(bgrdGreen<253)
                    bgrdGreen++;
                if(bgrdBlue>0)
                    bgrdBlue--;
            }
        }
        else if (levelNum == 3)
        {
            timeLimit = 5400;
            winCondition=5000;

            if(permaTimer%6 == 0 && permaTimer>300)
            {
                /*if(permaTimer%12==0 && bgrdOpac<130)
                {
                    bgrdOpac++;
                }*/
                if(bgrdRed<253)
                    bgrdRed+=2;
                if(bgrdGreen<223)
                    bgrdGreen++;
                if(bgrdBlue>0)
                    bgrdBlue--;
            }
        }
        else if (levelNum == 4)
        {
            timeLimit = 5400;
            winCondition=8000;

            if(permaTimer%6 == 0 && permaTimer>300)
            {
                /*if(permaTimer%12==0 && bgrdOpac<130)
                {
                    bgrdOpac++;
                }*/
                if(bgrdRed<253)
                    bgrdRed+=2;
                if(bgrdGreen>143)
                    bgrdGreen--;
                if(bgrdBlue>0)
                    bgrdBlue--;
            }
        }
        else if (levelNum == 5)
        {
            timeLimit = 7200;
            winCondition=15000;

            if(permaTimer%6 == 0 && permaTimer>300)
            {
                /*if(permaTimer%12==0 && bgrdOpac<130)
                {
                    bgrdOpac++;
                }*/
                if(bgrdRed<253)
                    bgrdRed+=2;
                if(bgrdGreen>83)
                    bgrdGreen--;
                if(bgrdBlue>0)
                    bgrdBlue--;
            }
        }
        else if (levelNum == 0)
        {
            if (permaTimer == 0)
            {
                score = 5000;
            }
            if(score>0)
            {
                timeLimit=2147483647;
                winCondition = 2147483647;
            }

            if(permaTimer%6 == 0)
            {
                /*if(permaTimer%12==0 && bgrdOpac<130)
                {
                    bgrdOpac++;
                }*/
                if(bgrdRed<253)
                    bgrdRed+=2;
                if(bgrdGreen>143)
                    bgrdGreen--;
                if(bgrdBlue>0)
                    bgrdBlue--;
            }
        }

        canvas.drawARGB(bgrdOpac, bgrdRed, bgrdGreen,bgrdBlue);
        if (score<winCondition && timeLimit-permaTimer > 0) {
            permaTimer++;
            if(permaTimer<300)
            {
                String instrStr = "";
                String instrStr2 = "";
                String countStr = "";

                if(levelNum == 0)
                {
                    instrStr = "Survive As Long As Possible!";
                    instrStr2 = "If score Reaches 0 you lose";
                    countStr = "" + (300 - permaTimer) / 60;
                }
                else
                {
                    instrStr = "Reach " + winCondition + " points to win";
                    countStr = "" + (300 - permaTimer) / 60;
                }

                canvas.drawText(instrStr, 100, 200, instrPaint);
                canvas.drawText(instrStr2, 100, 280, instrPaint);
                canvas.drawText(countStr, 400, 1000, countPaint);
            }
            else
            {

                if (levelNum > 0) {
                    String scorStr = "Score: " + score + " / " + winCondition;
                    String loseStr = "You Lose In: " + (timeLimit - permaTimer) / 60 + " seconds";

                    canvas.drawText(scorStr, 50, 125, scorePaint);
                    canvas.drawText(loseStr, 150, 700, willLosePaint);
                }
                else
                {
                    String scorStr = "Score: " + score;
                    String countTxt = "" + permaTimer/60;
                    int countX = 50;
                    String unitStr = "seconds";

                    canvas.drawText(scorStr, 50, 125, scorePaint);
                    canvas.drawText(countTxt, countX, 1025,eCountPaint);
                    //canvas.drawText(unitStr, countX+10, 725,unitPaint);
                }

                timer++;

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

                if (levelNum == 1) {
                    speed = 30;
                } else if (levelNum == 2) {
                    speed = 20;

                } else if (levelNum == 3) {
                    speed = 20;
                } else if (levelNum == 4) {
                    speed = 15;
                } else if (levelNum == 5) {
                    speed = 15;
                } else if (levelNum == 0) {
                    if (speed > 12) {
                        speed = 20 - permaTimer /300;
                    }
                    if (score < 0) {
                        timeLimit = 0;
                    }
                }

                if (timer >= speed) {
                    bubbleCount++;

                    int randX = (int) Math.floor(Math.random() * getWidth() - 700) + 700;

                    bubblesArray.add(new Bubble(randX, 2200));


                    timer = 0;
                }
//bruh
            }
        }
        else if(score>=winCondition ||(levelNum == 0 && score<=0))
        {
            if(levelNum==0)
            {
                String enduredTxt = "You Survived for " + permaTimer/60 + " seconds";
                canvas.drawText(enduredTxt, 0, 800, losePaint);
            }
            else
            {
                String winText = "You Win!";
                String winTime = "With " + (timeLimit - permaTimer) / 60 + " second to spare";
                canvas.drawText(winText, 350, 700, scorePaint);
                canvas.drawText(winTime, 0, 800, scorePaint);
            }
        }
        else if(timeLimit-permaTimer <=0){

            String lostStr = "You Lose! :(";
            String winAwayStr = "You were at "  + score + " points";
            String winAwayStr2 = "You needed " + winCondition + " points";
            canvas.drawText(lostStr, 50, 500, losePaint);
            canvas.drawText(winAwayStr, 50, 580, losePaint);
            canvas.drawText(winAwayStr2, 50, 660, losePaint);
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

        if(levelNum == 1)
        {
            b.moveY(10);
        }
        else if(levelNum == 2)
        {
            b.moveY(15);
        }
        else if(levelNum == 3)
        {
            b.moveY(20);
        }
        else if(levelNum == 4)
        {
            b.moveY(25);
        }
        else if(levelNum == 5)
        {
            b.moveY(30);
        }
        else if(levelNum == 0)
        {

            if(mSpeed <135)
                mSpeed = 20 + permaTimer/180;
            b.moveY(mSpeed);
        }
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

                    if(dx + dy < 3.14*(Math.pow(radius,2)))
                    {
                            score+=50;


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
