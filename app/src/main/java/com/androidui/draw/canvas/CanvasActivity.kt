package com.androidui.draw.canvas

import android.graphics.Region
import android.os.Bundle
import com.androidui.ImageViewerActivity
import com.androidui.R
import com.androidui.dialog.WebViewerDialog
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_canvas2.*

/**
 * https://www.jianshu.com/p/762b490403c3
 */
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
        }.setRightClick {
            codeDialog.text("""
class PictureView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    private var mPicture:Picture = Picture()
    private var mPaint = Paint()

    init {
        //一定要加上这句代码，否则有可能绘制不出来
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        mPaint.strokeWidth = 24f
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.RED
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
        recording()//如果要获取到控件绘制完成的宽高，该方法必须在此调用，否则获取不到。
        super.onDraw(canvas)
        canvas?.drawColor(Color.CYAN)
        //绘制方法1
//        mPicture.draw(canvas)
        //绘制方法2
        canvas?.drawPicture(mPicture)
    }

    private fun recording() {
        //开始录制
        val canvas = mPicture.beginRecording(width, height)
        canvas.drawCircle(mPicture.width/2f, mPicture.height/2f, 50f, mPaint)
        mPaint.color = Color.BLUE
        canvas.drawPoint(mPicture.width/2f, mPicture.height/2f, mPaint)
        mPicture.endRecording()
    }
}
            """.trimIndent())
        }

        header5.setLeftClick {
            WebViewerDialog(this).url("canvas/bitmap")
        }

        headerCanvasTranslate.setLeftClick {
            codeDialog.text("""
平移（translate）
作用：移动画布（实际上是移动坐标系，如下图）

具体使用
// 将画布原点向右移200px，向下移100px
canvas.translate(200, 100)
// 注：位移是基于当前位置移动，而不是每次都是基于屏幕左上角的(0,0)点移动
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    canvas?.drawColor(Color.CYAN)
    canvas?.translate(width/2f,height/2f)
    canvas?.drawCircle(0f,0f,100f,paint)
}
            """.trimIndent())
        }

        headerSkewScale.setLeftClick {
            webDialog.url("canvas/canvas_skew")
        }

        sbSkewSx.change { seekBar, progress, fromUser ->
            skewView.sx = progress/20f
            skewView.invalidate()
        }

        sbSkewSy.change { seekBar, progress, fromUser ->
            skewView.sy = progress/20f
            skewView.invalidate()
        }

        headerClipScale.setLeftClick{
            webDialog.url("canvas/canvas_clip")
        }

        rgClip.check {
            when(it){
                R.id.rbClip1->clipView.regionOption = Region.Op.DIFFERENCE
                R.id.rbClip2->clipView.regionOption = Region.Op.REPLACE
                R.id.rbClip3->clipView.regionOption = Region.Op.INTERSECT
            }
            clipView.invalidate()
        }

        headerClipLayer.setLeftClick {
            webDialog.url("canvas/canvas_layer")
        }.setRightClick {
            codeDialog.text("""
override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    canvas?.drawColor(Color.CYAN)
    mWidth = width
    mHeight = height
    paint.color = Color.BLACK
    val text = "初始时图层数量是${'$'}'{canvas?.saveCount}"
    val th = textPaint.getTextRect(text).height().toFloat()
    canvas?.drawText(text,(width - paint.getTextRect(text).width())/2f,th,textPaint)

    canvas?.drawLine(0f, height/2f, width.toFloat(), height/2f, paint)
    canvas?.drawLine(width/2f, 0f, width/2f, height.toFloat(), paint)

    canvas?.save()
    val text1 = "调用save()后图层数量是${'$'}'{canvas?.saveCount}"
    canvas?.drawText(text1,(width - paint.getTextRect(text1).width())/2f,th*2,textPaint)

    paint.color = Color.RED
    canvas?.translate(width/2f,height/2f)
    canvas?.drawRect(0f,0f,100f,50f, paint)

    canvas?.save()
    val text2 = "再次调用save()后图层数量是${'$'}'{canvas?.saveCount}"
    canvas?.drawText(text2,0f,th,textPaint)
    canvas?.rotate(60f)
    paint.color = Color.YELLOW
    canvas?.drawRect(0f,0f,100f,50f, paint)

    canvas?.restoreToCount(1)
    val text3 = "最后调用restoreToCount(1)后图层数量是${'$'}'{canvas?.saveCount}"
    canvas?.drawText(text3,(width - paint.getTextRect(text3).width())/2f,height-th,textPaint)

    paint.color = Color.BLUE
    canvas?.drawRect(0f,0f,100f,50f, paint)

    canvas?.translate(width/2f,height/2f)

}
            """.trimIndent())
        }

        tvAnalysisLayer.text="""
分析：我先新建一个图层，将坐标系移动到视图中央，在原点位置绘制一个红色矩形；接着将坐标系顺时针旋转60度，在原点位置绘制了一个黄色矩形，最后将上面两个图层弹出，在原点绘制了一个蓝色矩形。
        """.trimIndent()

    }

}
