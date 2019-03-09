package com.android.draw.canvas

import android.graphics.Paint
import android.os.Bundle
import android.widget.SeekBar
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.listener.OnSeekBarChange
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.draw_arc.*
import kotlinx.android.synthetic.main.draw_circle.*
import kotlinx.android.synthetic.main.draw_line.*
import kotlinx.android.synthetic.main.draw_oval.*
import kotlinx.android.synthetic.main.draw_point.*
import kotlinx.android.synthetic.main.draw_rect.*
import kotlinx.android.synthetic.main.header_view1.view.*

@LayoutId(R.layout.activity_canvas)
class GeometryActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        drawPoint()
        drawCircle()
        drawRect()
        drawOval()
        drawLine()
        drawArc()
    }

    private fun drawPoint() {
        //初始化设置
        point.viewTreeObserver.addOnGlobalLayoutListener {
            sbPointCx.max = point.width
            sbPointCy.max = point.height
            sbPointCx.progress = point.width/2
            sbPointCy.progress = point.height/2
        }

        //cx变化更新
        sbPointCx.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                point.cx = progress.toFloat()
                point.invalidate()
            }
        })

        //cy变化更新
        sbPointCy.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                point.cy = progress.toFloat()
                point.invalidate()
            }
        })
    }

    private fun drawArc() {
        arc.viewTreeObserver.addOnGlobalLayoutListener {
            sbArcLeft.max = srnWidth
            sbArcTop.max = 200.dp2px()
            sbArcRight.max = srnWidth
            sbArcBottom.max = 200.dp2px()
            sbArcRight.progress = srnWidth/2
            sbArcBottom.progress = 100.dp2px()
            sbArcStartAngle.max = 360
            sbArcSweepAngle.max = 360
            sbArcStartAngle.progress = 0
            sbArcSweepAngle.progress = 90
        }

        sbArcLeft.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                arc.left = progress.toFloat()
                arc.invalidate()
            }
        })

        sbArcTop.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                arc.top = progress.toFloat()
                arc.invalidate()
            }
        })

        sbArcRight.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                arc.right = progress.toFloat()
                arc.invalidate()
            }
        })

        sbArcBottom.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                arc.bottom = progress.toFloat()
                arc.invalidate()
            }
        })

        sbArcStartAngle.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                arc.startAngle = progress.toFloat()
                arc.invalidate()
            }
        })

        sbArcSweepAngle.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                arc.sweepAngle = progress.toFloat()
                arc.invalidate()
            }
        })

        rgArcUseCenter.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rbUseCenter1->arc.useCenter = true
                R.id.rbUseCenter2->arc.useCenter = false
            }
            arc.invalidate()
        }

        rgPaintStyle.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rbArcFill->arc.paint.style = Paint.Style.FILL
                R.id.rbArcStroke->arc.paint.style = Paint.Style.STROKE
            }
            arc.invalidate()
        }
    }

    private fun drawLine() {
        line.viewTreeObserver.addOnGlobalLayoutListener {
            sbLineLeft.max = srnWidth
            sbLineTop.max = 200.dp2px()
            sbLineRight.max = srnWidth
            sbLineBottom.max = 200.dp2px()
            sbLineRight.progress = srnWidth/2
            sbLineBottom.progress = 100.dp2px()
        }

        sbLineLeft.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                line.startX = progress.toFloat()
                line.invalidate()
            }
        })

        sbLineTop.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                line.startY = progress.toFloat()
                line.invalidate()
            }
        })

        sbLineRight.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                line.stopX = progress.toFloat()
                line.invalidate()
            }
        })

        sbLineBottom.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                line.stopY = progress.toFloat()
                line.invalidate()
            }
        })
    }

    private fun drawOval() {
        oval.viewTreeObserver.addOnGlobalLayoutListener {
            sbOvalLeft.max = srnWidth
            sbOvalTop.max = 200.dp2px()
            sbOvalRight.max = srnWidth
            sbOvalBottom.max = 200.dp2px()
            sbOvalRight.progress = srnWidth/2
            sbOvalBottom.progress = 100.dp2px()
        }

        sbOvalLeft.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                oval.left = progress.toFloat()
                oval.invalidate()
            }
        })

        sbOvalTop.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                oval.top = progress.toFloat()
                oval.invalidate()
            }
        })

        sbOvalRight.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                oval.right = progress.toFloat()
                oval.invalidate()
            }
        })

        sbOvalBottom.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                oval.bottom = progress.toFloat()
                oval.invalidate()
            }
        })

        headerOval.tvSubTitle.click {
            codeDialog.text("""
                  oval.viewTreeObserver.addOnGlobalLayoutListener {
                    sbOvalLeft.max = srnWidth
                    sbOvalTop.max = 200.dp2px()
                    sbOvalRight.max = srnWidth
                    sbOvalBottom.max = 200.dp2px()
                    sbOvalRight.progress = srnWidth/2
                    sbOvalBottom.progress = 100.dp2px()
                }

                sbOvalLeft.setOnSeekBarChangeListener(object :OnSeekBarChange{
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        oval.left = progress.toFloat()
                        oval.invalidate()
                    }
                })

                sbOvalTop.setOnSeekBarChangeListener(object :OnSeekBarChange{
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        oval.top = progress.toFloat()
                        oval.invalidate()
                    }
                })

                sbOvalRight.setOnSeekBarChangeListener(object :OnSeekBarChange{
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        oval.right = progress.toFloat()
                        oval.invalidate()
                    }
                })

                sbOvalBottom.setOnSeekBarChangeListener(object :OnSeekBarChange{
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        oval.bottom = progress.toFloat()
                        oval.invalidate()
                    }
                })
            """.trimIndent())
        }
    }

    private fun drawRect() {
        rect.viewTreeObserver.addOnGlobalLayoutListener {
            sbRectLeft.max = srnWidth
            sbRectTop.max = 200.dp2px()
            sbRectRight.max = srnWidth
            sbRectBottom.max = 200.dp2px()
            sbRectRight.progress = srnWidth/2
            sbRectBottom.progress = 100.dp2px()
            sbRectRx.progress = 10
            sbRectRy.progress = 10
        }

        sbRectLeft.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rect.left = progress.toFloat()
                rect.invalidate()
            }
        })

        sbRectTop.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rect.top = progress.toFloat()
                rect.invalidate()
            }
        })

        sbRectRight.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rect.right = progress.toFloat()
                rect.invalidate()
            }
        })

        sbRectBottom.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rect.bottom = progress.toFloat()
                rect.invalidate()
            }
        })

        rgRectShape.check {
            when(it){
                R.id.rbRectSquare->{
                    rect.isRround = false
                    llRectRx.gone()
                    llRectRy.gone()
                }
                R.id.rbRectRound->{
                    rect.isRround = true
                    llRectRx.show()
                    llRectRy.show()
                }
            }
            rect.invalidate()
        }

        sbRectRx.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rect.rx = progress.toFloat()
                rect.invalidate()
            }
        })

        sbRectRy.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rect.ry = progress.toFloat()
                rect.invalidate()
            }
        })

        headerRect.tvSubTitle.click {
            codeDialog.text("""
                 rect.viewTreeObserver.addOnGlobalLayoutListener {
                    sbRectLeft.max = srnWidth
                    sbRectTop.max = 200.dp2px()
                    sbRectRight.max = srnWidth
                    sbRectBottom.max = 200.dp2px()
                    sbRectRight.progress = srnWidth/2
                    sbRectBottom.progress = 100.dp2px()
                }

                sbRectLeft.setOnSeekBarChangeListener(object :OnSeekBarChange{
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        rect.left = progress.toFloat()
                        rect.invalidate()
                    }
                })

                sbRectTop.setOnSeekBarChangeListener(object :OnSeekBarChange{
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        rect.top = progress.toFloat()
                        rect.invalidate()
                    }
                })

                sbRectRight.setOnSeekBarChangeListener(object :OnSeekBarChange{
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        rect.right = progress.toFloat()
                        rect.invalidate()
                    }
                })

                sbRectBottom.setOnSeekBarChangeListener(object :OnSeekBarChange{
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        rect.bottom = progress.toFloat()
                        rect.invalidate()
                    }
                })
            """.trimIndent())
        }
    }

    private fun drawCircle() {
        //初始化设置
        sbCircleRadius.progress = 30
        circle.viewTreeObserver.addOnGlobalLayoutListener {
            sbCircleCx.max = circle.width
            sbCircleCy.max = circle.height
            sbCircleCx.progress = circle.width/2
            sbCircleCy.progress = circle.height/2
        }

        //cx变化更新
        sbCircleCx.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                circle.cx = progress.toFloat()
                circle.invalidate()
            }
        })

        //cy变化更新
        sbCircleCy.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                circle.cy = progress.toFloat()
                circle.invalidate()
            }
        })

        //radius变化更新
        sbCircleRadius.setOnSeekBarChangeListener(object : OnSeekBarChange {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                circle.radius = progress.toFloat()
                circle.invalidate()
            }
        })

        header.subTitle.click {
            codeDialog.text("""
                        //初始化设置
                        sbCircleRadius.progress = 30
                        circle.viewTreeObserver.addOnGlobalLayoutListener {
                            sbCircleCx.max = circle.width
                            sbCircleCy.max = circle.height
                            sbCircleCx.progress = circle.width/2
                            sbCircleCy.progress = circle.height/2
                        }

                        //cx变化更新
                        sbCircleCx.setOnSeekBarChangeListener(object :OnSeekBarChange{
                            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                                circle.cx = progress.toFloat()
                                circle.invalidate()
                            }
                        })

                        //cy变化更新
                        sbCircleCy.setOnSeekBarChangeListener(object :OnSeekBarChange{
                            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                                circle.cy = progress.toFloat()
                                circle.invalidate()
                            }
                        })

                        //radius变化更新
                        sbCircleRadius.setOnSeekBarChangeListener(object : OnSeekBarChange {
                            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                                circle.radius = progress.toFloat()
                                circle.invalidate()
                            }
                        })
            """.trimIndent())
        }
    }
}
