package pl.jergro.shopinglist.ui.themes

import android.content.Context
import androidx.annotation.StyleRes
import pl.jergro.shopinglist.R
import java.util.*

object ThemesManager {
    private val themes by lazy {
        hashMapOf(
            DEFAULT_THEME to ThemeModel(R.style.AppTheme, R.style.DarkAppTheme, R.color.md_indigo_A400, DEFAULT_THEME),
            PINK to ThemeModel(R.style.AppTheme, R.style.DarkAppTheme, R.color.md_pink_A400, PINK),
            GREEN to ThemeModel(R.style.AppTheme, R.style.DarkAppTheme, R.color.md_green_A400, GREEN),
            YELLOW to ThemeModel(R.style.AppTheme, R.style.DarkAppTheme, R.color.md_amber_A400, YELLOW),
            ORANGE to ThemeModel(R.style.AppTheme, R.style.DarkAppTheme, R.color.md_deep_orange_A400, ORANGE)
        )
    }

    val themesList: Collection<ThemeModel> get() = Collections.unmodifiableCollection(themes.values)
    private const val PREFS_NAME = "Themes"
    private const val KEY_DARK = "dark"
    private const val KEY_NAME = "name"

    private const val DEFAULT_THEME = "blue"
    private const val PINK = "pink"
    private const val GREEN = "green"
    private const val YELLOW = "yellow"
    private const val ORANGE = "orange"

    @StyleRes
    fun getCurrentThemeRes(context: Context): Int {
        val selectedThemeName = getCurrentThemeName(context)
        val isDarkTheme = isDarkTheme(context)

        val theme = themes[selectedThemeName] ?: themes[DEFAULT_THEME]!!

        return if (isDarkTheme) theme.themeDark else theme.themeLight
    }

    fun getCurrentThemeName(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        return prefs.getString(
            KEY_NAME,
            DEFAULT_THEME
        )!!
    }

    fun isDarkTheme(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        return prefs.getBoolean(KEY_DARK, false)
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