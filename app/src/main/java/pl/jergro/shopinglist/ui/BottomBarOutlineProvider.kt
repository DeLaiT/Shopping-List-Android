package pl.jergro.shopinglist.ui

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import pl.jergro.shopinglist.utils.dp

class BottomBarOutlineProvider : ViewOutlineProvider() {
    override fun getOutline(view: View, outline: Outline) {
        val context = view.context
        outline.setRect(0, (-3).dp(context), view.width, view.height)
    }
}