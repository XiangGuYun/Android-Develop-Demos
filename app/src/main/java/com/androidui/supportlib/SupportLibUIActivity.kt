package com.androidui.supportlib

import android.os.Bundle
import com.androidui.R
import com.androidui.supportlib.bottom_sheet_dialog.BottomSheetDialogActivity
import com.androidui.supportlib.cardview.CardViewActivity
import com.androidui.supportlib.collapsing_toolbar_layout.CTLActivity
import com.androidui.supportlib.constraint.ConstraintActivity
import com.androidui.supportlib.coordinate.CoordinateActivity
import com.androidui.supportlib.drawerlayout.DrawerLayoutActivity
import com.androidui.supportlib.fab.FABActivity
import com.androidui.supportlib.nestedsv.NestedScrollViewActivity
import com.androidui.supportlib.recyclerview.RecyclerViewActivity
import com.androidui.supportlib.sliding_pane_layout.SlidingPaneLayoutActivity
import com.androidui.supportlib.snackbar.SnackBarActivity
import com.androidui.supportlib.swipe_refresh_layout.SwipeRefreshLayoutActivity
import com.androidui.supportlib.textinput.TextInputActivity
import com.androidui.supportlib.toolbar.ToolbarActivity
import com.androidui.supportlib.viewpager.ViewPagerActivity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_support_lib_ui.*

@LayoutId(R.layout.activity_support_lib_ui)
class SupportLibUIActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnRecyclerView.click { go(RecyclerViewActivity::class.java) }
        btnViewPager.click { go(ViewPagerActivity::class.java) }
        btnCardView.click { go(CardViewActivity::class.java) }
        btnToolbar.click { go(ToolbarActivity::class.java) }
        btnCLAPL.click { go(CoordinateActivity::class.java) }
        btnFloatingActionButton.click { go(FABActivity::class.java) }
        btnTextInputLayout.click { go(TextInputActivity::class.java) }
        btnSnackbar.click { go(SnackBarActivity::class.java) }
        btnCollapsingToolbarLayout.click { go(CTLActivity::class.java) }
        btnNestedScrollView.click { go(NestedScrollViewActivity::class.java) }
        btnDrawerLayout.click { go(DrawerLayoutActivity::class.java) }
        btnSwipeRefreshLayout.click { go(SwipeRefreshLayoutActivity::class.java) }
        btnSlidingPaneLayout.click { go(SlidingPaneLayoutActivity::class.java) }
        btnConstraintLayout.click { go(ConstraintActivity::class.java) }
        btnBottomSheetDialog.click { go(BottomSheetDialogActivity::class.java) }
    }
}
