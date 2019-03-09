package com.android.supportlib

import android.os.Bundle
import com.android.R
import com.android.supportlib.bottom_sheet_dialog.BottomSheetDialogActivity
import com.android.supportlib.cardview.CardViewActivity
import com.android.supportlib.collapsing_toolbar_layout.CTLActivity
import com.android.supportlib.constraint.ConstraintActivity
import com.android.supportlib.coordinate.CoordinateActivity
import com.android.supportlib.drawerlayout.DrawerLayoutActivity
import com.android.supportlib.fab.FABActivity
import com.android.supportlib.nestedsv.NestedScrollViewActivity
import com.android.supportlib.recyclerview.RecyclerViewActivity
import com.android.supportlib.sliding_pane_layout.SlidingPaneLayoutActivity
import com.android.supportlib.snackbar.SnackBarActivity
import com.android.supportlib.swipe_refresh_layout.SwipeRefreshLayoutActivity
import com.android.supportlib.textinput.TextInputActivity
import com.android.supportlib.toolbar.ToolbarActivity
import com.android.supportlib.viewpager.ViewPagerActivity
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
