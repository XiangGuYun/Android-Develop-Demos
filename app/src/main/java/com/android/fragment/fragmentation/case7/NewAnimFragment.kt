package com.android.fragment.fragmentation.case7

import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_fragmentation_case1.*
import me.yokeyword.fragmentation.anim.FragmentAnimator
import kotlin.random.Random

@LayoutId(R.layout.fragment_fragmentation_case1)
class NewAnimFragment:KotlinFragment() {
    override fun init() {
        tvFragment.text = "我是一个新的Fragment(Tag:${Random.nextInt(0,100)})"
        fragmentAnimator = FragmentAnimator(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    companion object {
        fun newInstance(): NewAnimFragment {
            return NewAnimFragment()
        }
    }

}