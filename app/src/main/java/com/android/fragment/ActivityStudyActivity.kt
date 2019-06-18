package com.android.fragment

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.activity.SystenActivityUtils
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_study.*

@LayoutId(R.layout.activity_study)
class ActivityStudyActivity : KotlinActivity(),SystenActivityUtils {

    override fun init(bundle: Bundle?) {
        readView1.setText("""
Activity的显示启动
①通过指定目标Activity的class来启动
标准写法：
startActivity(Intent(this,NewActivity::class.java))
简略写法：
go(NewActivity::class.java)
②通过指定目标Activity的classname来启动
标准写法：
startActivity(Intent(this,Class.forName("com.android.gesture.NewActivity")))
简略写法：
go("com.android.gesture.NewActivity")

Activity的隐式启动
通过Intent-filter的Action,Category或data来实现
标准写法：
startActivity(Intent().apply {
    action = "my_action"
    addCategory("my_category")
})
简略写法：
go("my_action","my_category")
        """.trimIndent())
        btnGo.click {
            goByName("com.android.gesture.NewActivity")
        }

        btnCallPhone.click {
        }

    }

    fun Activity.goByName(className:String){
        startActivity(Intent(this, Class.forName(className)))
    }

}
