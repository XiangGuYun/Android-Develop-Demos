package com.androidui.draw.canvas

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_path.*

@LayoutId(R.layout.activity_path)
class PathActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        drawPath()
        tvPic1.text = """
            val path = Path()
            path.moveTo(10f, 10f)
            path.lineTo(40f, 40f)
            path.lineTo(40f, 80f)
            canvas?.drawPath(path, paint)
        """.trimIndent()
        tvPic2.text = """
            val path = Path()
            path.moveTo(10f, 10f)
            path.lineTo(40f, 40f)
            //新增的代码
            path.rMoveTo(0f, 10f)
            path.lineTo(40f, 80f)
            canvas?.drawPath(path, paint)
        """.trimIndent()
        tvPic3.text = """
            val path = Path()
            path.moveTo(10f, 10f)
            path.lineTo(40f, 40f)
            //新加的setLastPoint
            path.setLastPoint(10f, 80f)
            path.lineTo(40f, 80f)
            canvas?.drawPath(path, paint)
        """.trimIndent()
        tvGeometry.text = """
            //绘制圆
            addCircle(float x, float y, float radius, Direction dir)
             //绘制椭圆
            addOval(RectF oval, Direction dir)
            addOval(float left, float top, float right, float bottom, Direction dir)
            //绘制矩形
            addRect(RectF rect, Direction dir)
            addRect(float left, float top, float right, float bottom, Direction dir)
            //绘制圆角矩形
            addRoundRect(RectF rect, float rx, float ry, Direction dir)
            addRoundRect(float left, float top, float right, float bottom, float rx, float ry,Direction dir)
            addRoundRect(RectF rect, float[] radii, Direction dir)
            addRoundRect(float left, float top, float right, float bottom, float[] radii,Direction dir)
        """.trimIndent()
        tvDirection.text ="""
            Path.Direction.CCW counter-clockwise ，沿逆时针方向绘制
            Path.Direction.CW  clockwise ，沿顺时针方向绘制
        """.trimIndent()
        tvCCW.text = """
            val path = Path()
            path.addCircle(width/2f, height/2f, width/3f, Path.Direction.CCW)
            //沿path绘制文字
            canvas?.drawTextOnPath("A、B、C、D、E、F、G、H、I、J、K、L、M、N、O、P、Q、R、S、T、U、V、W、X、Y、Z。", path, 0f, 0f, paint)
            canvas?.drawPath(path, paint)
        """.trimIndent()
        tvCW.text = """
            val path = Path()
            path.addCircle(width/2f, height/2f, width/3f, Path.Direction.CW)
            //沿path绘制文字
            canvas?.drawTextOnPath("A、B、C、D、E、F、G、H、I、J、K、L、M、N、O、P、Q、R、S、T、U、V、W、X、Y、Z。", path, 0f, 0f, paint)
            canvas?.drawPath(path, paint)
        """.trimIndent()
        btnPathGeoCode.click {
            codeDialog.text("""
        val path = Path()
        //以（400,200）为圆心，半径为100绘制圆
        path.addCircle(400f, 200f, 100f, Path.Direction.CW)

        //绘制椭圆
        val rectF = RectF(100f, 350f, 500f, 600f)
        //第一种方法绘制椭圆
        path.addOval(rectF, Path.Direction.CW)
        //第二种方法绘制椭圆
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addOval(600f, 350f, 1000f, 600f, Path.Direction.CW)
        }

        //绘制矩形
        val rect = RectF(100f, 650f, 500f, 900f)
        //第一种方法绘制矩形
        path.addRect(rect, Path.Direction.CW)
        //第二种方法绘制矩形
        path.addRect(600f, 650f, 1000f, 900f, Path.Direction.CCW)

        //绘制圆角矩形
        val roundRect = RectF(100f, 950f, 300f, 1100f)
        //第一种方法绘制圆角矩形
        path.addRoundRect(roundRect, 20f, 20f, Path.Direction.CW)
        //第二种方法绘制圆角矩形
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addRoundRect(350f, 950f, 550f, 1100f, 10f, 50f, Path.Direction.CCW)
        }
        //第三种方法绘制圆角矩形
        //float[] radii中有8个值，依次为左上角，右上角，右下角，左下角的rx,ry
        val roundRectT = RectF(600f, 950f, 800f, 1100f)
        path.addRoundRect(roundRectT, floatArrayOf(50f, 50f, 50f, 50f, 50f, 50f, 0f, 0f), Path.Direction.CCW)
        //第四种方法绘制圆角矩形
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addRoundRect(850f, 950f, 1050f, 1100f, floatArrayOf(0f, 0f, 0f, 0f, 50f, 50f, 50f, 50f), Path.Direction.CCW)
        }
        canvas?.drawPath(path, paint)
            """.trimIndent())
        }
    }

    private fun drawPath() {
        btnTeachLineTo.click {
            codeDialog.text("""
             Add a line from the last point to the specified point (x,y).
             If no moveTo() call has been made for this contour, the first point is
             automatically set to (0,0).

             @param x The x-coordinate of the end of a line
             @param y The y-coordinate of the end of a line

             添加一条由上一个点到指定的点(x,y)构成的直线。
             如果moveTo()没有在线段轮廓中被调用，那么第一个点会自动设置为(0,0)。
          """.trimIndent())
        }
        btnTeachRLineTo.click {
            codeDialog.text("""
                 Same as lineTo, but the coordinates are considered relative to the last
                 point on this contour. If there is no previous point, then a moveTo(0,0)
                 is inserted automatically.

                 @param dx The amount to add to the x-coordinate of the previous point on
                           this contour, to specify a line
                 @param dy The amount to add to the y-coordinate of the previous point on
                           this contour, to specify a line

                 和lineTo类似，但是坐标被看做和线段轮廓中上一个点相关。如果没有上一个点，那么moveTo(0,0)会被自动插入。

                 对比
                 lineTo传入的就是下个点的坐标(x, y);
                 rLineTo传入的是相对于上一个点坐标(preX, preY)在xy轴上的偏移量，实际坐标应该是(preX+dx, preY+dy)

            """.trimIndent())
        }
        btnClosePath.click {
            path.isClose = true
            path.invalidate()
        }
        btnLineTo.click {
            if(etLineToX.textString.isNotEmpty()&&etLineToY.textString.isNotEmpty()){
                path.nextX = etLineToX.textString.toFloat()
                path.nextY = etLineToY.textString.toFloat()
                path.invalidate()
            }else{
                "请输入正确的坐标值".toast()
            }
        }
        btnRLineTo.click {
            if(etRLineToX.textString.isNotEmpty()&&etRLineToY.textString.isNotEmpty()){
                path.dx = etRLineToX.textString.toFloat()
                path.dy = etRLineToY.textString.toFloat()
                path.invalidate()
            }else{
                "请输入正确的偏移值".toast()
            }
        }
    }

}
