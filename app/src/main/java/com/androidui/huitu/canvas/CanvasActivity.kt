package com.androidui.huitu.canvas

import android.os.Bundle
import com.androidui.ImageViewerActivity
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_canvas2.*

@LayoutId(R.layout.activity_canvas2)
class CanvasActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        readView1.setText("""
Canvas对象 & 获取的方法有4个：

// 方法1
// 利用空构造方法直接创建对象
Canvas canvas = new Canvas()；

// 方法2
// 通过传入装载画布Bitmap对象创建Canvas对象
// CBitmap上存储所有绘制在Canvas的信息
Canvas canvas = new Canvas(bitmap)

// 方法3
// 通过重写View.onDraw（）创建Canvas对象
// 在该方法里可以获得这个View对应的Canvas对象

@Override
protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //在这里获取Canvas对象
}

// 方法4
// 在SurfaceView里画图时创建Canvas对象

SurfaceView surfaceView = new SurfaceView(this);
// 从SurfaceView的surfaceHolder里锁定获取Canvas
SurfaceHolder surfaceHolder = surfaceView.getHolder();
//获取Canvas
Canvas c = surfaceHolder.lockCanvas();

// ...（进行Canvas操作）
// Canvas操作结束之后解锁并执行Canvas
surfaceHolder.unlockCanvasAndPost(c);

官方推荐方法4来创建并获取Canvas，原因：

SurfaceView里有一条线程是专门用于画图，所以方法4的画图性能最好，并适用于高质量的、刷新频率高的图形
而方法3刷新频率低于方法3，但系统花销小，节省资源
        """.trimIndent())

        ivDrawPicture.click {
            go(ImageViewerActivity::class.java, ImageViewerActivity.IMG_ID to R.mipmap.canvas_draw)
        }

        header1.setLeftClick {
            codeDialog.text("""
作用：将颜色填充整个画布，常用于绘制底色

具体使用
// 传入一个Color类的常量参数来设置画布颜色
// 绘制蓝色
canvas.drawColor(Color.BLUE);
            """.trimIndent())
        }

        header4.setLeftClick {
            codeDialog.text("""
作用：绘制矢量图的内容，即绘制存储在矢量图里某个时刻Canvas绘制内容的操作

矢量图（Picture）的作用：存储（录制）某个时刻Canvas绘制内容的操作

应用场景：绘制之前绘制过的内容
1.相比于再次调用各种绘图API，使用Picture能节省操作 & 时间
2.如果不手动调用，录制的内容不会显示在屏幕上，只是存储起来

特别注意：使用绘制矢量图时前请关闭硬件加速，以免引起不必要的问题！

具体使用方法：
// 获取宽度
Picture.getWidth ()；

// 获取高度
Picture.getHeight ()

// 开始录制
// 即将Canvas中所有的绘制内容存储到Picture中
// 返回一个Canvas
Picture.beginRecording（int width, int height）

// 结束录制
Picture.endRecording ()

// 将Picture里的内容绘制到Canvas中
Picture.draw (Canvas canvas)

// 还有两种方法可以将Picture里的内容绘制到Canvas中
// 方法2：Canvas.drawPicture（）
// 方法3：将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。

// 下面会详细介绍

一般使用的具体步骤
// 步骤1：创建Picture对象
Picture mPicture = new Picture();

// 步骤2：开始录制
mPicture.beginRecording（int width, int height）;

// 步骤3：绘制内容 or 操作Canvas
canvas.drawCircle(500,500,400,mPaint);
...（一系列操作）

// 步骤4：结束录制
mPicture.endRecording ();

步骤5：某个时刻将存储在Picture的绘制内容绘制出来
mPicture.draw (Canvas canvas);
            """.trimIndent())
        }
    }

}
