package com.androidui.supportlib.constraint

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_constraint.*

@LayoutId(R.layout.activity_constraint)
class ConstraintActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        header3.setLeftClick { codeDialog.text("""

要使某控件横向偏移百分比属性生效，必须要指定其在谁的右边和在谁的左边

要使某控件竖向偏移百分比属性生效，必须要指定其在谁的下边和在谁的上边

偏移的值范围是0~1
        """.trimIndent()) }
        btn10.click {
            go(ConstraintCase1Activity::class.java)
        }
    }

}
