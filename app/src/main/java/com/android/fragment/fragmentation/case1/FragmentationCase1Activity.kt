package com.android.fragment.fragmentation.case1

import android.os.Bundle
import com.android.R
import me.yokeyword.fragmentation.SupportActivity

class FragmentationCase1Activity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentation_case1)

        if (findFragment(FragmentationCase1Fragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, FragmentationCase1Fragment.newInstance())  // 加载根Fragment
        }

    }
}
