package com.android.anim.other

import android.animation.TypeEvaluator
import android.util.Log

class PlaneEvaluator : TypeEvaluator<Point> {

    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {

        // 将动画初始值startValue 和 动画结束值endValue 强制类型转换成Point对象
        val startPoint = startValue as Point
        val endPoint = endValue as Point

        Log.e("asfsfas", "fraction is $fraction, point is ${(endPoint.x - startPoint.x)*fraction}, ${(endPoint.y - startPoint.y)*fraction}")

        // 将计算后的坐标封装到一个新的Point对象中并返回
        return Point(startPoint.x+(endPoint.x - startPoint.x)*fraction, startPoint.y+(endPoint.y - startPoint.y)*fraction)
    }

}