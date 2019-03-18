package com.android.systemui.imageview

import android.graphics.BitmapFactory
import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_image_view.*

@LayoutId(R.layout.activity_image_view)
class ImageViewActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        header1.setLeftClick {
            codeDialog.text("""
①background通常指的都是背景,而src指的是内容!!
②当使用src填入图片时,是按照图片大小直接填充,并不会进行拉伸;
而使用background填入图片,则是会根据ImageView给定的宽度来进行拉伸

Java代码中设置background和src属性:
前景(对应src属性):setImageDrawable( );
背景(对应background属性):setBackgroundDrawable( );
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<ImageView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@mipmap/bg1"
    android:src="@mipmap/girl"
    android:scaleType="center"
    />
            """.trimIndent())
        }

        header2.setLeftClick {
            codeDialog.text("""
ImageView为我们提供了adjustViewBounds属性，用于设置缩放时是否保持原图长宽比！

这两个属性 需要adjustViewBounds为true才会生效的~
android:maxHeight:设置ImageView的最大高度
android:maxWidth:设置ImageView的最大宽度
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<ImageView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:src="@mipmap/img1"/>

<ImageView
    android:adjustViewBounds="true"
    android:maxHeight="200dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:src="@mipmap/img1"/>
            """.trimIndent())
        }

        header3.setLeftClick {
            codeDialog.text("""
fitXY:对图像的横向与纵向进行独立缩放,使得该图片完全适应ImageView,但是图片的横纵比可能会发生改变
fitStart:保持纵横比缩放图片,知道较长的边与Image的编程相等,缩放完成后将图片放在ImageView的左上角
fitCenter:同上,缩放后放于中间;
fitEnd:同上,缩放后放于右下角;
center:保持原图的大小，显示在ImageView的中心。当原图的size大于ImageView的size，超过部分裁剪处理。
centerCrop:保持横纵比缩放图片,知道完全覆盖ImageView,可能会出现图片的显示不完全
centerInside:保持横纵比缩放图片,直到ImageView能够完全地显示图片
matrix:默认值，不改变原图的大小，从ImageView的左上角开始绘制原图， 原图超过ImageView的部分作裁剪处理
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
         <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitle"
            android:text="原始图片"/>

        <ImageView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:src="@mipmap/img2"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitleLeft"
            android:text="fitXY:不按比例缩放图片，目标是把图片塞满整个View"/>

        <ImageView
            android:scaleType="fitXY"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:src="@mipmap/img2"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitleLeft"
            android:text="fitStart:保持图片的横纵比缩放,直到该图片能够显示在ImageView组件上,并将缩放好的图片显示在imageView的左上角"/>

        <ImageView
            android:scaleType="fitStart"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:src="@mipmap/img2"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitleLeft"
            android:text="fitCenter:保持图片的横纵比缩放,直到该图片能够显示在ImageView组件上,并将缩放好的图片显示在imageView的正中间"/>

        <ImageView
            android:scaleType="fitCenter"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:src="@mipmap/img2"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitleLeft"
            android:text="fitEnd:保持图片的横纵比缩放,直到该图片能够显示在ImageView组件上,并将缩放好的图片显示在imageView的右下角"/>

        <ImageView
            android:scaleType="fitEnd"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:src="@mipmap/img2"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitleLeft"
            android:text="centerCrop:按横纵比缩放,直接完全覆盖整个ImageView"/>

        <ImageView
            android:scaleType="centerCrop"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:src="@mipmap/img2"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitleLeft"
            android:text="centerInside:按横纵比缩放,使得ImageView能够完全显示这个图片"/>

        <ImageView
            android:scaleType="centerInside"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:src="@mipmap/img2"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitleLeft"
            android:text="matrix:从ImageView的左上角开始绘制原图，原图超过ImageView的部分作裁剪处理"/>

        <ImageView
            android:scaleType="matrix"
            android:layout_width="150dp"
            android:layout_height="150dp"
            android:src="@mipmap/img2"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/tvSmallTitleLeft"
            android:text="center:保持原图的大小，显示在ImageView的中心。当原图的size大于ImageView的size，超过部分裁剪处理。"/>

        <ImageView
            android:scaleType="center"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:src="@mipmap/img2"/>
            """.trimIndent())
        }

        imgRound.setBitmap(BitmapFactory.decodeResource(resources, R.mipmap.img3))

        header4.setRightClick {
            codeDialog.text("""

imgRound.setBitmap(BitmapFactory.decodeResource(resources, R.mipmap.img3))

public class RoundShaping extends ImageView {

    private Bitmap mBitmap;
    private Rect mRect = new Rect();
    private PaintFlagsDrawFilter pdf = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint = new Paint();
    private Path mPath=new Path();
    public RoundShaping(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    //传入一个Bitmap对象
    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }


    private void init() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);// 抗锯尺
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mBitmap == null) return;
        mRect.set(0,0,getWidth(),getHeight());
        canvas.save();
        canvas.setDrawFilter(pdf);
        mPath.addCircle(getWidth() / 2, getWidth() / 2, getHeight() / 2, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);
        canvas.restore();
    }
}
            """.trimIndent())
        }

//        val matrix = Matrix()
//        matrix.preTranslate(100f,100f)
//        matrix.setScale(0.1f,0.1f)
//        img5.imageMatrix = matrix

    }
}
