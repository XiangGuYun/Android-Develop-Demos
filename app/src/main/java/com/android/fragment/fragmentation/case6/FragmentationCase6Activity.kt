package com.android.fragment.fragmentation.case6

import android.os.Bundle
import com.android.R
import com.android.fragment.fragmentation.case1.FragmentationCase1Fragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragmentation_case6.*
import me.yokeyword.fragmentation.SupportHelper
import me.yokeyword.fragmentation.anim.FragmentAnimator

@LayoutId(R.layout.activity_fragmentation_case6)
class FragmentationCase6Activity : KotlinActivity() {


    override fun onCreateFragmentAnimator(): FragmentAnimator {
        //super.onCreateFragmentAnimator();
        //FragmentAnimator(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        return FragmentAnimator(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

    override fun init(bundle: Bundle?) {
        val frag1 = FragmentationCase1Fragment.newInstance()
        val frag2 =  when(extraInt("type" to 1)){
            1->LazyFragment.newInstance()
            2->NoLazyFragment.newInstance()
            else->AnimEndLoadFragment.newInstance()
        }

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

        btnViewCode.click {
            codeDialog.text("""
@LayoutId(R.layout.fragment_lazy)
class LazyFragment:KotlinFragment() {

    override fun init() {
        tvTest.text = "TEXT1"
    }

    companion object {
        fun newInstance(): LazyFragment {
            return LazyFragment()
        }
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        Thread{
            Thread.sleep(3000)
            getAct().runOnUiThread {
                tvTest.setTextColor(Color.parseColor("#ff6666"))
                tvTest.text = "TEXT2"
            }
        }.start()
    }

}

@LayoutId(R.layout.fragment_lazy)
class NoLazyFragment:KotlinFragment() {

    override fun init() {
        tvTest.text = "TEXT1"
        Thread{
            Thread.sleep(3000)
            getAct().runOnUiThread {
                tvTest.setTextColor(Color.parseColor("#ff6666"))
                tvTest.text = "TEXT2"
            }
        }.start()
    }

    companion object {
        fun newInstance(): NoLazyFragment {
            return NoLazyFragment()
        }
    }

}

@LayoutId(R.layout.fragment_lazy)
class AnimEndLoadFragment:KotlinFragment() {

    override fun init() {
        tvTest.text = "TEXT1"
    }

    companion object {
        fun newInstance(): AnimEndLoadFragment {
            return AnimEndLoadFragment()
        }
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)
        Thread{
            Thread.sleep(3000)
            getAct().runOnUiThread {
                tvTest.setTextColor(Color.parseColor("#ff6666"))
                tvTest.text = "TEXT2"
            }
        }.start()
    }

}
            """.trimIndent())
        }
    }

}
