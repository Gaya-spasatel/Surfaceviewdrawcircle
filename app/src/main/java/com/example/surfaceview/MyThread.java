package com.example.surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class MyThread extends Thread {
    private boolean flag;
    private Paint paint;
    private SurfaceHolder holder;

    MyThread(SurfaceHolder h){
        this.flag = false;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        this.holder = h;
    }

    public void setRunning(boolean f){
        this.flag = f;
    }

    @Override
    public void run() {
        //super.run();
        while(flag){
            Canvas canvas = holder.lockCanvas();
            Random r = new Random();
            int color = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            int height = canvas.getHeight();
            int weight = canvas.getWidth();
            int maxradius = Math.min(height, weight)/2;
            paint.setColor(color);
            canvas.drawCircle(weight/2, height/2,maxradius, paint);
            holder.unlockCanvasAndPost(canvas);
            try{
                Thread.sleep(1000);

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
