package com.mobilyte.mymapapplication;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by root on 16/3/16.
 */
public class MyView extends View {

    ArrayList<Pointr> points;

    public MyView(Context context) {
        super(context);
        points = new ArrayList<>();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        points = new ArrayList<>();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        Path path1 = new Path();
        boolean first = true;
        for (int i = 0; i < points.size(); i += 2) {
            Pointr point = points.get(i);
            if (first) {
                first = false;
                path.moveTo(point.x, point.y);
                path1.moveTo(0, 0);
                path1.lineTo(getHeight(), getWidth());
            } else if (i < points.size() - 1) {
                Pointr next = points.get(i + 1);
                path1.lineTo(point.x, point.y);
                path.quadTo(point.x, point.y, next.x, next.y);
            } else {
                path.lineTo(point.x, point.y);
                path1.lineTo(point.x, point.y);
            }
        }
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(13);
        paint.setAlpha(0);
        paint.setColor(Color.WHITE);
        canvas.drawPath(path, paint);
        Paint paint1 = new Paint();

        paint1.setColor(Color.RED);
        float radius = 50.0f;
        CornerPathEffect corEffect = new CornerPathEffect(radius);
        paint.setPathEffect(corEffect);
//        Path path1 = new Path();
        /*path1.moveTo(20, 20);
        path1.lineTo(400, 20);
        path1.lineTo(600, 300);
        path1.lineTo(400, 400);
        path1.lineTo(20, 400);*/

//        path1.close();
        canvas.drawPath(path1, paint1);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_UP) {
            Pointr point = new Pointr();
            point.x = event.getX();
            point.y = event.getY();
            points.add(point);
            invalidate();
            Log.d("TAG", "point: " + point);
            return true;
        }
        return super.onTouchEvent(event);
    }
}
