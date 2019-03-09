package com.android.gesture

import android.graphics.Matrix
import android.os.Bundle
import android.widget.SeekBar
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.listener.OnSeekBarChange
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_matrix.*

@LayoutId(R.layout.activity_matrix)
class MatrixActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        Matrix()
        tvMatrix.text = """
SCALE：缩放
SKEW：错切
TRANS：平移
PERSP：透视

单位矩阵：它是个方阵，从左上角到右下角的对角线（称为主对角线）上的元素均为1。除此以外全都为0。
1 0 0
0 1 0
0 0 1
        """.trimIndent()

        case1()
        case2()
        case3()
        case4()
        case5()
        case6()
    }

    private fun case6() {
        headerPolyToPoly.setLeftClick {
            webDialog.url("poly_to_poly")
        }
        tvPTP1.text = """
1个点，平移
val src = floatArrayOf(0f, 0f)
val dst = floatArrayOf(translateX, translateY)
mMatrix.setPolyToPoly(src, 0, dst, 0, 1)
canvas?.drawBitmap(bitmap, mMatrix, paint)
        """.trimIndent()
        sbPTPtransX.change { seekBar, progress, fromUser ->
            ptp1View.translateX = progress*3f
            ptp1View.invalidate()
        }
        sbPTPtransY.change { seekBar, progress, fromUser ->
            ptp1View.translateY = progress*3f
            ptp1View.invalidate()
        }

        tvPTP2.text = """
两个点，可以达到旋转效果或者缩放效果，缩放比较简单，这里我们来看旋转效果，一个点指定中心，一点指出旋转的效果
val bw = bitmap.width.toFloat()
val bh = bitmap.height.toFloat()
val src = floatArrayOf(bw, bh, 0f, 0f)
val dst = floatArrayOf(bw, bh, bw + changeX, bh + changeY)
mMatrix.setPolyToPoly(src, 0, dst, 0, 2)
canvas?.drawBitmap(bitmap, mMatrix, paint)
        """.trimIndent()

        tvPTP3.text = """
canvas?.translate(width/2f-bitmap.width/2f,height/2f-bitmap.height/2f)
val bw = bitmap.width
val bh = bitmap.height
val src = floatArrayOf(0f, 0f, 0f, bh.toFloat(), bw.toFloat(), bh.toFloat())
val dst = floatArrayOf(0f, 0f, 300f, bh.toFloat(), (bw + 300).toFloat(), bh.toFloat())
mMatrix.setPolyToPoly(src, 0, dst, 0, 3)
canvas?.drawBitmap(bitmap, mMatrix, paint)
        """.trimIndent()

        tvPTP4.text = """
canvas?.translate(width/2f-bitmap.width/2f,height/2f-bitmap.height/2f)
val bw = bitmap.width
val bh = bitmap.height
val src = floatArrayOf(0f, 0f, 0f, bh.toFloat(), bw.toFloat(), bh.toFloat(), bw.toFloat(), 0f)
val dst = if(isPositive1){
    floatArrayOf(change1, 0f, 0f, bh.toFloat(), bw.toFloat(), bh.toFloat(), (bw - change1), 0f)
}else{
    floatArrayOf(-change1, 0f, 0f, bh.toFloat(), bw.toFloat(), bh.toFloat(), (bw + change1), 0f)
}
mMatrix.setPolyToPoly(src, 0, dst, 0, 4)
canvas?.drawBitmap(bitmap, mMatrix, paint)
        """.trimIndent()

        sbPTP4XY.change { seekBar, progress, fromUser ->
            ptp4View.change1 = progress*0.6f
            ptp4View.invalidate()
        }

        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            ptp4View.isPositive1 = !isChecked
        }

    }

    private fun case5() {
        sbSin.change { seekBar, progress, fromUser ->
            matrixSinCosView.sinValue = progress.toFloat()/20
            matrixSinCosView.invalidate()
        }
        sbCos.change { seekBar, progress, fromUser ->
            matrixSinCosView.cosValue = progress.toFloat()/20
            matrixSinCosView.invalidate()
        }
        sbPx.max = srnWidth-20.dp2px()
        sbPx.change { seekBar, progress, fromUser ->
            matrixSinCosView.px = progress.toFloat()/20
            matrixSinCosView.invalidate()
        }
        sbPy.max = 180.dp2px()
        sbPy.change { seekBar, progress, fromUser ->
            matrixSinCosView.py = progress.toFloat()/20
            matrixSinCosView.invalidate()
        }
    }

    private fun case4() {
        sbSkewKx.max = 100
        sbSkewKy.max = 100
        sbSkewKx.change { seekBar, progress, fromUser ->
            matrixSkewView.kx = progress.toFloat()/20
            matrixSkewView.invalidate()
        }
        sbSkewKy.change { seekBar, progress, fromUser ->
            matrixSkewView.ky = progress.toFloat()/20
            matrixSkewView.invalidate()
        }
    }

    private fun case3() {
        sbDegree.max = 720
        sbDegree.change { seekBar, progress, fromUser ->
            matrixRotateView.degree = progress.toFloat()
            matrixRotateView.invalidate()
        }
    }

    private fun case2() {
        headerScaleMatrix.setLeftClick {
            codeDialog.text("""
public void setScale(float sx, float sy, float px, float py)
public void setScale(float sx, float sy)

两个方法都是设置缩放到matrix中，sx和sy代表了缩放的倍数，px和py代表缩放的中心。
            """.trimIndent())
        }
        sbScaleDx.progress = 20
        sbScaleDx.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                matrixScaleView.sx = progress.toFloat()/20
                matrixScaleView.invalidate()
            }
        })
        sbScaleDy.progress = 20
        sbScaleDy.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                matrixScaleView.sy = progress.toFloat()/20
                matrixScaleView.invalidate()
            }
        })
    }

    private fun case1() {
        sbTransDx.max = srnWidth-20.dp2px()
        sbTransDx.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                matrixTransView.dx = progress.toFloat()
                matrixTransView.invalidate()
            }
        })
        sbTransDy.max = 180.dp2px()
        sbTransDy.setOnSeekBarChangeListener(object :OnSeekBarChange{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                matrixTransView.dy = progress.toFloat()
                matrixTransView.invalidate()
            }
        })

        headerTransMatrix.setRightClick {
            codeDialog.text("""
class MatrixTransView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    val paint = Paint()
    val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.girl)!!
    var mMatrix = Matrix()
    var dx: Float = 0f
    var dy: Float = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mMatrix.setTranslate(dx, dy)
        canvas?.drawBitmap(bitmap, mMatrix, paint)
    }

}

sbTransDx.max = srnWidth-20.dp2px()
sbTransDx.setOnSeekBarChangeListener(object :OnSeekBarChange{
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        matrixTransView.dx = progress.toFloat()
        matrixTransView.invalidate()
    }
})

sbTransDy.max = 180.dp2px()
sbTransDy.setOnSeekBarChangeListener(object :OnSeekBarChange{
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        matrixTransView.dy = progress.toFloat()
        matrixTransView.invalidate()
    }
})
            """.trimIndent())
        }
    }
}
