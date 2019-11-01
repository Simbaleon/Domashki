package com.example.dom;

import android.content.Context;
import android.graphics.*;
import android.view.View;

public class Dz1_8_11_Bolls extends View {
    Paint paint = new Paint();
    int N = 10;
    int j=0;
    float[] x  = new float[N];
    float[] y  = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];
    int[] col = new int[5*N];
    boolean started;
    @Override
    protected void onDraw(Canvas canvas) {
        if (!started) {
            for (int i = 0; i < N; i++) {
                x[i] = (float) (Math.random() * canvas.getWidth());
                y[i] = (float) (Math.random() * canvas.getHeight());
                vx[i] = (float) (Math.random() * 20 - 10);
                vy[i] = (float) (Math.random() * 30 - 10);

            }
            for (int i = 0; i < N * 5; i++) {
                col[i] = (int) (Math.random() * 255 );
            }
            started = true;
        }
        for (int i = 0; i < N - 1; i++) {
            paint.setColor(Color.BLUE);
            canvas.drawLine(x[i], y[i], x[i + 1], y[i + 1], paint);
        }
        j = 0;
        for (int i = 0; i < N; i++) {
            paint.setARGB(col[j], col[j + 1], col[j + 2], 0);
            canvas.drawCircle(x[i], y[i], 35, paint);
            j += 5;
        }
        for (int i = 0; i < N; i++) {

            if (x[i] - 35 < 0 || x[i] + 35 > canvas.getWidth()) {
                vx[i] = -vx[i];
                x[i] += vx[i];
            } else x[i] += vx[i];
            if (y[i] - 35 < 0 || y[i] + 35 > canvas.getHeight()) {
                vy[i] = -vy[i];
                y[i] += vy[i];
            } else y[i] += vy[i];
        }
        invalidate();
    }
    public Dz1_8_11_Bolls(Context context) {
        super(context);
    }
}