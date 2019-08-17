package pl.jergro.shopinglist.ui.binding_adapters

import android.content.Context
import android.content.res.ColorStateList
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import pl.jergro.shopinglist.R

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

@BindingAdapter("productTitleByDone")
fun TextView.setProductTitleColorByDone(done: Boolean) {
    val c1 = getColorByAttr(context, R.attr.titleColor)
    val c2 = getColorByAttr(context, R.attr.textDisabledColor)

    setTextColor(if (done) c2 else c1)
}

@BindingAdapter("productPriceByDone")
fun TextView.setProductPriceColorByDone(done: Boolean) {
    val c1 = getColorByAttr(context, R.attr.textActiveColor)
    val c2 = getColorByAttr(context, R.attr.textDisabledColor)

    setTextColor(if (done) c2 else c1)
}

@BindingAdapter("productPriceIconTintByDone")
fun ImageView.setPriceiconTintByDone(done: Boolean) {
    val c1 = getColorByAttr(context, R.attr.textActiveColor)
    val c2 = getColorByAttr(context, R.attr.textDisabledColor)

    imageTintList = ColorStateList.valueOf(if (done) c2 else c1)
}

@BindingAdapter("productDoneCheckTint")
fun ImageView.setProductDoneCheckTint(done: Boolean) {
    val c1 = getColorByAttr(context, R.attr.accentColor)
    val c2 = getColorByAttr(context, R.attr.textDisabledColor)

    imageTintList = ColorStateList.valueOf(if (done) c1 else c2)
}

fun getColorByAttr(context: Context, @AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    val theme = context.theme
    theme.resolveAttribute(attr, typedValue, true)

    return typedValue.data
}