package com.android.fragment.case1

import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_test1.*

@LayoutId(R.layout.fragment_test1)
class TestFragment:KotlinFragment(){

    override fun init() {
        btnNext.click {
            (getAct() as TestActivity).fu.replace(SecondFragment(),true){
                it.setCustomAnimations(R.anim.my_fade_in, R.anim.my_down_out)
            }
        }
    }




}
