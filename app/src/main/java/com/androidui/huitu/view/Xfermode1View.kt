package com.androidui.huitu.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.DensityUtils
import com.kotlinlib.other.StringUtils
import com.scwang.smartrefresh.layout.util.DensityUtil
import android.R.attr.y
import android.R.attr.x




class Xfermode1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils, StringUtils{

    val paint:Paint = Paint()
    val path = Path()
    var mode:PorterDuff.Mode

    init {
        //一定要加上这句代码，否则有可能绘制不出来
        setBackgroundColor(Color.CYAN)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paint.strokeWidth = 15f
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        mode = PorterDuff.Mode.SRC
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.RED
        canvas?.translate(width/2f, height/2f)
        canvas?.drawCircle(-100f,0f,200f,paint)
        paint.color = Color.GREEN
        paint.xfermode = PorterDuffXfermode(mode)
        canvas?.drawCircle(100f,0f,200f,paint)
        paint.xfermode = null
    }

}