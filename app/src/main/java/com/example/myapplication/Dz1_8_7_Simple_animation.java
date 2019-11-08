package com.example.myapplication;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Dz1_8_7_Simple_animation extends View {
    boolean started;
    Paint paint = new Paint();
    int N = 10;
    float[] x = new float[N];
    float[] y = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];

    public Dz1_8_7_Simple_animation(Context context) {
        super(context);
        if (!started) {

            for (int i = 0; i < N; i++) {
                x[i] = (float) (Math.random() * 500);
                y[i] = (float) (Math.random() * 500);
                vx[i] = (float) (Math.random() * 6 - 3);
                vy[i] = (float) (Math.random() * 6 - 3);
            }
            started = true;
        }
    }
    @Override
    protected void onDraw(Canvas canvas){
        for (int i = 0; i < N; i++) {
            canvas.drawCircle(x[i], y[i], 20, paint);
        }
        for (int i = 0; i < N; i++) {
            x[i] += vx[i];
            y[i] += vy[i];
        }
        invalidate();
    }
}
