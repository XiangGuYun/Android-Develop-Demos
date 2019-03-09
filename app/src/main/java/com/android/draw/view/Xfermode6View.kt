package com.android.draw.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.StringUtils
import com.android.R


class Xfermode6View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils, StringUtils{

    val paint:Paint = Paint()
    val path = Path()
    var mode:PorterDuff.Mode

    private var mMatirx: Matrix

    private var bmp: Bitmap

    init {
        //一定要加上这句代码，否则有可能绘制不出来
        setBackgroundColor(Color.CYAN)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paint.strokeWidth = 15f
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.isDither = true//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.isFilterBitmap = true//加快显示速度，本设置项依赖于dither和xfermode的设置
        mode = PorterDuff.Mode.SRC_ATOP
        mMatirx = Matrix()
        bmp = BitmapFactory.decodeResource(resources, R.mipmap.love)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val sc = canvas?.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint, Canvas.ALL_SAVE_FLAG)
        paint.color = Color.RED
        canvas?.translate(width/2f, height/2f)
        canvas?.drawRoundRect(-bmp.width/2f,-bmp.width/2f,bmp.width/2f,bmp.width/2f, 10f,10f,paint)
        paint.color = Color.GREEN
        paint.xfermode = PorterDuffXfermode(mode)
        mMatirx.setTranslate(-bmp.width/2f,-bmp.height/2f)
        canvas?.drawBitmap(bmp, mMatirx,paint)
        paint.xfermode = null
        canvas?.restoreToCount(sc!!)
    }

}