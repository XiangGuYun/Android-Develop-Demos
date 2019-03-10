package com.android.systemui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewHandWriting extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mSurfaceHolder;
    //绘图的Canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;
    //画笔
    private Paint mPaint;
    //路径
    private Path mPath;
    private static final String TAG = "pyh";
    public SurfaceViewHandWriting(Context context) {
        this(context, null);
    }

    public SurfaceViewHandWriting(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceViewHandWriting(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPath = new Path();
        mPath.moveTo(0, 100);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            long start = System.currentTimeMillis();
            drawSomething();
            long end = System.currentTimeMillis();
            if (end - start < 100) {
                try {
                    Thread.sleep(100 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    /**
     * 初始化View
     */
    private void initView(){
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
    }

    private void drawSomething() {
        try {
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            //绘制背景
            mCanvas.drawColor(Color.WHITE);
            //绘制路径
            mCanvas.drawPath(mPath, mPaint);
        }catch (Exception e){

        }finally {
            if (mCanvas != null){
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}