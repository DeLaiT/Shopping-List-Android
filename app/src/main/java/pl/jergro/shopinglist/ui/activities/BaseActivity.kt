package pl.jergro.shopinglist.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.ui.themes.ThemesManager


@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {
    private var currentTheme: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        updateTheme()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val theme = ThemesManager.getCurrentThemeRes(this)
        if (currentTheme != theme) {
            recreate()
        }
    }

    private fun updateTheme() {
        currentTheme = ThemesManager.getCurrentThemeRes(this)

        setTheme(currentTheme)
        updateStatusBar()
    }

    private fun updateStatusBar() {
        if (ThemesManager.isDarkTheme(this))
            setDarkStatusBar()
        else
            setLightStatusBar()

        window.statusBarColor = getColorFromAttribute(R.attr.bgColor)
    }

    private fun setLightStatusBar() {
        var flags = window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        window.decorView.systemUiVisibility = flags
    }

    private fun setDarkStatusBar() {
        var flags = window.decorView.systemUiVisibility

        if (flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR != 0)
            flags -= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        window.decorView.systemUiVisibility = flags
    }

    @ColorInt
    private fun getColorFromAttribute(@AttrRes attrRes: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(attrRes, typedValue, true)

        return typedValue.data
    }
}