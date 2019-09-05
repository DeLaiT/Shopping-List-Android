package pl.jergro.shopinglist.ui.dialogs

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.DialogSettingsBinding
import pl.jergro.shopinglist.ui.themes.ThemesManager
import pl.jergro.shopinglist.ui.views.PickColorView
import timber.log.Timber

class SettingsDialog(private val activityContext: Context) : BottomSheetDialog(activityContext) {
    private val binding = DialogSettingsBinding.inflate(LayoutInflater.from(context))
    private val colorViews = ArrayList<PickColorView>()

    override fun getView(): View {
        return binding.root
    }

    override fun onCreated(){
        if(ThemesManager.isDarkTheme(context)) {
            binding.dark.setImageResource(R.drawable.ic_round_check_24px)
        } else {
            binding.light.setImageResource(R.drawable.ic_round_check_24px)
        }

        binding.dark.setOnClickListener {
            binding.light.setImageDrawable(null)
            binding.dark.setImageResource(R.drawable.ic_round_check_24px)

            ThemesManager.setThemeDark(true, context)
            (activityContext as Activity).recreate()
        }
        binding.light.setOnClickListener {
            binding.dark.setImageDrawable(null)
            binding.light.setImageResource(R.drawable.ic_round_check_24px)

            ThemesManager.setThemeDark(false, context)
            (activityContext as Activity).recreate()
        }

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
                (activityContext as Activity).recreate()
            }

            colorViews.add(view)
            binding.colorsList.addView(view)
        }
    }
}