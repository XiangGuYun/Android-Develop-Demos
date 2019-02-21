package com.androidui.anim

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import com.androidui.R
import com.androidui.anim.other.ColorEvaluator
import com.androidui.anim.other.PlaneEvaluator
import com.androidui.anim.other.Point
import com.androidui.anim.other.ViewWrapper
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_property_anim.*


@LayoutId(R.layout.activity_property_anim)
class PropertyAnimActivity : KotlinActivity() {

    val str1 = """
1. 属性动画出现的原因

属性动画（Property Animation）是在 Android 3.0（API 11）后才提供的一种全新动画模式
那么为什么要提供属性动画（Property Animation）？

1.1 背景
实现动画效果在Android开发中非常常见，因此Android系统一开始就提供了两种实现动画的方式：

逐帧动画（Frame Animation）
补间动画（ Tweened animation ）

1.2 问题
逐帧动画 & 补间动画存在一定的缺点：
a. 作用对象局限：View
即补间动画 只能够作用在视图View上，即只可以对一个Button、TextView、甚至是LinearLayout、或者其它继承自View的组件进行动画操作，但无法对非View的对象进行动画操作

有些情况下的动画效果只是视图的某个属性 & 对象而不是整个视图；
如，现需要实现视图的颜色动态变化，那么就需要操作视图的颜色属性从而实现动画效果，而不是针对整个视图进行动画操作

b. 没有改变View的属性，只是改变视觉效果
补间动画只是改变了View的视觉效果，而不会真正去改变View的属性。
如，将屏幕左上角的按钮 通过补间动画 移动到屏幕的右下角
点击当前按钮位置（屏幕右下角）是没有效果的，因为实际上按钮还是停留在屏幕左上角，补间动画只是将这个按钮绘制到屏幕右下角，改变了视觉效果而已。

c. 动画效果单一
补间动画只能实现平移、旋转、缩放 & 透明度这些简单的动画需求
一旦遇到相对复杂的动画效果，即超出了上述4种动画效果，那么补间动画则无法实现。

即在功能 & 可扩展性有较大局限性

1.3 问题
为了解决补间动画的缺陷，在 Android 3.0（API 11）开始，系统提供了一种全新的动画模式：属性动画（Property Animation）
下面，我将全面介绍属性动画（Property Animation）

2. 简介
作用对象：任意 Java 对象
不再局限于 视图View对象
实现的动画效果：可自定义各种动画效果
不再局限于4种基本变换：平移、旋转、缩放 & 透明度

3. 特点
作用对象进行了扩展：不只是View对象，甚至没对象也可以
动画效果：不只是4种基本变换，还有其他动画效果
作用领域：API11后引入的

4. 工作原理
在一定时间间隔内，通过不断对值进行改变，并不断将该值赋给对象的属性，从而实现该对象在该属性上的动画效果，可以是任意对象的任意属性

从上述工作原理可以看出属性动画有两个非常重要的类：ValueAnimator 类 & ObjectAnimator 类
其实属性动画的使用基本都是依靠这两个类
所以，在下面介绍属性动画的具体使用时，我会着重介绍这两个类。

    """.trimIndent()

