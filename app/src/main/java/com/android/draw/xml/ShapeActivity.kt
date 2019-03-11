package com.android.draw.xml

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_shape.*

@LayoutId(R.layout.activity_shape)
class ShapeActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        tv1.setSimpleClickText {
            codeDialog.text("""
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    //默认为rectangle
    android:shape=["rectangle"|"oval"|"line"|"ring"]>
    <corners //当shape="rectangle"时使用
        //半径，会被后面的单个半径属性覆盖
        android:radius="integer"
        android:topLeftRadius="integer"
        android:topRightRadius="integer"
        android:bottomLegtRadius="integer"
        android:bottomRightRadius="integer"/>
    <gradient   //渐变
        android:angle="integer"
        android:centerX="integer"
        android:centerY="integer"
        android:centerColor="color"
        android:endColor="color"
        android:gradientRadius="integer"
        android:startColor="color"
        android:type=["linear"|"radial"|"sweep"]
        android:useCenter=["true"|"false"]/>
    <padding
        android:left="integer"
        android:top="integer"
        android:right="integer"
        android:bottom="integer"/>
    <size //指定大小，一般用在ImageView配合scaleType属性使用
        android:width="integer"
        android:height="integer"/>
    <solid  //填充颜色
        android:color="color"/>
    <stroke //指定边框
        android:width="integer"
        android:color="color"
        //虚线宽度
        android:dashWidth="integer"
        //虚线间隔宽度
        android:dashGap="integer"/>
</shape>
            """.trimIndent())
        }

        header1.setLeftClick {
            codeDialog.text("""
rectangle

solid: 设置形状填充的颜色，只有android:color一个属性
android:color 填充的颜色

padding: 设置内容与形状边界的内间距，可分别设置左右上下的距离
android:left 左内间距
android:right 右内间距
android:top 上内间距
android:bottom 下内间距

gradient: 设置形状的渐变颜色，可以是线性渐变、辐射渐变、扫描性渐变
android:type 渐变的类型
    linear 线性渐变，默认的渐变类型
    radial 放射渐变，设置该项时，android:gradientRadius也必须设置
    sweep 扫描性渐变
android:startColor 渐变开始的颜色
android:endColor 渐变结束的颜色
android:centerColor 渐变中间的颜色
android:angle 渐变的角度，线性渐变时才有效，必须是45的倍数，0表示从左到右，90表示从下到上
android:centerX 渐变中心的相对X坐标，放射渐变时才有效，在0.0到1.0之间，默认为0.5，表示在正中间
android:centerY 渐变中心的相对X坐标，放射渐变时才有效，在0.0到1.0之间，默认为0.5，表示在正中间
android:gradientRadius 渐变的半径，只有渐变类型为radial时才使用
android:useLevel 如果为true，则可在LevelListDrawable中使用

corners: 设置圆角，只适用于rectangle类型，可分别设置四个角不同半径的圆角，当设置的圆角半径很大时，比如200dp，就可变成弧形边了
android:radius 圆角半径，会被下面每个特定的圆角属性重写
android:topLeftRadius 左上角的半径
android:topRightRadius 右上角的半径
android:bottomLeftRadius 左下角的半径
android:bottomRightRadius 右下角的半径

stroke: 设置描边，可描成实线或虚线。
android:color 描边的颜色
android:width 描边的宽度
android:dashWidth 设置虚线时的横线长度
android:dashGap 设置虚线时的横线之间的距离

<?xml version="1.0" encoding="utf-8"?><!-- android:shape指定形状类型，默认为rectangle -->
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <!-- solid指定形状的填充色，只有android:color一个属性 -->
    <solid android:color="#2F90BD" />


    <!-- padding设置内容区域离边界的间距 -->
    <padding
        android:bottom="12dp"
        android:left="12dp"
        android:right="12dp"
        android:top="12dp" />


    <!-- corners设置圆角，只适用于rectangle -->
    <corners android:radius="200dp" />


    <!-- stroke设置描边 -->
    <stroke
        android:width="2dp"
        android:color="@android:color/darker_gray"
        android:dashGap="4dp"
        android:dashWidth="4dp" />


</shape>
 """.trimIndent())
        }

        header2.setLeftClick {
            codeDialog.text("""
当采用线性渐变时，一般会用到以下属性：
<gradient
    android:type="linear"
    android:startColor="#ff6666"
    android:centerColor="#66ff66"
    android:endColor="#6666ff"
    android:angle="0" />

现在来看看angle为不同的值时的效果如何（注意angle必须是45的倍数或0）         """.trimIndent())
        }

        headerDash.setLeftClick {
            codeDialog.text("""
                dashWidth：该属性并不是指定虚线的粗细，而是指定一个虚线单位的长度。
                dashGap：指定虚线单位之间的间隙大小。
            """.trimIndent())
        }

        headerLine.setLeftClick {
            codeDialog.text("""
line主要用于画分割线，是通过stroke和size特性组合来实现的，先看虚线的代码：

<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="line">

    <stroke
        android:width="1dp"
        android:color="#FF0000" />
    <!-- 虚线的高度 -->
    <size android:height="4dp" />

</shape>

画线时，有几点特性必须要知道的：

只能画水平线，画不了竖线；
线的高度是通过stroke的android:width属性设置的；
size的android:height属性定义的是整个形状区域的高度；
size的height必须大于stroke的width，否则，线无法显示；
线在整个形状区域中是居中显示的；
线左右两边会留有空白间距，线越粗，空白越大；
引用虚线的view需要添加属性android:layerType，值设为”software”，否则显示不了虚线。
引用：

 <ImageView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="@drawable/bg_line_with_solid" />
            """.trimIndent())
        }

        headerRing.setLeftClick {
            codeDialog.text("""
首先，shape根元素有些属性只适用于ring类型，先过目下这些属性吧：


android:innerRadius 内环的半径
android:innerRadiusRatio 浮点型，以环的宽度比率来表示内环的半径，默认为3，表示内环半径为环的宽度除以3，该值会被 android:innerRadius覆盖
android:thickness 环的厚度
android:thicknessRatio 浮点型，以环的宽度比率来表示环的厚度，默认为9，表示环的厚度为环的宽度除以9，该值会被-android:thickness覆盖
android:useLevel 一般为false，否则可能环形无法显示，只有作为LevelListDrawable使用时才设为true
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:innerRadiusRatio="3"
    android:shape="ring"
    android:thicknessRatio="14"
    android:useLevel="false">
   <solid android:color="#ff6666"/>
</shape>

<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:innerRadiusRatio="3"
    android:shape="ring"
    android:thicknessRatio="9"
    android:useLevel="false">
    <gradient
        android:endColor="#66ff66"
        android:startColor="#FFFFFF"
        android:type="sweep" />
</shape>

<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:innerRadiusRatio="3"
    android:shape="ring"
    android:thicknessRatio="9"
    android:useLevel="false">
    <stroke
        android:color="#6666ff"
        android:width="2dp"/>
</shape>

<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromDegrees="0"
    android:pivotX="50%"
    android:pivotY="50%"
    android:toDegrees="1080.0">
    <shape xmlns:android="http://schemas.android.com/apk/res/android"
        android:innerRadiusRatio="3"
        android:shape="ring"
        android:thicknessRatio="9"
        android:useLevel="false">
        <gradient
            android:endColor="#2F90BD"
            android:startColor="#FFFFFF"
            android:type="sweep" />
    </shape>
</rotate>

<ProgressBar
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_margin="8dp"
        android:indeterminate="false"
        android:layout_gravity="center_horizontal"
        android:indeterminateDrawable="@drawable/shape_progress" />
            """.trimIndent())
        }



    }
}
