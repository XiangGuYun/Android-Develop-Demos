package com.kotlinlib.view.surfaceview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.kotlinlib.draw.PaintUtils

abstract class BaseSurfaceView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        SurfaceView(context, attrs, defStyleAttr), PaintUtils, SurfaceHolder.Callback{

    private var canvas: Canvas? = null//画布
    private var isDrawing: Boolean = false

    init {
        holder.addCallback(this)
        isFocusable = true
        isFocusableInTouchMode = true
        keepScreenOn = true
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        isDrawing = true
        Thread(Runnable {
            while (isDrawing){
                var startTime = System.currentTimeMillis()
                canvas = holder!!.lockCanvas()
                if(canvas!=null){
                    drawing(canvas!!)
                    /**绘制结束后解锁显示在屏幕上**/
                    holder.unlockCanvasAndPost(canvas)
                }
                /**取得更新结束的时间**/
                val endTime = System.currentTimeMillis()

                /**计算出一次更新的毫秒数**/
                var diffTime = (endTime - startTime) as Int

                /**确保每次更新时间为30帧**/
                while (diffTime <= getDrawingSpeed()) {
                    diffTime = (System.currentTimeMillis() - startTime) as Int
                    /**线程等待 */
                    Thread.yield()
                }
            }
        })
    }

    protected fun getDrawingSpeed(): Int {
        return 30
    }

    protected abstract fun drawing(canvas: Canvas)

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        isDrawing = false
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }
}