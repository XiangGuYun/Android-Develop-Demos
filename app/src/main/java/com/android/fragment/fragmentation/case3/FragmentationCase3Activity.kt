package com.android.fragment.fragmentation.case3

import android.os.Bundle
import com.android.R
import com.android.fragment.fragmentation.case1.FragmentationCase1Fragment
import com.android.fragment.fragmentation.case2.FragmentationCase2Fragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragmentation_case3.*
import me.yokeyword.fragmentation.SupportHelper

@LayoutId(R.layout.activity_fragmentation_case3)
class FragmentationCase3Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        val frag1 = FragmentationCase1Fragment.newInstance()
        val frag2 = FragmentationCase2Fragment.newInstance()

        loadMultipleRootFragment(R.id.fl_container, 0, frag1, frag2)

        btnViewCode.click {
            codeDialog.text("""
val frag1 = FragmentationCase1Fragment.newInstance()
val frag2 = FragmentationCase2Fragment.newInstance()

loadMultipleRootFragment(R.id.fl_container, 0, frag1, frag2)

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
            """.trimIndent())
        }

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
