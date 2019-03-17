package com.android.fragment.fragmentation.case7

import android.os.Bundle
import com.android.R
import com.android.fragment.fragmentation.case1.FragmentationCase1Fragment
import com.android.fragment.fragmentation.case4.NewFragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragmentation_case7.*
import me.yokeyword.fragmentation.SupportHelper
import me.yokeyword.fragmentation.anim.FragmentAnimator

@LayoutId(R.layout.activity_fragmentation_case7)
class FragmentationCase7Activity : KotlinActivity() {

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return FragmentAnimator(android.R.anim.fade_in, android.R.anim.fade_in)
    }

    override fun init(bundle: Bundle?) {

        if (findFragment(FragmentationCase1Fragment::class.java) == null) {
            // 装载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
            //loadRootFragment(R.id.fl_container, FragmentationCase2Fragment.newInstance())
            loadRootFragment(R.id.fl_container, FragmentationCase1Fragment.newInstance(), true, false)  // 加载根Fragment
        }

        btnViewCode.click {
            codeDialog.text("""
if (findFragment(FragmentationCase2Fragment::class.java) == null) {
    //loadRootFragment(R.id.fl_container, FragmentationCase2Fragment.newInstance())
    loadRootFragment(R.id.fl_container, FragmentationCase2Fragment.newInstance(), true, false)
}
            """.trimIndent())
        }

        btnViewStack.click {
            // 查看栈视图Dialog
            SupportHelper.showFragmentStackHierarchyView(this)
            // 打印查看栈视图Log
            SupportHelper.logFragmentStackHierarchy(this, "STACK_LOG")
//            // 根据class/tag寻找Fragment
//            SupportHelper.findFragment(fragmentManager, tag/class)
        }

        btnLaunch.click {
            start(NewFragment.newInstance())
        }

        btnSetting.click {
            start(NewAnimFragment.newInstance())
        }

    }

}
