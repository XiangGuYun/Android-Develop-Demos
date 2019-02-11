package com.androidui.huitu

import android.os.Bundle
import android.widget.SeekBar
import com.androidui.R
import com.androidui.dialog.CodeViewerDialog
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.listener.OnSeekBarChange
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_canvas.*

@LayoutId(R.layout.activity_canvas)
class CanvasActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {

        val codeDialog = CodeViewerDialog(this)

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
