package pl.jergro.shopinglist.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
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
    }
}