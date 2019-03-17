package com.android.fragment.fragmentation.case5

import android.os.Bundle
import android.os.Message
import com.android.R
import com.android.fragment.fragmentation.case1.FragmentationCase1Fragment
import com.android.fragment.fragmentation.case2.FragmentationCase2Fragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragmentation_case5.*
import me.yokeyword.fragmentation.SupportHelper
import org.greenrobot.eventbus.Subscribe

@LayoutId(R.layout.activity_fragmentation_case5)
class FragmentationCase5Activity : KotlinActivity() {

    @Subscribe
    fun handleEvent(msg:Message){
        if(msg.what==0x112233){
            tvInfo.text = msg.obj.toString()
        }else if(msg.what==0x112234){
            tvInfo1.text = msg.obj.toString()
        }
    }

    override fun init(bundle: Bundle?) {
        startEventBus = true
        val frag1 = FragmentationCase1Fragment.newInstance()
        val frag2 = FragmentationCase2Fragment.newInstance()

        loadMultipleRootFragment(R.id.fl_container, 0, frag1, frag2)

        btnViewStack.click {
            // 查看栈视图Dialog
            SupportHelper.showFragmentStackHierarchyView(this)
            // 打印查看栈视图Log
            SupportHelper.logFragmentStackHierarchy(this, "STACK_LOG")
        }

        rg_case3.check {
            when(it){
                R.id.rb1->{
                    showHideFragment(frag1, frag2)
                }
                R.id.rb2->{
                    showHideFragment(frag2, frag1)
                }
            }
        }
    }

}
