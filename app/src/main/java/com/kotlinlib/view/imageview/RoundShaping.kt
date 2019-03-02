package com.kotlinlib.view.imageview

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

/**
 * 用来对方形的View进行圆角塑形，需要和方形View放在FrameLayout中，位置置前。
 * @property paint Paint
 * @property path Path
 * @property mode Mode
 * @constructor
 */
class RoundShaping @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val paint:Paint = Paint()
    val path = Path()
    var mode: PorterDuff.Mode

    init {
        //一定要加上这句代码，否则有可能绘制不出来
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        paint.isDither = true//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.isFilterBitmap = true//加快显示速度，本设置项依赖于dither和xfermode的设置
        mode = PorterDuff.Mode.XOR
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val sc = canvas?.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint, Canvas.ALL_SAVE_FLAG)
        canvas?.drawRect(0f,0f,width.toFloat(),height.toFloat(),paint)
        paint.xfermode = PorterDuffXfermode(mode)
        canvas?.drawRoundRect(0f,0f,width.toFloat(),height.toFloat(), 25f, 25f, paint)
        paint.xfermode = null
        canvas?.restoreToCount(sc!!)
    }

}

