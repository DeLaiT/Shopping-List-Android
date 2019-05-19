package pl.jergro.shopinglist.utils

import android.content.Context
import kotlin.math.roundToInt

fun Int.dp(context: Context): Int {
    return (this * context.resources.displayMetrics.density).roundToInt()
}