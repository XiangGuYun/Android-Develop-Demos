package com.android.fragment.fragmentation.case6

import android.graphics.Color
import android.os.Bundle
import com.android.R
import com.android.fragment.fragmentation.case1.FragmentationCase1Fragment
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_lazy.*

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