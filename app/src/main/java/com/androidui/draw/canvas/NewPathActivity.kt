package com.androidui.draw.canvas

import android.os.Bundle
import com.androidui.ImageViewerActivity
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_new_path.*

@LayoutId(R.layout.activity_new_path)
class NewPathActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        btn1.click { go(PathActivity::class.java) }

        ivCatalog.click {
            go(ImageViewerActivity::class.java, ImageViewerActivity.IMG_ID to R.mipmap.path_catalog)
        }

        headerSetPath.setLeftClick {
            webDialog.url("path/set_path")
        }

        tv1.setClickText("关卡1：绘制一个六角星(代码)",12,14){
            codeDialog.text("""
override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    //初始化Path
    canvas?.translate(width/2f, height/2f)

    path.moveTo(0f,-120f)
    path.lineTo(100f,60f)
    path.lineTo(-100f,60f)
    path.close()

    canvas?.drawPath(path, paint)

    path.moveTo(-100f,-60f)
    path.lineTo(100f,-60f)
    path.lineTo(0f,120f)
    path.close()

    canvas?.drawPath(path, paint)

}
                    """.trimIndent())
        }

        val text = "关卡2：有无setLastPoint的区别(代码)"
        tv2.setClickText(text, text.length-3,text.length){
            codeDialog.text("""
canvas?.drawLine(width/2f,0f,width/2f,height.toFloat(),paint1)
canvas?.drawLine(0f,height/2f,width.toFloat(),height/2f,paint1)
canvas?.translate(width/2f, height/2f)

path.moveTo(0f,-120f)
path.lineTo(100f,60f)
path.lineTo(-100f,60f)

//关键
path.setLastPoint(0f,0f)

path.close()

canvas?.drawPath(path, paint)
                    """.trimIndent())
        }

        /*
        绘制弧形
         */
        tvArcTo.setSimpleClickText {
            codeDialog.text("""
canvas?.drawLine(width/2f,0f,width/2f,height.toFloat(),paint1)
canvas?.drawLine(0f,height/2f,width.toFloat(),height/2f,paint1)

path.moveTo(0f,0f)

//初始化Path
canvas?.translate(width/2f, height/2f)

path.arcTo(RectF(-width/2f, -height/2f, width/2f, height/2f), 0f, 145f)
canvas?.drawPath(path, paint)
            """.trimIndent())
        }

        /*
        合并路径
         */
        headerAddPath1.setLeftClick {
            webDialog.url("canvas/path_contact")
        }

        /*
        路径属性
         */
        headerPathProperty.setLeftClick {
            webDialog.url("canvas/path_prop")
        }

        readerPath.setText("""
// 判断path中是否包含内容
public boolean isEmpty ()
// 例子：
Path path = new Path();
path.isEmpty();  //返回true
path.lineTo(100,100); // 返回false


// 判断path是否是一个矩形
// 如果是一个矩形的话，会将矩形的信息存放进参数rect中。
public boolean isRect (RectF rect)

// 实例
path.lineTo(0,400);
path.lineTo(400,400);
path.lineTo(400,0);
path.lineTo(0,0);

RectF rect = new RectF();
boolean b = path.isRect(rect);  // b返回ture,
// rect存放矩形参数，具体如下：
// rect.left = 0
// rect.top = 0
// rect.right = 400
// rect.bottom = 400

// 将新的路径替代现有路径
public void set (Path src)

// 实例
// 设置一矩形路径
Path path = new Path();
path.addRect(-200,-200,200,200, Path.Direction.CW);

// 设置一圆形路径
Path src = new Path();
src.addCircle(0,0,100, Path.Direction.CW);

// 将圆形路径代替矩形路径
path.set(src);

// 绘制图形
canvas.drawPath(path,mPaint);


// 平移路径
// 与Canvas.translate() 平移画布类似


// 方法1
// 参数x,y：平移位置
public void offset (float dx, float dy)

// 方法2
// 参数dst：存储平移后的路径状态，但不影响当前path
// 可通过dst参数绘制存储的路径
public void offset (float dx, float dy, Path dst)



 // 为了方便观察,平移坐标系
canvas.translate(350, 500);

// path中添加一个圆形(圆心在坐标原点)
path = new Path();
path.addCircle(0, 0, 100, Path.Direction.CW);

// 平移路径并存储平移后的状态
Path dst = new Path();
// 平移
path.offset(400, 0, dst);

// 绘制path
canvas.drawPath(path, mPaint1);

// 通过dst绘制平移后的图形(红色)
mPaint1.setColor(Color.RED);
canvas.drawPath(dst,mPaint1);;

        """.trimIndent())

        headerPathFillColor.setLeftClick{
            webDialog.url("canvas/PathFillColor")
        }

        headerPathBoolOp.setLeftClick {
            webDialog.url("canvas/path_bool_op")
        }

    }

}
