package com.example.myapplication;

import android.content.Context;
import android.graphics.*;
import android.view.View;

public class Dz1_9_6 extends View {
    int N = 10;
    int j=0;
    Paint paint = new Paint();
    float[] x  = new float[N];
    float[] y  = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];
    @Override
    protected void onDraw(Canvas canvas) {
        drawBalls(canvas);
        addValues(x, vx);
        addValues(y, vy);

        invalidate();
    }
    float rand(float min , float max){
        return (float)(Math.random() * (max - min + 1)) + min;
    }
    void fillRandom(float[] array , float min, float max){
        for (int i = 0; i < array.length; i++){
            array[i] = rand (min, max);
        }
    }
    void addValues(float[] array , float[] values){
        for (int i = 0; i < array.length; i++){
            array[i] += values[i];
        }
    }
    void drawBalls(Canvas canvas){
        for (int i = 0; i < N; i++) {
            canvas.drawCircle(x[i], y[i], 20, paint);
        }
    }
    public Dz1_9_6(Context context) {
        super(context);
        fillRandom(x, 0, 500);
        fillRandom(y, 0, 500);
        fillRandom(vx, -3, 3);
        fillRandom(vy, -3, 3);
    }
}
