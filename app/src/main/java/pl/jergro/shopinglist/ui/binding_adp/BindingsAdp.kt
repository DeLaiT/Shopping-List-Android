package pl.jergro.shopinglist.ui.binding_adp

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

class BindingsAdp {

    companion object {

        @BindingAdapter("icon")
        @JvmStatic
        fun toIcon(view: ImageView, icon: Int?) {
            icon?.let { view.setImageResource(it) }
        }

        @BindingAdapter("color")
        @JvmStatic
        fun toColor(view: ImageView, color: Int?) {
            color?.let { view.setColorFilter(ContextCompat.getColor(view.context, it)) }
        }

        @BindingAdapter("texto")
        @JvmStatic
        fun toTextColor(view: TextView, color: Int?) {
            color?.let { view.setTextColor(ContextCompat.getColor(view.context, it)) }
        }
    }

}