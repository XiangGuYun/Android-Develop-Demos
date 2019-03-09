package com.android.systemui

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import com.android.R
import com.android.systemui.test.ET1Activity
import com.android.systemui.test.ET2Activity
import com.android.systemui.test.ET3Activity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_edit_text.*


@LayoutId(R.layout.activity_edit_text)
class EditTextActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header2.setLeftClick {
            codeDialog.text("""
可选参数如下：

文本类型，多为大写、小写和数字符号
android:inputType="none"
android:inputType="text"
android:inputType="textCapCharacters"
android:inputType="textCapWords"
android:inputType="textCapSentences"
android:inputType="textAutoCorrect"
android:inputType="textAutoComplete"
android:inputType="textMultiLine"
android:inputType="textImeMultiLine"
android:inputType="textNoSuggestions"
android:inputType="textUri"
android:inputType="textEmailAddress"
android:inputType="textEmailSubject"
android:inputType="textShortMessage"
android:inputType="textLongMessage"
android:inputType="textPersonName"
android:inputType="textPostalAddress"
android:inputType="textPassword"
android:inputType="textVisiblePassword"
android:inputType="textWebEditText"
android:inputType="textFilter"
android:inputType="textPhonetic"

数值类型
android:inputType="number"//正整数
android:inputType="numberSigned"//正负整数
android:inputType="numberDecimal"//正负小数
android:inputType="phone"//拨号键盘
android:inputType="datetime"
android:inputType="date"//日期键盘
android:inputType="time"//时间键盘
            """.trimIndent())
        }

        header3.setLeftClick {
            codeDialog.text("""
                限制最小行：android:minLines="3"
                限制最大行：android:maxLines="3"
                限制单行：singleLine="true"
            """.trimIndent())
        }

        header4.setLeftClick {
            codeDialog.text("""
                可以通过下述两个属性来设置字的间距：
                android:textScaleX="1.5"    //设置字与字的水平间隔
                android:textScaleY="1.5"    //设置字与字的垂直间隔
            """.trimIndent())
        }

        header5.setLeftClick {
            codeDialog.text("""
android:windowSoftInputMode Activity主窗口与软键盘的交互模式，可以用来避免输入法面板遮挡问题，Android1.5后的一个新特性。
这个属性能影响两件事情：
【一】当有焦点产生时，软键盘是隐藏还是显示
【二】是否减少活动主窗口大小以便腾出空间放软键盘

简单点就是有焦点时的键盘控制以及是否减少Act的窗口大小，用来放小键盘
有下述值可供选择，可设置多个值，用"|"分开
stateUnspecified：软键盘的状态并没有指定，系统将选择一个合适的状态或依赖于主题的设置
stateUnchanged：当这个activity出现时，软键盘将一直保持在上一个activity里的状态，无论是隐藏还是显示
stateHidden：用户选择activity时，软键盘总是被隐藏
stateAlwaysHidden：当该Activity主窗口获取焦点时，软键盘也总是被隐藏的
stateVisible：软键盘通常是可见的
stateAlwaysVisible：用户选择activity时，软键盘总是显示的状态
adjustUnspecified：默认设置，通常由系统自行决定是隐藏还是显示
adjustResize：该Activity总是调整屏幕的大小以便留出软键盘的空间
adjustPan：当前窗口的内容将自动移动以便当前焦点从不被键盘覆盖和用户能总是看到输入内容的部分
            """.trimIndent())
        }

        header5.setRightClick {
            codeDialog.text("""
                 <activity android:name=".systemui.test.ET2Activity"
                    android:windowSoftInputMode="adjustResize|stateHidden"/>

                <activity android:name=".systemui.test.ET1Activity"
                    android:windowSoftInputMode="adjustResize|stateHidden"/>

                @LayoutId(R.layout.activity_et2)
                class ET2Activity : KotlinActivity() {
                    override fun init(bundle: Bundle?) {
                        showKeyboardDelay(etTest)//需要添加延迟时间才能
                    }
                }
            """.trimIndent())
        }

        btnET1.click {
            go(ET1Activity::class.java)
        }

        btnET2.click {
            go(ET2Activity::class.java)
        }

        header6.setLeftClick {
            codeDialog.text("""
                有时可能需要我们控制EditText中的光标移动到指定位置或者选中某些文本！
                EditText为我们提供了setSelection()的方法，方法有两种形式:

                一个参数的是设置光标位置的，两个参数的是设置起始位置与结束位置的中间括的部分，即部分选中！
                当然我们也可以调用setSelectAllOnFocus(true);让EditText获得焦点时选中全部文本！
                另外我们还可以调用setCursorVisible(false);设置光标不显示
                还可以调用getSelectionStart()和getSelectionEnd获得当前光标的前后位置
            """.trimIndent())
        }

        btnTestSelect.click {
            go(ET3Activity::class.java)
        }

        btnAddFace.click {
            val spanStr = SpannableString("imge")
            val drawable = resources.getDrawable(R.mipmap.ic_launcher_round)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            val span = ImageSpan(drawable, ImageSpan.ALIGN_BASELINE)
            spanStr.setSpan(span, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            val cursor = etFace.selectionStart
            etFace.text.insert(cursor, spanStr)
        }

    }

}
