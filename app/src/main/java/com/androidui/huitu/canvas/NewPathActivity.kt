package com.androidui.huitu.canvas

import android.graphics.Paint
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

    }

}
