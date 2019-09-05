package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import pl.jergro.shopinglist.databinding.DialogSettingsBinding
import pl.jergro.shopinglist.ui.themes.ThemesManager
import pl.jergro.shopinglist.ui.views.PickColorView
import timber.log.Timber

class SettingsDialog(context: Context) : BottomSheetDialog(context) {
    private val binding = DialogSettingsBinding.inflate(LayoutInflater.from(context))
    private val colorViews = ArrayList<PickColorView>()

    override fun getView(): View {
        return binding.root
    }

    override fun onCreated(){
        binding.dark.setOnClickListener { ThemesManager.setThemeDark(true, context) }
        binding.light.setOnClickListener { ThemesManager.setThemeDark(false, context) }

        setupColorViews()
    }

    private fun setupColorViews() {
        val selectedColorName = ThemesManager.getCurrentThemeName(context)
        val themes = ThemesManager.themesList

        themes.forEach { themeModel ->
            val view = PickColorView(context.getColor(themeModel.themeColor), context)

            if(themeModel.themeName == selectedColorName) {
                view.setChecked(true)
            }

            view.setOnClickListener {
                Timber.v("Changing accent color to ${themeModel.themeName}")
                colorViews.forEach { it.setChecked(false) }
                view.setChecked(true)
                ThemesManager.setThemeName(themeModel.themeName, context)
            }

            colorViews.add(view)
            binding.colorsList.addView(view)
        }
    }
}