    override fun init(bundle: Bundle?) {
        readView.setText(str1)
        b1.click {
            codeDialog.text("""
定义：属性动画机制中 最核心的一个类
实现动画的原理：通过不断控制 值 的变化，再不断 手动 赋给对象的属性，从而实现动画效果。

ValueAnimator类中有3个重要方法：

ValueAnimator.ofInt（int values）
ValueAnimator.ofFloat（float values）
ValueAnimator.ofObject（int values）
            """.trimIndent())
        }

        h1.setLeftClick {
            codeDialog.text("""
作用：将初始值 以整型数值的形式 过渡到结束值
即估值器是整型估值器 - IntEvaluator

特别说明：
因为ValueAnimator本质只是一种值的操作机制，所以下面的介绍先是展示如何改变一个值的过程（下面的实例主要讲解：如何将一个值从0平滑地过渡到3）
至于如何实现动画，是需要开发者手动将这些 值 赋给 对象的属性值。关于这部分在下节会进行说明。

// 步骤1：设置动画属性的初始值 & 结束值
ValueAnimator anim = ValueAnimator.ofInt(0, 3);
        // ofInt()作用有两个
        // 1. 创建动画实例
        // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和1,表示将值从0平滑过渡到1
        // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值 过渡到 结束值
        // 关于自定义插值器我将在下节进行讲解
        // 下面看看ofInt()的源码分析 ->>关注1

// 步骤2：设置动画的播放各种属性
        anim.setDuration(500);
        // 设置动画运行的时长

        anim.setStartDelay(500);
        // 设置动画延迟播放时间

        anim.setRepeatCount(0);
        // 设置动画重复播放次数 = 重放次数+1
        // 动画播放次数 = infinite时,动画无限重复

        anim.setRepeatMode(ValueAnimator.RESTART);
        // 设置重复播放动画模式
        // ValueAnimator.RESTART(默认):正序重放
        // ValueAnimator.REVERSE:倒序回放

// 步骤3：将改变的值手动赋值给对象的属性值：通过动画的更新监听器
        // 设置 值的更新监听器
        // 即：值每次改变、变化一次,该方法就会被调用一次
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int currentValue = (Integer) animation.getAnimatedValue();
                // 获得改变后的值

                System.out.println(currentValue);
                // 输出改变后的值

        // 步骤4：将改变后的值赋给对象的属性值，下面会详细说明
                View.setproperty（currentValue）；

       // 步骤5：刷新视图，即重新绘制，从而实现动画效果
                View.requestLayout();


            }
        });

        anim.start();
        // 启动动画
    }

// 关注1：ofInt()源码分析
    public static ValueAnimator ofInt(int... values) {
        // 允许传入一个或多个Int参数
        // 1. 输入一个的情况（如a）：从0过渡到a；
        // 2. 输入多个的情况（如a，b，c）：先从a平滑过渡到b，再从b平滑过渡到C

        ValueAnimator anim = new ValueAnimator();
        // 创建动画对象
        anim.setIntValues(values);
        // 将传入的值赋值给动画对象
        return anim;
    }
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
 //注意btn1的宽度不能是wrap_content
val anim = ValueAnimator.ofInt(70.dp2px(), 500, 70.dp2px())
        .apply {
            duration = 2000
            addUpdateListener {
                val currentValue = it.animatedValue as Int
                println(currentValue.toString())
                btn1.layoutParams.width = currentValue
                btn1.requestLayout()
            }
        }
val anim1 = ValueAnimator.ofInt(50.dp2px(), 500, 70.dp2px())
        .apply {
            duration = 2000
            addUpdateListener {
                val currentValue = it.animatedValue as Int
                println(currentValue.toString())
                iv1.layoutParams.width = currentValue
                iv1.requestLayout()
            }
        }
b2.click {
    anim.start()
    anim1.start()
}
            """.trimIndent())
        }

        h2.setLeftClick {
            codeDialog.text("""
作用：将初始值 以浮点型数值的形式 过渡到结束值

在 Java 代码中设置
ValueAnimator anim = ValueAnimator.ofFloat(0, 3);
// 其他使用类似ValueAnimator.ofInt（int values），此处不作过多描述

            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
val anim = ValueAnimator.ofFloat(ivPlane.x, srnWidth.toFloat(), ivPlane.x)
        .apply {
            duration = 3000
            addUpdateListener {
                val currentValue = it.animatedValue as Float
                ivPlane.x = currentValue
                ivPlane.requestLayout()
            }
        }
b3.click { anim.start() }
            """.trimIndent())
        }

        h3.setLeftClick {
            codeDialog.text("""
作用：将初始值 以对象的形式 过渡到结束值
即通过操作 对象 实现动画效果

其实ValueAnimator.ofObject()的本质还是操作 ** 值 **，只是是采用将 多个值 封装到一个对象里的方式 同时对多个值一起操作而已
就像上面的例子，本质还是操作坐标中的x，y两个值，只是将其封装到Point对象里，方便同时操作x，y两个值而已

具体使用：

// 创建初始动画时的对象  & 结束动画时的对象
myObject object1 = new myObject();
myObject object2 = new myObject();

ValueAnimator anim = ValueAnimator.ofObject(new myObjectEvaluator(), object1, object2);
// 创建动画对象 & 设置参数
// 参数说明
// 参数1：自定义的估值器对象（TypeEvaluator 类型参数） - 下面会详细介绍
// 参数2：初始动画的对象
// 参数3：结束动画的对象
anim.setDuration(5000);
anim.start();
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
class PlaneEvaluator : TypeEvaluator<Point> {

    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {

        // 将动画初始值startValue 和 动画结束值endValue 强制类型转换成Point对象
        val startPoint = startValue as Point
        val endPoint = endValue as Point

        // 将计算后的坐标封装到一个新的Point对象中并返回
        return Point(
            startPoint.x+(endPoint.x - startPoint.x)*fraction,
            startPoint.y+(endPoint.y - startPoint.y)*fraction
        )
    }

}

val anim = ValueAnimator.ofObject(PlaneEvaluator(), Point(0f, 1400f),
            Point(500f, 1700f))
            .apply {
                duration = 3000
                addUpdateListener {
                    val currentPoint = it.animatedValue as Point
                    ivPlane1.x = currentPoint.x
                    ivPlane1.y = currentPoint.y
                    ivPlane1.requestLayout()
                }
            }
b4.click { anim.start() }
            """.trimIndent())
        }

        b5.click {
            codeDialog.text("""
实现动画的原理
直接对对象的属性值进行改变操作，从而实现动画效果

如直接改变 View的 alpha 属性 从而实现透明度的动画效果
继承自ValueAnimator类，即底层的动画实现机制是基于ValueAnimator类

本质原理： 通过不断控制 值 的变化，再不断 自动 赋给对象的属性，从而实现动画效果。如下图：

ObjectAnimator与 ValueAnimator类的区别：

ValueAnimator 类是先改变值，然后 手动赋值 给对象的属性从而实现动画；是 间接 对对象属性进行操作；
ObjectAnimator 类是先改变值，然后 自动赋值 给对象的属性从而实现动画；是 直接 对对象属性进行操作；
            """.trimIndent())
        }

        case1()
        case2()
        case3()
        case4()
        case5()
        case6()
    }

