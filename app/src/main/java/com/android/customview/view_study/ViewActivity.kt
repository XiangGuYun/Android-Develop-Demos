package com.android.customview.view_study

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_attr_read.*

@LayoutId(R.layout.activity_attr_read)
class ViewActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        tvMotionEvent.setSimpleClickText {
            codeDialog.text("""
但其中有两个比较特殊的事件: ACTION_CANCEL 和 ACTION_OUTSIDE 。
为什么说特殊呢，因为它们是由程序触发而产生的，而且触发条件也非常特殊，通常情况下即便不处理这两个事件也没有什么问题。接下来我们就扒一扒它们的真面目:

ACTION_CANCEL
ACTION_CANCEL 的触发条件是事件被上层拦截，然而我们在 事件分发机制原理 一文中了解到当事件被上层 View 拦截的时候，ChildView 是收不到任何事件的，ChildView 收不到任何事件，自然也不会收到 ACTION_CANCEL 了，所以说这个 ACTION_CANCEL 的正确触发条件并不是这样，那么是什么呢？
事实上，只有上层 View 回收事件处理权的时候，ChildView 才会收到一个 ACTION_CANCEL 事件。
这样说可能不太容易理解，咱举个例子?
例如：上层 View 是一个 RecyclerView，它收到了一个 ACTION_DOWN 事件，由于这个可能是点击事件，所以它先传递给对应 ItemView，询问 ItemView 是否需要这个事件，然而接下来又传递过来了一个 ACTION_MOVE 事件，且移动的方向和 RecyclerView 的可滑动方向一致，所以 RecyclerView 判断这个事件是滚动事件，于是要收回事件处理权，这时候对应的 ItemView 会收到一个 ACTION_CANCEL ，并且不会再收到后续事件。
通俗一点？
RecyclerView：儿砸，这里有一个 ACTION_DOWN 你看你要不要。
ItemView ：好嘞，我看看。
RecyclerView：噫？居然是移动事件ACTION_MOVE，我要滚起来了，儿砸，我可能要把你送去你姑父家(缓存区)了，在这之前给你一个 ACTION_CANCEL，你要收好啊。
ItemView ：……
这是实际开发中最有可能见到 ACTION_CANCEL 的场景了。

ACTION_OUTSIDE
ACTION_OUTSIDE的触发条件更加奇葩，从字面上看，outside 意思不就是超出区域么？然而不论你如何滑动超出控件区域都不会触发 ACTION_OUTSIDE这个事件。相信很多魔法师都对此很是疑惑，说好的超出区域呢？
实际上这个事件根本就不是在这里用的，看官方解释(装一下逼)：
 A movement has happened outside of the normal bounds of the UI element. This does not provide a full gesture, but only the initial location of the movement/touch.
一个触摸事件已经发生了UI元素的正常范围之外。因此不再提供完整的手势，只提供 运动/触摸 的初始位置。
我们知道，正常情况下，如果初始点击位置在该视图区域之外，该视图根本不可能会收到事件，然而，万事万物都不是绝对的，肯定还有一些特殊情况，你可曾还记得点击 Dialog 区域外关闭吗？Dialog 就是一个特殊的视图(没有占满屏幕大小的窗口)，能够接收到视图区域外的事件(虽然在通常情况下你根本用不到这个事件)，除了 Dialog 之外，你最可能看到这个事件的场景是悬浮窗，当然啦，想要接收到视图之外的事件需要一些特殊的设置。
设置视图的 WindowManager 布局参数的 flags为FLAG_WATCH_OUTSIDE_TOUCH，这样点击事件发生在这个视图之外时，该视图就可以接收到一个 ACTION_OUTSIDE 事件。
参见StackOverflow：How to dismiss the dialog with click on outside of the dialog?
由于这个事件用到的几率比较小，此处就不展开叙述了，以后用到的时候再详细讲解。
            """.trimIndent())
        }

        header2.setLeftClick {
            codeDialog.text("""
1.我们在自定义View时，当需要刷新View时，如果是在UI线程中，那就直接调用invalidate方法，如果是在非UI线程中，那就通过postInvalidate方法来刷新View

2.postInvalidate方法实现了消息机制，最终调用的也是invalidate方法来刷新View

3.invalidate方法最终调用的是ViewRootImpl中的performTraversals来重新绘制View
            """.trimIndent())
        }.setRightClick { codeDialog.text("""
override fun draw(canvas: Canvas?) {
    canvas?.drawColor("#${getRandColorCode()}".color())
    super.draw(canvas)
    Thread{
        Thread.sleep(1000)
        postInvalidate()
    }.start()
}
        """.trimIndent()) }



    }
}
