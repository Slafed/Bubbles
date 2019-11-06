package com.example.bubbles;

import android.graphics.Paint;
import android.view.View;

import javax.security.auth.Destroyable;

public class Bubble implements Destroyable {
    private float x;
    private float y;
    private int popTime;
    private boolean isPopped = false;
    final float radius = 100;

    public Bubble(float x, float y, int popTime, boolean isPopped)
    {
        this.x = x;
        this.y = y;
        this.popTime = popTime;
        this.isPopped = isPopped;
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

    public void setX(float x) { this.x = x; }

    public void setPopped(boolean isPopped) { this.isPopped = isPopped; }
    public boolean getPopped() { return isPopped; }

    public void setPopTime(int popTime) { this.popTime = popTime; }
    public float getPopTime() { return popTime; }

    public void moveX(float x)
    {
        this.x += x;
    }
    public void moveY(float y)
    {
        this.y -=y;
    }



}
