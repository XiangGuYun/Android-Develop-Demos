package com.android.systemui.textview

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_text_view_extend.*
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlResImageGetter


@LayoutId(R.layout.activity_text_view_extend)
class TextViewExtendActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header1.setLeftClick {
            webDialog.url("view/SlantedTextView")
        }

        header2.setLeftClick {
            webDialog.url("view/HtmlTextViewForAndroid")
        }

        tvHtml1.setHtml(
                "<h3>Hello Cat</h3>" +
                "<ul>" +
                        "<li>Cat's WIKI</li>" +
                "</ul><br>" +
                "<img src=\"cat\"/>"+"<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;猫，属于<b>猫科动物</b>，分<font color='#ff6666'>家猫、野猫</font>，是全世界家庭中较为广泛的宠物。家猫的祖先据推测是起源于古埃及的沙漠猫，波斯的波斯猫，已经被人类驯化了3500年（但未像狗一样完全地被驯化）。\n" +
                        "一般的猫：头圆、颜面部短，前肢五指，后肢四趾，趾端具锐利而弯曲的爪，爪能伸缩。夜行性。\n" +
                        "以伏击的方式猎捕其他动物，大多能攀缘上树。猫的趾底有脂肪质肉垫，以免在行走时发出声响，捕猎时也不会惊跑鼠。行进时爪子处于收缩状态，防止爪被磨钝，在捕鼠和攀岩时会伸出来。</p>",
                HtmlResImageGetter(tvHtml1))

        tvHtml2.setHtml("百度<img src=\"https://www.baidu.com/img/bd_logo1.png\"/>", HtmlHttpImageGetter(tvHtml2))


    }

}
