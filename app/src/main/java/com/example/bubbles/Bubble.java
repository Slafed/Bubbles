package com.example.bubbles;

import android.graphics.Paint;
import android.view.View;

import javax.security.auth.Destroyable;

public class Bubble implements Destroyable {
    private float x;
    private float y;
    final float radius = 100;

    public Bubble(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void moveX(float x)
    {
        this.x += x;
    }
    public void moveY()
    {
        this.y -=10;
    }



}