    private fun case6() {
        tvQuestion1.text = """
问题：上面的例子是给自己写的类添加set和get方法，但如果是系统的或是其他无法直接修改的类，应该如何添加？

方案1：通过继承原始类，直接给类加上该属性的 get()&set()，从而实现给对象加上该属性的 get()&set()。

方案2：通过包装原始动画对象，间接给对象加上该属性的 get()&set()，即用一个类来包装原始对象（接下来就讲这个方案）。
        """.trimIndent()

        headerLast.setRightClick {
            codeDialog.text("""
// 提供ViewWrapper类,用于包装View对象
// 本例:包装Button对象
public class ViewWrapper {

    private View mTarget;

    // 构造方法:传入需要包装的对象
    public ViewWrapper(View target) {
        mTarget = target;
    }

    // 为宽度设置get（） & set（）
    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }

}

val wrapper = ViewWrapper(btnWidth)

bLast.click {
    ObjectAnimator.ofInt(wrapper, "width", 80.dp2px(), 800, 80.dp2px()).setDuration(3000).start()
}
            """.trimIndent())
        }

        val wrapper = ViewWrapper(btnWidth)
        bLast.click {
            ObjectAnimator.ofInt(wrapper, "width", 80.dp2px(), 800, 80.dp2px()).setDuration(3000).start()
        }

    }

