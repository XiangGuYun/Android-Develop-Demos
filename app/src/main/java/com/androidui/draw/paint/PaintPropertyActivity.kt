package com.androidui.draw.paint

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import com.androidui.dialog.CodeViewerDialog
import kotlinx.android.synthetic.main.activity_paint.*
import kotlinx.android.synthetic.main.header_view1.view.*

class PaintPropertyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        val d1 = CodeViewerDialog(this)
        btnStyle.tvSubTitle.setOnClickListener {
            d1.text("""
                /*
                Paint.Style.FILL：填充内部
                Paint.Style.STROKE  ：描边
                Paint.Style.FILL_AND_STROKE  ：填充内部和描边
                */
                override fun onDraw(canvas: Canvas?) {
                    super.onDraw(canvas)
                    setBackgroundColor(Color.WHITE)

                    paint.color = Color.RED
                    paint.style = Paint.Style.FILL
                    canvas?.drawRect(0f, 0f, width.toFloat(), 150f, paint)
                    paint.reset()

                    paint.color = Color.GREEN
                    paint.style = Paint.Style.STROKE
                    paint.strokeWidth = 15f
                    canvas?.drawRect(0f, 150f, width.toFloat(), 300f, paint)
                    paint.reset()

                    paint.color = Color.BLUE
                    paint.style = Paint.Style.FILL_AND_STROKE
                    canvas?.drawRect(0f, 300f, width.toFloat(), 450f, paint)
                }
            """.trimIndent())
        }

        btnCap.tvSubTitle.setOnClickListener {
            d1.text("""
                /*
                主要是用来设置线条的末端，为了直观，下面三个线条我设置的比较粗
                */
                override fun onDraw(canvas: Canvas?) {
                    super.onDraw(canvas)

                    paint.color = Color.RED
                    paint.strokeCap = Paint.Cap.BUTT
                    canvas?.drawLine(200f, 100f, 800f, 100f, paint)

                    paint.color = Color.GREEN
                    paint.strokeCap = Paint.Cap.ROUND
                    canvas?.drawLine(200f, 200f, 800f, 200f, paint)

                    paint.color = Color.BLUE
                    paint.strokeCap = Paint.Cap.SQUARE
                    canvas?.drawLine(200f, 300f, 800f, 300f, paint)
                }
            """.trimIndent())
        }

        btnJoin.tvSubTitle.setOnClickListener {
            d1.text("""
                override fun onDraw(canvas: Canvas?) {
                    super.onDraw(canvas)

                    paint.color = Color.RED
                    paint.strokeJoin = Paint.Join.BEVEL
                    canvas?.drawRect(200f, 30f, 800f, 160f, paint)

                    paint.color = Color.GREEN
                    paint.strokeJoin = Paint.Join.MITER
                    canvas?.drawRect(200f, 210f, 800f, 310f, paint)

                    paint.color = Color.BLUE
                    paint.strokeJoin = Paint.Join.ROUND
                    canvas?.drawRect(200f, 360f, 800f, 510f, paint)
                }
            """.trimIndent())
        }

    }
}
