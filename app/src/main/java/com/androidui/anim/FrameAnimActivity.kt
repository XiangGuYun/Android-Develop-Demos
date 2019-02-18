package com.androidui.anim

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_frame_anim.*


@LayoutId(R.layout.activity_frame_anim)
class FrameAnimActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        tvTop.text = """
特点
优点：使用简单、方便
缺点：容易引起 OOM，因为会使用大量 & 尺寸较大的图片资源
尽量避免使用尺寸较大的图片

应用场景
较为复杂的个性化动画效果。

使用时一定要避免使用尺寸较大的图片，否则会引起OOM
        """.trimIndent()

        header1.setRightClick {
            codeDialog.text("""
步骤1：在 res/drawable的文件夹里创建动画效果.xml文件

步骤2：设置动画资源（图片资源）
<animation-list
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="true" // 设置是否只播放一次，默认为false
    >
    // item = 动画图片资源；duration = 设置一帧持续时间(ms)
    <item android:drawable="@drawable/a0" android:duration="100"/>
    <item android:drawable="@drawable/a1" android:duration="100"/>
    <item android:drawable="@drawable/a2" android:duration="100"/>
</animation-list>

步骤3：在Java代码中载入 & 启动动画
// 1. 设置动画
iv1.setImageResource(R.drawable.frame1)
// 2. 获取动画对象
val animationDrawable = iv1.drawable as AnimationDrawable
// 3. 启动动画
btnStart.click {
    animationDrawable.start()
}
// 4. 停止动画
btnStop.click {
    animationDrawable.stop()
}
            """.trimIndent())
        }

        // 1. 设置动画
        iv1.setImageResource(R.drawable.frame1)
        // 2. 获取动画对象
        val animationDrawable = iv1.drawable as AnimationDrawable
        // 3. 启动动画
        btnStart.click {
            animationDrawable.stop()
            animationDrawable.start()
        }
        // 4. 停止动画
        btnStop.click {
            animationDrawable.stop()
        }

        /*
        代码实现
         */

        header2.setRightClick {
            codeDialog.text("""
val animDrawable = AnimationDrawable()

for (i in 1 until 4) {
    val id = resources.getIdentifier("a'$'i", "drawable", packageName)
    val drawable = resources.getDrawable(id)
    animDrawable.addFrame(drawable, 1000)
}
animDrawable.isOneShot = false
iv2.setImageDrawable(animDrawable)

btnStart1.click {
    animDrawable.stop()
    animDrawable.start()
}

btnStop1.click {
    animDrawable.stop()
}
            """.trimIndent())
        }

        val animDrawable = AnimationDrawable()

        for (i in 1 until 4) {
            val id = resources.getIdentifier("a$i", "mipmap", packageName)
            val drawable = resources.getDrawable(id)
            animDrawable.addFrame(drawable, 1000)
        }
        animDrawable.isOneShot = false
        iv2.setImageDrawable(animDrawable)

        btnStart1.click {
            animDrawable.stop()
            animDrawable.start()
        }

        btnStop1.click {
            animDrawable.stop()
        }

    }

}
