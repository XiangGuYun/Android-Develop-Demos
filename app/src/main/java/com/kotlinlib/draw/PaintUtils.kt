package com.kotlinlib.draw

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

/**
 * 绘图工具类
 */
interface PaintUtils {
    //白色
    val WHITE:Int get() = Color.parseColor("#ffffff")
    val BLUE:Int get() = Color.BLUE

    fun Paint.color(color:Int):Paint{
        setColor(color)
        return this
    }

    fun Paint.color(color:String):Paint{
        setColor(Color.parseColor(color))
        return this
    }

    fun Paint.antiAlias(anti:Boolean):Paint{
        isAntiAlias = anti
        return this
    }

    fun Paint.dither(dither:Boolean):Paint{
        isDither = dither
        return this
    }

    fun Paint.isStroke(stroke:Boolean):Paint{
        style = if(stroke)
            Paint.Style.STROKE
        else
            Paint.Style.FILL
        return this
    }

    fun Paint.strokeWidth(width:Number):Paint{
        strokeWidth = width.toFloat()
        return this
    }
    fun Paint.strokeJoin(join:Paint.Join):Paint{
        strokeJoin = join
        return this
    }


    fun Paint.textSize(size:Float):Paint{
        textSize =  size
        return this
    }

    fun Paint.reset(size:Float):Paint{
        reset()
        return this
    }

    fun Path.move_to(x:Number,y:Number):Path{
        moveTo(x.toFloat(),y.toFloat())
        return this
    }

    fun Path.line_to(x:Number,y:Number):Path{
        lineTo(x.toFloat(),y.toFloat())
        return this
    }

    fun Paint.isFakeBoldText(bool:Boolean):Paint{
        isFakeBoldText = bool
        return this
    }

    fun Path.closePath(): Path {
        close()
        return this
    }

    fun Canvas.draw_path(path:Path,paint:Paint): Canvas {
        drawPath(path,paint)
        return this
    }

    fun <T:Number> Canvas.draw_text(text:String,x:T,y:T,paint:Paint): Canvas {
        drawText(text,x.toFloat(), y.toFloat(), paint)
        return this
    }

    fun <T:Number> Canvas.draw_line(x:T,y:T,x1:T,y1:T,paint:Paint): Canvas {
        drawLine(x.toFloat(), y.toFloat(), x1.toFloat(), y1.toFloat(), paint)
        return this
    }

}