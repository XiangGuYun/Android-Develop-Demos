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
import com.androidui.R


class Xfermode2View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils, StringUtils{

    val paint:Paint = Paint()
    val path = Path()
    var mode:PorterDuff.Mode

    private var mMatirx: Matrix

    private var bmp: Bitmap?

    init {
        //一定要加上这句代码，否则有可能绘制不出来
        setBackgroundColor(Color.CYAN)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paint.strokeWidth = 15f
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        mode = PorterDuff.Mode.SRC
        mMatirx = Matrix()
        mMatirx.setTranslate(-200f,-200f)
        bmp = BitmapFactory.decodeResource(resources, R.mipmap.love)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val sc = canvas?.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint, Canvas.ALL_SAVE_FLAG)
        paint.color = Color.RED
        canvas?.translate(width/2f, height/2f)
        canvas?.drawCircle(0f,0f,100f,paint)
        paint.color = Color.GREEN
        paint.xfermode = PorterDuffXfermode(mode)
        canvas?.drawBitmap(bmp, mMatirx,paint)
        paint.xfermode = null
        canvas?.restoreToCount(sc!!)
    }

    private fun makeCircle(): Bitmap {
        val bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        val radius = 200 / 2
        canvas.drawCircle((200 / 2).toFloat(), (200 / 2).toFloat(), radius * 1.5f, paint)
        return bitmap
    }

}