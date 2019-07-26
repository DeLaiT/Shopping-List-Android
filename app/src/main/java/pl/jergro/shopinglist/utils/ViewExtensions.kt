package pl.jergro.shopinglist.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

fun View.elevateOnScroll(recyclerView: RecyclerView) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val context = recyclerView.context
            val threshold = 16.dp(context).toFloat()
            val maxElevation = 3.dp(context).toFloat()
            val y = recyclerView.computeVerticalScrollOffset()

            elevation = min(y / threshold * maxElevation, maxElevation)
        }
    })
}