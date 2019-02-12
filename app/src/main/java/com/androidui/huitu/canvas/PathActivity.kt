package com.androidui.huitu.canvas

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
