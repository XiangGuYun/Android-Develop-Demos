package com.android.anim

import android.graphics.drawable.Animatable
import android.os.Bundle
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_svganim.*
import android.view.animation.AccelerateDecelerateInterpolator
import com.android.R
import android.graphics.Path


@LayoutId(R.layout.activity_svganim)
class SVGAnimActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header1.setRightClick {
            codeDialog.text("""
btn1.click {
    (ivTest.drawable as Animatable).start()
}

<ImageView
    android:id="@+id/ivTest"
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:src="@drawable/my_svg"/>

//my_svg.xml
<?xml version="1.0" encoding="utf-8"?>
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/my_vector">
    <target
        android:name="path1"
        android:animation="@anim/path1" />
    <target
        android:name="path2"
        android:animation="@anim/path2" />
</animated-vector>

//my_vector.xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="200dp"
    android:height="200dp"
    android:viewportHeight="100"
    android:viewportWidth="100">

    <group>
        <path
            android:name="path1"
            android:pathData="
            M 20,80
            L 50,80 80,80"
            android:strokeColor="@android:color/holo_green_dark"
            android:strokeLineCap="round"
            android:strokeWidth="5" />

        <path
            android:name="path2"
            android:pathData="
            M 20,20
            L 50,20 80,20"
            android:strokeColor="@android:color/holo_green_dark"
            android:strokeLineCap="round"
            android:strokeWidth="5" />
    </group>

</vector>

//path1.xml
<?xml version="1.0" encoding="utf-8"?>
<objectAnimator  xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="5000"
    android:interpolator="@android:anim/bounce_interpolator"
    android:propertyName="pathData"
    android:valueFrom="
            M 20,80
            L 50,80 80,80"
    android:valueTo="
            M 20,80
            L 50,50 80,80"
    android:valueType="pathType" />

//path2.xml
<?xml version="1.0" encoding="utf-8"?>
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="5000"
    android:interpolator="@android:anim/bounce_interpolator"
    android:propertyName="pathData"
    android:valueFrom="
            M 20,20
            L 50,20 80,20"
    android:valueTo="
            M 20,20
            L 50,50 80,20"
    android:valueType="pathType" />

            """.trimIndent())
        }

        btn1.click {
            (ivTest.drawable as Animatable).start()
        }

        header2.setLeftClick {
            codeDialog.text("""
这里必须推荐一下https://github.com/geftimov/android-pathview这个库。
在这个案例中，我将演示如何使用该库来加载SVG动画文件。
首先，我们需要用到来自该库的com.eftimoff.androipathview.PathView，该View有三条重要的属性：
① pathColor="@android:color/holo_green_light" 设置路径颜色
② svg="@raw/monitor" 设置svg文件来源
③ pathWidth="1dp" 设置路径宽度

除此之外，PathView还有一些重要的方法
//使用SVG自带的颜色
pathView1.useNaturalColors()

//是否在绘制路径之后填充颜色
pathView1.setFillAfter(false)

pathView1
    .pathAnimator//设置路径动画
    .sequentialPathAnimator//设置序列路径动画
    .delay(100)//动画延迟多少毫秒开始
    .duration(2000)//动画持续多少毫秒
    .listenerStart(AnimationListenerStart())//监听动画开始事件
    .listenerEnd(AnimationListenerEnd())//监听动画结束事件
    .interpolator(AccelerateDecelerateInterpolator())//设置插值器
    .start()//开始动画
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
pathView1.useNaturalColors()
pathView1.setFillAfter(true)
pathView1
        .pathAnimator
        .delay(100)
        .duration(2000)
        .interpolator(AccelerateDecelerateInterpolator())
        .start()

<com.eftimoff.androipathview.PathView
    android:id="@+id/pathView1"
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:layout_marginTop="10dp"
    android:layout_gravity="center"
    app:pathColor="@android:color/holo_green_light"
    app:svg="@raw/monitor"
    app:pathWidth="1dp"/>
            """.trimIndent())
        }

        btn2.click {
            //使用SVG自带的颜色
            pathView1.useNaturalColors()

            pathView1.setFillAfter(true)
            pathView1
                    .pathAnimator
//                    .sequentialPathAnimator
                    .delay(100)
                    .duration(2000)
//                    .listenerStart(AnimationListenerStart())
//                    .listenerEnd(AnimationListenerEnd())
                    .interpolator(AccelerateDecelerateInterpolator())
                    .start()
        }

        header3.setRightClick {
            codeDialog.text("""
val path = Path()
val length = 200.dp2px().toFloat()
val height = 200.dp2px().toFloat()
path.moveTo(0.0f, 0.0f)
path.lineTo(length / 4f, 0.0f)
path.lineTo(length, height / 2.0f)
path.lineTo(length / 4f, height)
path.lineTo(0.0f, height)
path.lineTo(length * 3f / 4f, height / 2f)
path.lineTo(0.0f, 0.0f)
path.close()
pathView2.setPath(path)

btn3.click {
    pathView2
            .pathAnimator
            .delay(100)
            .duration(2000)
            .interpolator(AccelerateDecelerateInterpolator())
            .start()
}
            """.trimIndent())
        }

        val path = Path()
        val length = 200.dp2px().toFloat()
        val height = 200.dp2px().toFloat()
        path.moveTo(0.0f, 0.0f)
        path.lineTo(length / 4f, 0.0f)
        path.lineTo(length, height / 2.0f)
        path.lineTo(length / 4f, height)
        path.lineTo(0.0f, height)
        path.lineTo(length * 3f / 4f, height / 2f)
        path.lineTo(0.0f, 0.0f)
        path.close()
        pathView2.setPath(path)

        btn3.click {
            pathView2
                    .pathAnimator
                    .delay(100)
                    .duration(2000)
                    .interpolator(AccelerateDecelerateInterpolator())
                    .start()
        }

    }

}
