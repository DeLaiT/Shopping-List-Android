package pl.jergro.shopinglist.ui.themes

import androidx.annotation.ColorLong
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes

class ThemeModel(
    @StyleRes val themeLight: Int,
    @StyleRes val themeDark: Int,
    @ColorRes val themeColor: Int,
    val themeName: String
)