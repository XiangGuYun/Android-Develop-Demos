package com.android.gesture

import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ScrollView
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_gesture_listener.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@LayoutId(R.layout.activity_gesture_listener)
class GestureListenerActivity : KotlinActivity() {

    private lateinit var listener:MyGestureListener
    private lateinit var detector:GestureDetector

    @Subscribe
    fun handleEvent(msg:Message){
        when(msg.what){
            0x111->{
                tvLog.text = msg.obj.toString()
                scrollText.fullScroll(ScrollView.FOCUS_DOWN)
            }
        }
    }

    class MyGestureListener: GestureDetector.SimpleOnGestureListener(){

        companion object {
            val TAG = "Test"
            val log = StringBuilder()
        }

        override fun onShowPress(e: MotionEvent?) {
            Log.d(TAG, "onShowPress:手指按下一段时间,还没到长按")
            log.append("onShowPress:手指按下一段时间," +
                    "还没到长按\n")
            EventBus.getDefault().post(Message.obtain().apply {
                what = 0x111
                obj = log.toString()
            })
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Log.d(TAG, "onSingleTapUp:手指离开屏幕的一瞬间")
            log.append("onSingleTapUp:手指离开屏幕的一瞬间\n")
            EventBus.getDefault().post(Message.obtain().apply {
                what = 0x111
                obj = log.toString()
            })
            return false
        }

        override fun onDown(e: MotionEvent?): Boolean {
            Log.d(TAG, "onDown:按下")
            log.append("onDown:按下\n")
            EventBus.getDefault().post(Message.obtain().apply {
                what = 0x111
                obj = log.toString()
            })
            return false
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            Log.d(TAG, "onFling:迅速滑动，并松开")
            log.append("onFling:迅速滑动，并松开\n")
            EventBus.getDefault().post(Message.obtain().apply {
                what = 0x111
                obj = log.toString()
            })
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            Log.d(TAG, "onScroll:在触摸屏上滑动")
            log.append("onScroll:在触摸屏上滑动\n")
            EventBus.getDefault().post(Message.obtain().apply {
                what = 0x111
                obj = log.toString()
            })
            return false
        }

        override fun onLongPress(e: MotionEvent?) {
            Log.d(TAG, "onLongPress:长按并且没有松开")
            log.append("onLongPress:长按并且没有松开\n")
            EventBus.getDefault().post(Message.obtain().apply {
                what = 0x111
                obj = log.toString()
            })
        }

    }

    override fun init(bundle: Bundle?) {
        startEventBus = true
        //实例化GestureListener与GestureDetector对象
        listener = MyGestureListener()
        detector = GestureDetector(this, listener)
        btnClearLog.click {
            tvLog.text=""
            MyGestureListener.log.setLength(0)
        }

        case1()
    }



    private fun case1() {
        header1.setLeftClick {
            codeDialog.text("""
OnGestureListener，这个Listener监听一些手势，如单击、滑动、长按等操作：

按下（onDown）： 刚刚手指接触到触摸屏的那一刹那，就是触的那一下。
抛掷（onFling）： 手指在触摸屏上迅速移动，并松开的动作。
长按（onLongPress）： 手指按在持续一段时间，并且没有松开。
滚动（onScroll）： 手指在触摸屏上滑动。
按住（onShowPress）： 手指按在触摸屏上，它的时间范围在按下起效，在长按之前。
抬起（onSingleTapUp）：手指离开触摸屏的那一刹那。

知道了GestureListener的相关方法后，实现手势检测也很简单，步骤如下：

Step 1: 创建GestureDetector对象，创建时需实现GestureListener传入
Step 2: 将Activity或者特定组件上的TouchEvent的事件交给GestureDetector处理即可！ 我们写个简单的代码来验证这个流程，即重写对应的方法：

public class MainActivity extends AppCompatActivity {

    private MyGestureListener mgListener;
    private GestureDetector mDetector;
    private final static String TAG = "MyGesture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化GestureListener与GestureDetector对象
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    //自定义一个GestureListener,这个是View类下的，别写错哦！！！
    private class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            Log.d(TAG, "onDown:按下");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
            Log.d(TAG, "onShowPress:手指按下一段时间,不过还没到长按");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Log.d(TAG, "onSingleTapUp:手指离开屏幕的一瞬间");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.d(TAG, "onScroll:在触摸屏上滑动");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            Log.d(TAG, "onLongPress:长按并且没有松开");
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.d(TAG, "onFling:迅速滑动，并松开");
            return false;
        }
    }

}

            """.trimIndent())
        }
    }
}