    private fun case5() {
        myView.color = "#ffff33"
        myView1.color = "#ff3366"
        myView2.color = "#33ff66"
        myView3.color = "#00ccff"
        myView4.color = "#336666"
        myView5.color = "#FFA500"
        val anim1 = ObjectAnimator.ofObject(myView, "color", ColorEvaluator(),
                "#ffff33", "#FFA500").apply { duration=8000 }
        val anim2 = ObjectAnimator.ofObject(myView1, "color", ColorEvaluator(),
                "#ff3366", "#336666").apply { duration=8000 }
        val anim3 = ObjectAnimator.ofObject(myView2, "color", ColorEvaluator(),
                "#33ff66", "#00ccff").apply { duration=8000 }
        val anim4 = ObjectAnimator.ofObject(myView3, "color", ColorEvaluator(),
                "#00ccff", "#33ff66").apply { duration=8000 }
        val anim5 = ObjectAnimator.ofObject(myView4, "color", ColorEvaluator(),
                "#336666", "#ff3366").apply { duration=8000 }
        val anim6 = ObjectAnimator.ofObject(myView5, "color", ColorEvaluator(),
                "#FFA500", "#ffff33").apply { duration=8000 }
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set()设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw()重新绘制，从而实现动画效果
        b10.click {
            anim1.start()
            anim2.start()
            anim3.start()
            anim4.start()
            anim5.start()
            anim6.start()
        }
    }

    private fun case4() {
        val animator = ObjectAnimator.ofFloat(girl, "alpha", 1f, 0f, 1f)
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是透明度alpha
        // 动画效果是:常规 - 全透明 - 常规
        animator.duration = 5000
        b6.click { animator.start() }

        val curTranslationX = girl.translationX
        // 获得当前按钮的位置
        val animator1 = ObjectAnimator.ofFloat(girl, "translationX", curTranslationX, 300f, curTranslationX)
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        animator1.duration = 5000
        b7.click {
            animator1.start()
        }

        val animator2 = ObjectAnimator.ofFloat(girl, "scaleX", 1f, 3f, 1f)
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴缩放
        // 动画效果是:放大到3倍,再缩小到初始大小
        animator2.duration = 5000
        b8.click {
            animator2.start()
        }

        val animator3 = ObjectAnimator.ofFloat(girl, "rotation", 0f, 360f)
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是旋转alpha
        // 动画效果是:0 - 360
        animator3.duration = 5000
        b9.click {
            animator3.start()
        }
    }

    private fun case3() {
        val anim = ValueAnimator.ofObject(PlaneEvaluator(), Point(0f, 1400f),
                Point(500f, 1700f))
                .apply {
                    duration = 3000
                    addUpdateListener {
                        val currentPoint = it.animatedValue as Point
                        ivPlane1.x = currentPoint.x
                        ivPlane1.y = currentPoint.y
                        ivPlane1.requestLayout()
                    }
                }
        b4.click { anim.start() }
    }

    private fun case2() {
        val anim = ValueAnimator.ofFloat(ivPlane.x, srnWidth.toFloat(), ivPlane.x)
                .apply {
                    duration = 3000
                    addUpdateListener {
                        val currentValue = it.animatedValue as Float
                        ivPlane.x = currentValue
                        ivPlane.requestLayout()
                    }
                }
        b3.click { anim.start() }
    }

    private fun case1() {
        //注意btn1的宽度不能是wrap_content
        val anim = ValueAnimator.ofInt(70.dp2px(), 500, 70.dp2px())
                .apply {
                    duration = 2000
                    addUpdateListener {
                        val currentValue = it.animatedValue as Int
                        println(currentValue.toString())
                        btn1.layoutParams.width = currentValue
                        btn1.requestLayout()
                    }
                }
        val anim1 = ValueAnimator.ofInt(50.dp2px(), 500, 70.dp2px())
                .apply {
                    duration = 2000
                    addUpdateListener {
                        val currentValue = it.animatedValue as Int
                        println(currentValue.toString())
                        iv1.layoutParams.width = currentValue
                        iv1.requestLayout()
                    }
                }
        b2.click {
            anim.start()
            anim1.start()
        }
    }

}
