package pl.jergro.shopinglist.ui.binding_adp

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("imageRes")
fun ImageView.setImageByResource(icon: Int?) {
    icon?.let { setImageResource(it) }
}

@BindingAdapter("imageColorRes")
fun ImageView.setColorByResource(color: Int?) {
    color?.let { setColorFilter(ContextCompat.getColor(context, it)) }
}

@BindingAdapter("textColorRes")
fun TextView.setTextColorByResource(color: Int?) {
    color?.let { setTextColor(ContextCompat.getColor(context, it)) }
}