package com.androidui.huitu.paint

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.androidui.ImageViewerActivity
import com.androidui.R
import com.androidui.dialog.CodeViewerDialog
import com.androidui.dialog.ListBtmDialog
import com.kotlinlib.other.BaseInterface
import kotlinx.android.synthetic.main.activity_draw_text.*

class DrawTextActivity : AppCompatActivity(),BaseInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_text)

        val codeDialog = CodeViewerDialog(this)

        ivFontMetric.setOnClickListener {
            startActivity(Intent(this, ImageViewerActivity::class.java)
                    .putExtra(ImageViewerActivity.IMG_ID, R.mipmap.fontmetric))
        }

        val dialogTextColor = ListBtmDialog(this, "请选择颜色", arrayListOf("BLACK","BLUE","GREEN"
                ,"RED","CYAN","DKGRAY","LTGRAY","MAGENTA","YELLOW")){
            dialog, view, pos ->
            view.setOnClickListener {
                when(pos){
                    0-> viewDrawText.paint.color = Color.BLACK
                    1-> viewDrawText.paint.color = Color.BLUE
                    2-> viewDrawText.paint.color = Color.GREEN
                    3-> viewDrawText.paint.color = Color.RED
                    4-> viewDrawText.paint.color = Color.CYAN
                    5-> viewDrawText.paint.color = Color.DKGRAY
                    6-> viewDrawText.paint.color = Color.LTGRAY
                    7-> viewDrawText.paint.color = Color.MAGENTA
                    8-> viewDrawText.paint.color = Color.YELLOW
                }
                dialog.dismiss()
                viewDrawText.invalidate()
            }
        }
        btnTextColor.setOnClickListener {
            dialogTextColor.show()
        }

        btnTextColorCode.setOnClickListener {
            codeDialog.text("""
                 val dialogTextColor = ListBtmDialog(this, "请选择颜色", arrayListOf("BLACK","BLUE","GREEN"
                ,"RED","RED","CYAN","DKGRAY","LTGRAY","MAGENTA","YELLOW")){
                    dialog, view, pos ->
                    view.setOnClickListener {
                        when(pos){
                            0-> viewDrawText.paint.color = Color.BLACK
                            1-> viewDrawText.paint.color = Color.BLUE
                            2-> viewDrawText.paint.color = Color.GREEN
                            3-> viewDrawText.paint.color = Color.RED
                            4-> viewDrawText.paint.color = Color.CYAN
                            5-> viewDrawText.paint.color = Color.DKGRAY
                            6-> viewDrawText.paint.color = Color.LTGRAY
                            7-> viewDrawText.paint.color = Color.MAGENTA
                            8-> viewDrawText.paint.color = Color.YELLOW
                        }
                        dialog.dismiss()
                        viewDrawText.invalidate()
                    }
                }
            """.trimIndent())
        }

        btnDrawTextCode.setOnClickListener {
            codeDialog.text("""
                override fun onDraw(canvas: Canvas?) {
                    super.onDraw(canvas)
                    val text = "这是用Paint绘制的文字"
                    val rect = Rect()//用来存储文本的宽高值
                    paint.getTextBounds(text, 0, text.length, rect)
                    canvas?.drawText(
                        text, //要绘制的文本内容
                        0, //文本内容的开始字符下标值
                        text.length, //文本内容的结束字符下标值+1
                        (width-rect.width())/2f, //文字的开始处x轴坐标
                        (height+rect.height()/2)/2f,//文字的开始处y轴坐标
                        paint//画笔
                    )
                }
            """.trimIndent())
        }

        seekBarTextSize.max = 100
        seekBarTextSize.progress = 18
        seekBarTextSize.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewDrawText.paint.textSize = progress.toFloat()
                viewDrawText.invalidate()
            }

        })
        btnCodeTextSize.setOnClickListener {
            codeDialog.text("""
                seekBarTextSize.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        viewDrawText.paint.textSize = progress.toFloat()
                        viewDrawText.invalidate()
                    }
                })
            """.trimIndent())
        }

        val dialogTypeFace = ListBtmDialog(this, "请选择字体", arrayListOf("DEFAULT","DEFAULT_BOLD","MONOSPACE"
                ,"SANS_SERIF","SERIF","ITALIC","BOLD_ITALIC","自定义字体")){
            dialog, view, pos ->
            view.setOnClickListener {
                when(pos){
                    0-> viewDrawText.paint.typeface = Typeface.DEFAULT
                    1-> viewDrawText.paint.typeface = Typeface.DEFAULT_BOLD
                    2-> viewDrawText.paint.typeface = Typeface.MONOSPACE
                    3-> viewDrawText.paint.typeface = Typeface.SANS_SERIF
                    4-> viewDrawText.paint.typeface = Typeface.SERIF
                    5-> viewDrawText.paint.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
                    6-> viewDrawText.paint.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
                    7-> viewDrawText.paint.typeface = Typeface.createFromAsset(assets, "font/miaohuntype.ttf")
                }
                dialog.dismiss()
                viewDrawText.invalidate()
            }
        }
        btnTypeface.setOnClickListener {
            dialogTypeFace.show()
        }
        btnCodeTypeface.setOnClickListener {
            codeDialog.text("""
                 val dialogTypeFace = ListBtmDialog(this, "请选择字体", arrayListOf("DEFAULT","DEFAULT_BOLD","MONOSPACE"
                ,"SANS_SERIF","SERIF","ITALIC","BOLD_ITALIC","自定义字体")){
                    dialog, view, pos ->
                    view.setOnClickListener {
                        when(pos){
                            0-> viewDrawText.paint.typeface = Typeface.DEFAULT
                            1-> viewDrawText.paint.typeface = Typeface.DEFAULT_BOLD
                            2-> viewDrawText.paint.typeface = Typeface.MONOSPACE
                            3-> viewDrawText.paint.typeface = Typeface.SANS_SERIF
                            4-> viewDrawText.paint.typeface = Typeface.SERIF
                            5-> viewDrawText.paint.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
                            6-> viewDrawText.paint.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
                            7-> viewDrawText.paint.typeface = Typeface.createFromAsset(assets, "font/miaohuntype.ttf")
                        }
                        dialog.dismiss()
                        viewDrawText.invalidate()
                    }
                }
            """.trimIndent())
        }

        seekBarSkewX.max = 100
        seekBarSkewX.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewDrawText.paint.textSkewX = -progress/100f
                viewDrawText.invalidate()
            }

        })
        btnCodeSkewX.setOnClickListener {
            codeDialog.text("""
                seekBarSkewX.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{

                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        viewDrawText.paint.textSkewX = -progress/100f
                        viewDrawText.invalidate()
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })
            """.trimIndent())
        }

        rgTextUnderLine.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rbTextUnderLine1->{
                    viewDrawText.paint.isUnderlineText = true
                }
                R.id.rbTextUnderLine2->{
                    viewDrawText.paint.isUnderlineText = false
                }
            }
            viewDrawText.invalidate()
        }

        seekBarLetterSpacing.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    viewDrawText.paint.letterSpacing = progress/100f
                    viewDrawText.invalidate()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        btnLetterSpacingCode.setOnClickListener {
            codeDialog.text("""
                 override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        viewDrawText.paint.letterSpacing = progress/100f
                        viewDrawText.invalidate()
                    }
                }
            """.trimIndent())
        }

        btnTextUnderLineCode.setOnClickListener {
            codeDialog.text("""
                rgTextUnderLine.setOnCheckedChangeListener { group, checkedId ->
                    when(checkedId){
                        R.id.rbTextUnderLine1->viewDrawText.paint.isUnderlineText = true
                        R.id.rbTextUnderLine2->viewDrawText.paint.isUnderlineText = false
                    }
                    viewDrawText.invalidate()
                }
            """.trimIndent())
        }

        rgStrikeThruText.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rbStrikeThruText1->{
                    viewDrawText.paint.isStrikeThruText = true
                }
                R.id.rbStrikeThruText2->{
                    viewDrawText.paint.isStrikeThruText = false
                }
            }
            viewDrawText.invalidate()
        }

        btnStrikeThruTextCode.setOnClickListener {
            codeDialog.text("""
                rgStrikeThruText.setOnCheckedChangeListener { group, checkedId ->
                    when(checkedId){
                        R.id.rbStrikeThruText1->{
                            viewDrawText.paint.isStrikeThruText = true
                        }
                        R.id.rbStrikeThruText2->{
                            viewDrawText.paint.isStrikeThruText = false
                        }
                    }
                    viewDrawText.invalidate()
                }
            """.trimIndent())
        }

        val dialogAlignType = ListBtmDialog(this, "请选择对齐方式", arrayListOf("居左对齐","居中对齐","居右对齐")){
            dialog, view, pos ->
            view.setOnClickListener {
                when(pos){
                    0-> viewDrawText.paint.textAlign = Paint.Align.LEFT
                    1-> viewDrawText.paint.textAlign = Paint.Align.CENTER
                    2-> viewDrawText.paint.textAlign = Paint.Align.RIGHT
                }
                dialog.dismiss()
                viewDrawText.invalidate()
            }
        }
        btnTextAlign.setOnClickListener {
            dialogAlignType.show()
        }
        btnTextAlignCode.setOnClickListener {
            codeDialog.text("""
                /*
                对齐方式指的是drawText(text, start, end, x, y, paint)中的start和end所描述的点在文字的左边、右边还是中间，默认是在左边。
                */
                val dialogAlignType = ListBtmDialog(this, "请选择对齐方式", arrayListOf("居左对齐","居中对齐","居右对齐")){
                    dialog, view, pos ->
                    view.setOnClickListener {
                        when(pos){
                            0-> viewDrawText.paint.textAlign = Paint.Align.LEFT
                            1-> viewDrawText.paint.textAlign = Paint.Align.CENTER
                            2-> viewDrawText.paint.textAlign = Paint.Align.RIGHT
                        }
                        dialog.dismiss()
                        viewDrawText.invalidate()
                    }
                }
            """.trimIndent())
        }

        btnFontMetricsInfo.setOnClickListener {
            codeDialog.text("""
                Canvas绘制文本时，使用FontMetrics对象，计算文本位置的坐标。
                public static class FontMetrics {
                    public float   top;

                    public float   ascent;

                    public float   descent;

                    public float   bottom;

                    public float   leading;
                }
                说明如下：
                1. 基准点是baseline
                2. Ascent是baseline之上至字符最高处的距离
                3. Descent是baseline之下至字符最低处的距离
                4. Leading文档说的很含糊，其实是上一行字符的descent到下一行的ascent之间的距离
                5. Top指的是指的是最高字符到baseline的值，即ascent的最大值
                6. 同上，bottom指的是最下字符到baseline的值，即descent的最大值
                descent-ascent就可以看作文本的高度。
            """.trimIndent())
        }

        btnGetTextHeight.setOnClickListener {
            viewDrawText.paint.apply {
                val height = descent()-ascent()
                "当前文字的高度是$height".toast(this@DrawTextActivity)
            }
        }
        btnGetTextHeightCode.setOnClickListener {
            codeDialog.text("""
                 viewDrawText.paint.apply {
                    val height = descent()-ascent()
                    "当前文字的高度是'$'height".toast(this@DrawTextActivity)
                }
            """.trimIndent())
        }

        btnGetTextMetric.setOnClickListener {
            val rect = Rect()
            val text = "这是用Paint绘制的文字"
            viewDrawText.paint.getTextBounds(text, 0, text.length, rect)
            "文本宽度是${rect.width()}，高度是${rect.height()}".toast(this)
        }

        btnGetTextMetricCode.setOnClickListener {
            codeDialog.text("""
                val rect = Rect()
                val text = "这是用Paint绘制的文字"
                viewDrawText.paint.getTextBounds(text, 0, text.length, rect)
                "文本宽度是$'{rect.width()}，高度是$'{rect.height()}".toast(this)
            """.trimIndent())
        }


    }
}
