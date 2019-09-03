package pl.jergro.shopinglist.ui.themes

import android.content.Context
import androidx.annotation.StyleRes
import pl.jergro.shopinglist.R

object ThemesManager {
    private val themes by lazy {
        hashMapOf(
            DEFAULT_THEME to ThemeModel(R.style.AppTheme, R.style.DarkAppTheme, R.color.md_indigo_A400),
            "pink" to ThemeModel(R.style.AppTheme, R.style.AppTheme, R.color.md_pink_A400),
            "green" to ThemeModel(R.style.AppTheme, R.style.AppTheme, R.color.md_green_A400),
            "yellow" to ThemeModel(R.style.AppTheme, R.style.AppTheme, R.color.md_amber_A400),
            "orange" to ThemeModel(R.style.AppTheme, R.style.AppTheme, R.color.md_deep_orange_A400)
        )
    }

    private const val PREFS_NAME = "Themes"
    private const val KEY_DARK = "dark"
    private const val KEY_NAME = "name"
    private const val DEFAULT_THEME = "blue"

    @StyleRes
    fun getCurrentThemeRes(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val selectedThemeName = prefs.getString(
            KEY_NAME,
            DEFAULT_THEME
        )!!
        val isDarkTheme = prefs.getBoolean(KEY_DARK, false)

        val theme = themes[selectedThemeName] ?: themes[DEFAULT_THEME]!!

        return if (isDarkTheme) theme.themeDark else theme.themeLight
    }

    fun setThemeName(themeName: String, context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(KEY_NAME, themeName)
            commit()
        }
    }

    fun setThemeDark(isThemeDark: Boolean, context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putBoolean(KEY_DARK, isThemeDark)
            commit()
        }
    }
}