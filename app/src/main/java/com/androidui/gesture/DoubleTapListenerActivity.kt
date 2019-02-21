package com.androidui.gesture

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ScrollView
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_double_tap_listener.*

@LayoutId(R.layout.activity_double_tap_listener)
class DoubleTapListenerActivity : KotlinActivity() {
    private lateinit var listener:MyGestureListener
    private lateinit var detector: GestureDetector
    val log = StringBuilder()


    class MyGestureListener: GestureDetector.SimpleOnGestureListener()

    override fun init(bundle: Bundle?) {
        //实例化GestureListener与GestureDetector对象
        listener = MyGestureListener()
        detector = GestureDetector(this, listener)

        detector.setOnDoubleTapListener(object :GestureDetector.OnDoubleTapListener{
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                log.append("onDoubleTap\n")
                tvLog.text = log.toString()
                scrollText.fullScroll(ScrollView.FOCUS_DOWN)
                return false
            }

            override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
                log.append("onDoubleTapEvent\n")
                tvLog.text = log.toString()
                scrollText.fullScroll(ScrollView.FOCUS_DOWN)
                return false
            }

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                log.append("onSingleTapConfirmed\n")
                tvLog.text = log.toString()
                scrollText.fullScroll(ScrollView.FOCUS_DOWN)
                return false
            }
        })

        ivGirl.setOnTouchListener { v, event ->
            detector.onTouchEvent(event)
            true
        }

        btnClearLog.click {
            tvLog.text=""
            log.setLength(0)
        }

        case1()
    }



    private fun case1() {
        header1.setLeftClick {
            codeDialog.text("""
OnDoubleTapListener，这个Listener监听双击和单击事件。
- onSingleTapConfirmed(MotionEvent e)：可以确认（通过单击DOWN后300ms没有下一个DOWN事件确认）这不是一个双击事件，而是一个单击事件的时候会回调。
- onDoubleTap(MotionEvent e)：可以确认这是一个双击事件的时候回调。
- onDoubleTapEvent(MotionEvent e)：onDoubleTap()回调之后的输入事件（DOWN、MOVE、UP）都会回调这个方法（这个方法可以实现一些双击后的控制，如让View双击后变得可拖动等）

 /**
 * The listener that is used to notify when a double-tap or a confirmed
 * single-tap occur.
 */
public interface OnDoubleTapListener {
    /**
     * Notified when a single-tap occurs.
     * <p>
     * Unlike {@link OnGestureListener#onSingleTapUp(MotionEvent)}, this
     * will only be called after the detector is confident that the user's
     * first tap is not followed by a second tap leading to a double-tap
     * gesture.
     *
     * @param e The down motion event of the single-tap.
     * @return true if the event is consumed, else false
     */
    boolean onSingleTapConfirmed(MotionEvent e);

    /**
     * Notified when a double-tap occurs.
     *
     * @param e The down motion event of the first tap of the double-tap.
     * @return true if the event is consumed, else false
     */
    boolean onDoubleTap(MotionEvent e);

    /**
     * Notified when an event within a double-tap gesture occurs, including
     * the down, move, and up events.
     *
     * @param e The motion event that occurred during the double-tap gesture.
     * @return true if the event is consumed, else false
     */
    boolean onDoubleTapEvent(MotionEvent e);
}
            """.trimIndent())
        }
    }
}
