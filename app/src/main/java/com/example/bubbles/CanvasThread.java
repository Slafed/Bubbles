package com.example.bubbles;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class CanvasThread extends Thread{

    private CanvasView view;
    private boolean running = false;

    public CanvasThread( CanvasView view)
    {
        this.view = view;
    }

    public void setRunning(boolean run)
    {
        running = run;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run()
    {
        while(running)
        {
            Canvas c = null;
            try
            {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder())
                {
                    view.onDraw(c);
                }
            }
            finally
            {
                if(c != null)
                {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
