package com.androidui.fragment.case2

import android.os.Message
import com.androidui.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_case2_detail.*
import org.greenrobot.eventbus.Subscribe

@LayoutId(R.layout.fragment_case2_detail)
class Case2DetailFragment: KotlinFragment(){
    override fun init() {
        bus.register(this)
    }

    @Subscribe
    fun handleEvent(msg:Message){
        if(msg.what==0x111){
            tvBookName.text = msg.obj.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bus.unregister(this)
    }

}
