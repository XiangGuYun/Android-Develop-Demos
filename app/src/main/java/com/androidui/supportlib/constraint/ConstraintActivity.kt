package com.androidui.supportlib.constraint

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_constraint.*
import kotlinx.android.synthetic.main.activity_path.*

@LayoutId(R.layout.activity_constraint)
class ConstraintActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        header3.setLeftClick { codeDialog.text("""

要使某控件横向偏移百分比属性生效，必须要指定其在谁的右边和在谁的左边

要使某控件竖向偏移百分比属性生效，必须要指定其在谁的下边和在谁的上边

偏移的值范围是0~1
        """.trimIndent()) }.setRightClick {
            codeDialog.text("""
 <Button
    android:id="@+id/btn1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="左边"
    style="@style/btn_test"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintHorizontal_bias="0.5"
    app:layout_constraintVertical_bias="0.5"
    />

<Button
    android:id="@+id/btn2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="中间"
    style="@style/btn_test"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintLeft_toRightOf="@+id/btn1"
    app:layout_constraintRight_toLeftOf="@+id/btn3"
    app:layout_constraintHorizontal_bias="0.5"
    />

<Button
    android:id="@+id/btn3"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="右边"
    style="@style/btn_test"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintHorizontal_bias="0.5"
    app:layout_constraintVertical_bias="0.5"
    />

<TextView
    android:id="@+id/tv1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/small_title"
    app:layout_constraintTop_toBottomOf="@id/btn1"
    android:text="app:layout_constraintHorizontal_bias='0.5'"/>

<Button
    android:id="@+id/btn4"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="左边"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/tv1"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintHorizontal_bias="0.5"
    app:layout_constraintVertical_bias="0.5"
    />

<Button
    android:id="@+id/btn5"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="中间"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/tv1"
    app:layout_constraintLeft_toRightOf="@+id/btn1"
    app:layout_constraintRight_toLeftOf="@+id/btn3"
    app:layout_constraintHorizontal_bias="0"
    />

<Button
    android:id="@+id/btn6"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="右边"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/tv1"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintHorizontal_bias="0.5"
    app:layout_constraintVertical_bias="0.5"
    />

<TextView
    android:id="@+id/tv2"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/small_title"
    app:layout_constraintTop_toBottomOf="@id/btn4"
    android:text="app:layout_constraintHorizontal_bias='0'"/>

<Button
    android:id="@+id/btn7"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="左边"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/tv2"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintHorizontal_bias="0.5"
    app:layout_constraintVertical_bias="0.5"
    />

<Button
    android:id="@+id/btn8"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="中间"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/tv2"
    app:layout_constraintLeft_toRightOf="@+id/btn1"
    app:layout_constraintRight_toLeftOf="@+id/btn3"
    app:layout_constraintHorizontal_bias="1"
    />

<Button
    android:id="@+id/btn9"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="右边"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/tv2"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintHorizontal_bias="0.5"
    app:layout_constraintVertical_bias="0.5"
    />

<TextView
    android:id="@+id/tv3"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/small_title"
    app:layout_constraintTop_toBottomOf="@id/btn7"
    android:text="app:layout_constraintHorizontal_bias='1'"/>

<Button
    android:id="@+id/btn10"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="上边"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/tv3"
    app:layout_constraintLeft_toLeftOf="parent"
    />

<Button
    android:id="@+id/btn11"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="中间"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/btn10"
    app:layout_constraintBottom_toTopOf="@+id/btn12"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintVertical_bias="0.5"
    />

<Button
    android:id="@+id/btn12"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="下边"
    style="@style/btn_test"
    app:layout_constraintBottom_toTopOf="@id/line1"
    app:layout_constraintLeft_toLeftOf="parent"
    />

<Button
    android:id="@+id/btn13"
    style="@style/btn_test"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="上边"
    app:layout_constraintTop_toBottomOf="@id/tv3"
    app:layout_constraintLeft_toRightOf="@id/btn10"
    app:layout_constraintRight_toLeftOf="@+id/btn16"
    />

<Button
    android:id="@+id/btn14"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="中间"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/btn10"
    app:layout_constraintBottom_toTopOf="@+id/btn12"
    app:layout_constraintLeft_toRightOf="@id/btn10"
    app:layout_constraintVertical_bias="0.3"
    app:layout_constraintRight_toLeftOf="@+id/btn16"
    />

<Button
    android:id="@+id/btn15"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="下边"
    style="@style/btn_test"
    app:layout_constraintBottom_toTopOf="@id/line1"
    app:layout_constraintLeft_toRightOf="@id/btn10"
    app:layout_constraintRight_toLeftOf="@+id/btn16"
    />

<Button
    android:id="@+id/btn16"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="上边"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/tv3"
    app:layout_constraintRight_toRightOf="parent"
    />

<Button
    android:id="@+id/btn17"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="中间"
    style="@style/btn_test"
    app:layout_constraintTop_toBottomOf="@+id/btn16"
    app:layout_constraintBottom_toTopOf="@+id/btn18"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintVertical_bias="0.7"
    />

<Button
    android:id="@+id/btn18"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="下边"
    style="@style/btn_test"
    app:layout_constraintBottom_toTopOf="@id/line1"
    app:layout_constraintRight_toRightOf="parent"
    />

<android.support.constraint.Guideline
    android:id="@+id/line1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    app:layout_constraintGuide_percent="0.9" />

<TextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/small_title"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/line1"
    android:text="从左往右的layout_constraintVertical_bias值分别是0.5，0.3，0.7。"/>
            """.trimIndent())
        }
        btn10.click {
            go(ConstraintCase1Activity::class.java)
        }

        header5.setLeftClick {
            codeDialog.text("""
问题1：如何将图片横向居中？
app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent"

问题1：如何控制花朵相对于中心图的距离，角度？
//该属性用来指定圆心
app:layout_constraintCircle="@+id/love"
//该属性用来指定半径
app:layout_constraintCircleRadius="100dp"
//该属性用来指定角度，正上方为0度，正下方为180度
app:layout_constraintCircleAngle="0"
            """.trimIndent())
        }
        .setRightClick {
            codeDialog.text("""
<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/flower"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header5"
    app:layout_constraintCircle="@+id/love"
    app:layout_constraintCircleRadius="100dp"
    app:layout_constraintCircleAngle="0" />

<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/flower"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header5"
    app:layout_constraintCircle="@+id/love"
    app:layout_constraintCircleRadius="100dp"
    app:layout_constraintCircleAngle="90" />

<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/flower"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header5"
    app:layout_constraintCircle="@+id/love"
    app:layout_constraintCircleRadius="100dp"
    app:layout_constraintCircleAngle="135" />

<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/flower"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header5"
    app:layout_constraintCircle="@+id/love"
    app:layout_constraintCircleRadius="100dp"
    app:layout_constraintCircleAngle="180" />

<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/flower"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header5"
    app:layout_constraintCircle="@+id/love"
    app:layout_constraintCircleRadius="100dp"
    app:layout_constraintCircleAngle="225" />

<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/flower"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header5"
    app:layout_constraintCircle="@+id/love"
    app:layout_constraintCircleRadius="100dp"
    app:layout_constraintCircleAngle="270" />

<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/flower"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header5"
    app:layout_constraintCircle="@+id/love"
    app:layout_constraintCircleRadius="100dp"
    app:layout_constraintCircleAngle="315" />

<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/flower"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header5"
    app:layout_constraintCircle="@+id/love"
    app:layout_constraintCircleRadius="100dp"
    app:layout_constraintCircleAngle="45" />

<de.hdodenhof.circleimageview.CircleImageView
    android:id="@+id/love"
    app:layout_constraintTop_toBottomOf="@+id/header5"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    android:layout_marginTop="70dp"
    android:layout_width="96dp"
    android:layout_height="96dp"
    android:src="@mipmap/love"
    app:civ_border_width="2dp"
    app:civ_border_color="#FF000000"/>
            """.trimIndent())
        }

        header6.setLeftClick {
           webDialog.url("view/constraint_width_height")
        }.setRightClick {
            codeDialog.text("""
    <TextView
        android:id="@+id/tvCons1"
        android:layout_marginTop="10dp"
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:background="#ff6666"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/header6"/>

    <TextView
        android:id="@+id/tvCons2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#66ff66"
        android:padding="10dp"
        android:text="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
        app:layout_constraintLeft_toRightOf="@+id/tvCons1"
        app:layout_constraintTop_toBottomOf="@+id/tvCons1"
        app:layout_constraintRight_toRightOf="parent"/>

    <TextView
        android:id="@+id/tvCons3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#6666ff"
        android:padding="10dp"
        android:text="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
        app:layout_constraintLeft_toRightOf="@+id/tvCons1"
        app:layout_constraintTop_toBottomOf="@+id/tvCons2"
        app:layout_constrainedWidth="true"
        app:layout_constraintRight_toRightOf="parent"/>
            """.trimIndent())
        }

        header7.setLeftClick {
           webDialog.url("view/chain")
        }

        header8.setLeftClick {
            codeDialog.text("""
在约束布局中宽高的维度 match_parent  被 0dp 代替，默认生成的大小占所有的可用空间。那么有以下几个属性可以使用：

layout_constraintWidth_min and layout_constraintHeight_min  //设置最小尺寸
layout_constraintWidth_max and layout_constraintHeight_max  //设置最大尺寸
layout_constraintWidth_percent and layout_constraintHeight_percent  //设置相对于父类的百分比
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<TextView
    android:layout_width="0dp"
    android:layout_height="50dp"
    android:layout_marginTop="10dp"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header8"
    android:background="#ff6666"
    app:layout_constraintWidth_percent="0.5"/>
            """.trimIndent())
        }

        header9.setLeftClick {
            codeDialog.text("""
当约束目标的可见性为View.GONE时，还可以通过以下属性设置不同的边距值：

layout_goneMarginStart
layout_goneMarginEnd
layout_goneMarginLeft
layout_goneMarginTop
layout_goneMarginRight
layout_goneMarginBottom
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
 <TextView
    android:layout_marginTop="10dp"
    android:layout_width="100dp"
    android:layout_height="50dp"
    android:visibility="gone"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header9"
    android:background="#ff6666"
    android:id="@+id/tvRedGone"/>

<TextView
    android:layout_marginTop="10dp"
    android:layout_width="100dp"
    android:layout_height="50dp"
    app:layout_constraintLeft_toRightOf="@+id/tvRedGone"
    app:layout_constraintTop_toBottomOf="@id/header9"
    android:background="#66ff66"
    app:layout_goneMarginLeft="100dp"
    android:id="@+id/tvRedShow"/>
            """.trimIndent())
        }

        headerGuideline.setLeftClick {
            codeDialog.text("""
Guideline 与 LinearLayout 类似可以设置水平或垂直方向，android:orientation="horizontal"，android:orientation="vertical"，水平方向高度为0，垂直方向宽度为0。Guideline 具有以下的三种定位方式：

layout_constraintGuide_begin
距离父容器起始位置的距离（左侧或顶部）
layout_constraintGuide_end
距离父容器结束位置的距离（右侧或底部）
layout_constraintGuide_percent
距离父容器宽度或高度的百分比

例如，设置一条垂直方向距离父控件左侧为100dp的Guideline：
<android.support.constraint.Guideline
    android:layout_width="wrap_content"
    android:orientation="vertical"
    app:layout_constraintGuide_begin="100dp"
    android:layout_height="wrap_content"/>
            """.trimIndent())
        }

        headerBarrier.setLeftClick {
            codeDialog.text("""
指定障碍方向
app:barrierDirection="end"
指定引用的控件ID
app:constraint_referenced_ids="account,password"

注意点1：与barrier有约束关系的控件宽高值千万别设置为match_parent，否则barrier将会无效
注意点2：layout_constraintRight_toRightOf无法与layout_constraintStart_toEndOf搭配
注意点3：barrier是无法得到预览效果的
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
    <TextView
        android:id="@+id/account"
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:text="账号"
        android:gravity="center"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:background="#ff6666"/>

    <TextView
        android:id="@+id/password"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/account"
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:text="密码"
        android:gravity="center"
        android:background="#66ff66"/>

    <android.support.constraint.Barrier
        android:id="@+id/barrier"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:barrierDirection="end"
        app:constraint_referenced_ids="account,password"/>

    <!--注意点1：这里宽度千万别设置为match_parent，否则barrier将会无效-->
    <!--注意点2：layout_constraintRight_toRightOf无法与layout_constraintStart_toEndOf搭配-->
    <!--注意点3：barrier是无法得到预览效果的-->
    <EditText
        android:id="@+id/etAccount"
        android:layout_width="0dp"
        android:layout_height="50dp"
        app:layout_constraintLeft_toRightOf="@+id/barrier"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>

    <EditText
        android:id="@+id/etPassword"
        app:layout_constraintTop_toBottomOf="@id/etAccount"
        android:layout_width="0dp"
        android:layout_height="50dp"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintLeft_toRightOf="@+id/barrier"/>
            """.trimIndent())
        }
        btnBarrier.click {
            go(ConstraintCase2Activity::class.java)
        }

        headerGroup.setLeftClick {
            codeDialog.text("Group用于控制多个控件的可见性。\napp:constraint_referenced_ids=\"account,password\"：用于指定被包裹的控件。\n在该例子中，绿色矩形在红色矩形右边，但是红色矩形被不可见的Group包裹了，因此看不到。")
        }.setRightClick {
            codeDialog.text("""
<android.support.constraint.Group
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:visibility="invisible"
        app:constraint_referenced_ids="tvVisible1"/>

<TextView
    android:id="@+id/tvVisible1"
    android:layout_width="100dp"
    android:layout_height="50dp"
    android:background="#FF6666"
    android:layout_marginTop="10dp"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/headerGroup"/>

<TextView
    android:id="@+id/tvVisible2"
    android:layout_width="100dp"
    android:layout_height="50dp"
    android:background="#66ff66"
    android:layout_marginTop="10dp"
    app:layout_constraintLeft_toRightOf="@+id/tvVisible1"
    app:layout_constraintTop_toBottomOf="@id/headerGroup"/>
            """.trimIndent())
        }

        header4.setRightClick {
            codeDialog.text("""
 <TextView
    android:layout_marginTop="10dp"
    app:layout_constraintHorizontal_weight="1"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header4"
    app:layout_constraintVertical_weight="1"
    app:layout_constraintRight_toLeftOf="@+id/btnWeight2"
    android:layout_width="0dp"
    android:layout_height="50dp"
    android:background="#ff6666"
    android:id="@+id/btnWeight1"/>

<TextView
    android:layout_width="0dp"
    android:layout_height="50dp"
    android:background="#66ff66"
    android:layout_marginTop="10dp"
    app:layout_constraintLeft_toRightOf="@id/btnWeight1"
    app:layout_constraintRight_toLeftOf="@id/btnWeight3"
    app:layout_constraintTop_toBottomOf="@id/header4"
    app:layout_constraintHorizontal_weight="1"
    android:id="@+id/btnWeight2"/>

<TextView
    android:layout_marginTop="10dp"
    app:layout_constraintHorizontal_weight="1"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toBottomOf="@id/header4"
    app:layout_constraintLeft_toRightOf="@id/btnWeight2"
    android:layout_width="0dp"
    android:layout_height="50dp"
    android:background="#6666ff"
    android:id="@+id/btnWeight3"/>

<TextView
    android:text="红绿蓝的横向权重值分别是1，1，1，采用默认的权重样式是spread。需要注意的是：\n1.要让权重属性生效，相邻的控件必须相互指定左右约束关系。\n2.要让spread样式生效，宽度要设置为0dp。\n接下来看看另外两种样式。"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:id="@+id/tv4"
    style="@style/small_title"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/btnWeight1"/>

<TextView
    android:layout_marginTop="10dp"
    android:id="@+id/tvStyle1"
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:layout_marginBottom="10dp"
    android:background="#ff6666"
    app:layout_constraintHorizontal_weight="1"
    app:layout_constraintHorizontal_chainStyle="packed"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toLeftOf="@id/tvStyle2"
    app:layout_constraintTop_toBottomOf="@id/tv4"/>

<TextView
    android:layout_marginTop="10dp"
    android:id="@+id/tvStyle2"
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:layout_marginBottom="10dp"
    android:background="#66ff66"
    app:layout_constraintHorizontal_weight="1"
    app:layout_constraintHorizontal_chainStyle="packed"
    app:layout_constraintLeft_toRightOf="@id/tvStyle1"
    app:layout_constraintRight_toLeftOf="@id/tvStyle3"
    app:layout_constraintTop_toBottomOf="@id/tv4"/>

<TextView
    android:layout_marginTop="10dp"
    android:id="@+id/tvStyle3"
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:background="#6666ff"
    app:layout_constraintHorizontal_chainStyle="packed"
    app:layout_constraintLeft_toRightOf="@id/tvStyle2"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tv4"/>

<TextView
    android:id="@+id/tv5"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/small_title"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tvStyle1"
    android:gravity="center"
    android:text="packed样式"/>

<TextView
    android:layout_marginTop="10dp"
    android:id="@+id/tvStyle4"
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:layout_marginBottom="10dp"
    android:background="#ff6666"
    app:layout_constraintHorizontal_weight="2"
    app:layout_constraintHorizontal_chainStyle="spread_inside"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toLeftOf="@id/tvStyle5"
    app:layout_constraintTop_toBottomOf="@id/tv5"/>

<TextView
    android:layout_marginTop="10dp"
    android:id="@+id/tvStyle5"
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:layout_marginBottom="10dp"
    android:background="#66ff66"
    app:layout_constraintHorizontal_weight="1"
    app:layout_constraintHorizontal_chainStyle="spread_inside"
    app:layout_constraintLeft_toRightOf="@id/tvStyle4"
    app:layout_constraintRight_toLeftOf="@id/tvStyle6"
    app:layout_constraintTop_toBottomOf="@id/tv5"/>

<TextView
    android:layout_marginTop="10dp"
    android:id="@+id/tvStyle6"
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:background="#6666ff"
    app:layout_constraintHorizontal_chainStyle="spread_inside"
    app:layout_constraintLeft_toRightOf="@id/tvStyle5"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tv5"/>

<TextView
    android:id="@+id/tv6"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/small_title"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tvStyle4"
    android:gravity="center"
    android:text="spread_inside样式"/>
            """.trimIndent())
        }

        header.setRightClick {
            codeDialog.text("""
<Button
    android:id="@+id/btn1"
    android:text="Button1"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/header"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/btn_test"/>

<Button
    android:id="@+id/btn2"
    android:text="Button2"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/btn1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/btn_test"/>

<Button
    android:id="@+id/btn3"
    android:text="Button3"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/btn2"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/btn_test"/>

<Button
    android:id="@+id/btn4"
    android:text="Button4"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/btn3"
    android:layout_width="100dp"
    android:layout_height="wrap_content"
    style="@style/btn_test"/>

<Button
    android:id="@+id/btn5"
    android:text="Button5"
    app:layout_constraintLeft_toRightOf="@id/btn4"
    app:layout_constraintRight_toLeftOf="@+id/btn6"
    app:layout_constraintTop_toBottomOf="@+id/btn3"
    android:layout_width="100dp"
    android:layout_height="wrap_content"
    style="@style/btn_test"/>

<Button
    android:id="@+id/btn6"
    android:text="Button6"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintBaseline_toBaselineOf="@id/btn5"
    android:layout_width="100dp"
    android:layout_height="wrap_content"
    style="@style/btn_test"/>

<TextView
    android:id="@+id/tv1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    style="@style/small_title"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/btn6"
    android:text="理解Baseline对齐"
    android:gravity="center_horizontal"/>

<Button
    android:id="@+id/btn7"
    android:layout_width="150dp"
    android:layout_height="100dp"
    style="@style/btn_test"
    android:text="Baseline1"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tv1"/>

<Button
    android:layout_width="150dp"
    android:layout_height="60dp"
    style="@style/btn_test"
    android:text="Baseline2"
    app:layout_constraintLeft_toRightOf="@+id/btn7"
    app:layout_constraintBaseline_toBaselineOf="@+id/btn7"
    />
            """.trimIndent())
        }

        header2.setLeftClick {
            codeDialog.text("""
//宽高比为16:6，只有在宽高值均为0dp时才有效
app:layout_constraintDimensionRatio="H,16:6"
//宽高比为6:16，只有在宽高值均为0dp时才有效
app:layout_constraintDimensionRatio="W,16:6"
//宽高比为16:6，只有在宽高值至少一个是0dp时才有效
app:layout_constraintDimensionRatio="16:6"
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
 <TextView
    android:id="@+id/tvRatio1"
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:layout_marginTop="10dp"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    android:background="#ff6666"
    android:text="4:1"
    android:gravity="center"
    android:textSize="30sp"
    app:layout_constraintTop_toBottomOf="@+id/header2"
    app:layout_constraintDimensionRatio="W,1:4"/>

<TextView
    android:id="@+id/btn8"
    android:layout_width="0dp"
    android:layout_height="0dp"
    app:layout_constraintDimensionRatio="3:1"
    android:background="#66ff66"
    android:text="3:1"
    app:layout_constraintHorizontal_weight="1"
    android:gravity="center"
    android:textSize="30sp"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toLeftOf="@id/btn9"
    app:layout_constraintTop_toBottomOf="@id/tvRatio1"/>

<TextView
    android:id="@+id/btn9"
    app:layout_constraintDimensionRatio="1:1"
    android:layout_height="0dp"
    android:layout_width="0dp"
    android:background="#6666ff"
    app:layout_constraintHorizontal_weight="1"
    android:gravity="center"
    android:textSize="30sp"
    android:text="1:1"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintLeft_toRightOf="@id/btn8"
    app:layout_constraintTop_toBottomOf="@id/tvRatio1"/>

<TextView
    android:id="@+id/tvRatio2"
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:background="#33dd33"
    app:layout_constraintRight_toRightOf="@id/btn8"
    app:layout_constraintLeft_toLeftOf="parent"
    android:text="3:2"
    android:gravity="center"
    android:textSize="30sp"
    app:layout_constraintBottom_toBottomOf="@id/btn9"
    app:layout_constraintTop_toBottomOf="@id/btn8"
    />

<TextView
    android:id="@+id/tvRatio3"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_constraintDimensionRatio="10:3"
    app:layout_constraintLeft_toLeftOf="parent"
    android:background="#ff6666"
    android:text="10:5"
    android:gravity="center"
    android:textSize="30sp"
    app:layout_constraintTop_toBottomOf="@id/btn9"/>
            """.trimIndent())
        }

    }

}
