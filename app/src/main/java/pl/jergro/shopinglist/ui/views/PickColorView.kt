package pl.jergro.shopinglist.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorInt
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.utils.dp

@SuppressLint("ViewConstructor")
class PickColorView(@ColorInt private val color: Int, context: Context) : ImageView(context){
    private var checked = false

    init {
        val size = 48.dp(context)
        layoutParams = ViewGroup.MarginLayoutParams(size, size)
        (layoutParams as ViewGroup.MarginLayoutParams).apply {
            marginStart = 4.dp(context)
            marginEnd = 4.dp(context)
        }

        setBackgroundResource(R.drawable.circle_outline)
        backgroundTintList = ColorStateList.valueOf(color)
        backgroundTintMode = PorterDuff.Mode.MULTIPLY
        scaleType = ScaleType.CENTER_INSIDE
    }

    fun setChecked(checked: Boolean) {
        this.checked = checked

        if(checked){
            setImageResource(R.drawable.ic_round_check_24px)
        } else {
            setImageDrawable(null)
        }
    }
}