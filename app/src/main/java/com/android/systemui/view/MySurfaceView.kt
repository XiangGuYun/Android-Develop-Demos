package com.android.systemui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import com.kotlinlib.other.BaseInterface
import com.kotlinlib.view.surfaceview.DrawView

class MySurfaceView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        DrawView(context, attrs, defStyleAttr), BaseInterface{

    val paint = Paint()
    val paint1 = Paint()
    val paint2 = Paint()
    val paint3 = Paint()
    val paint4 = Paint()
    val paint5 = Paint()
    val path = Path()

    var x = 0
    var y = 0

    init {
        drawingSpeed = 150
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 15f

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        path.moveTo(-50f, height/2f)
    }

    var degree = 0f
    var scale = 1.0f
    var needBigger = true

    override fun drawing(canvas: Canvas, index: Int) {
        degree+=10
        canvas.rotate(degree, width/2f, height/2f)
        if(scale<=1.6f&&needBigger){
            scale *= 1.1f
            canvas.scale(scale, scale, width/2f, height/2f)
        }else{
            needBigger = false
            if(scale>=1.0f){
                scale /= 1.1f
                canvas.scale(scale, scale, width/2f, height/2f)
            }else{
                needBigger = true
            }

        }

        canvas.drawColor(randomColor())
        canvas.drawCircle(width/2f, height/2f, 300f, paint4.apply { color = randomColor() })
        canvas.drawCircle(width/2f, height/2f, 250f, paint4.apply { color = randomColor() })
        canvas.drawCircle(width/2f, height/2f, 200f, paint3.apply { color = randomColor() })
        canvas.drawCircle(width/2f, height/2f, 150f, paint2.apply { color = randomColor() })
        canvas.drawCircle(width/2f, height/2f, 100f, paint1.apply { color = randomColor() })
        canvas.drawRect(width/2f-30, height/2f-30, width/2f+30, height/2f+30, paint5.apply { color = randomColor() })
//        x += 10
//        y = (100 * Math.sin(3.0 * x.toDouble() * Math.PI / 180) + 400).toInt()
//        //加入新的坐标点
//        path.lineTo(x.toFloat(), y.toFloat())
//        paint.color = randomColor()
//        canvas.drawPath(path, paint)
    }

}