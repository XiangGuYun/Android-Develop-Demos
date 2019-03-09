package com.android.draw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.android.R;

public class LogoLoadingView extends View {

    private int totalW,totalH;
    private Paint paint;
    private Bitmap bitmap;
    private int currentTop;
    private RectF rectF;
    private PorterDuffXfermode xfermode;

    public LogoLoadingView(Context context) {
        super(context);
        init();
    }

    public LogoLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        paint=new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setStyle(Paint.Style.FILL);//设置填充样式
        paint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setFilterBitmap(true);//加快显示速度，本设置项依赖于dither和xfermode的设置
        bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.love);//从资源文件中解析获取Bitmap
        xfermode=new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        /**
         * 设置当前矩形的高度为0
         */
        currentTop=bitmap.getHeight();
        rectF=new RectF(0,currentTop,bitmap.getWidth(),bitmap.getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(bitmap.getWidth(),bitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.top=currentTop;
        /**
         * 设置View的离屏缓冲。在绘图的时候新建一个“层”，所有的操作都在该层而不会影响该层以外的图像
         * 必须设置，否则设置的PorterDuffXfermode会无效，具体原因不明
         */
        int sc=canvas.saveLayer(0,0,totalW,totalH,paint,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap,0,0,null);
        paint.setXfermode(xfermode);
        paint.setColor(Color.RED);
        canvas.drawRect(rectF,paint);
        paint.setXfermode(null);
        /**
         * 还原画布，与canvas.saveLayer配套使用
         */
        canvas.restoreToCount(sc);
        if (currentTop>0){
            currentTop--;
            postInvalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        totalW=w;
        totalH=h;
    }
}