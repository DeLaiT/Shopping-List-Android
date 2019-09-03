package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import pl.jergro.shopinglist.databinding.DialogAboutBinding
import pl.jergro.shopinglist.databinding.DialogSettingsBinding
import pl.jergro.shopinglist.ui.themes.ThemesManager

class SettingsDialog(context: Context) : BottomSheetDialog(context) {
    val binding = DialogSettingsBinding.inflate(LayoutInflater.from(context))

    override fun getView(): View {
        return binding.root
    }

    override fun onCreated(){
        binding.dark.setOnClickListener { ThemesManager.setThemeDark(true, context) }
        binding.light.setOnClickListener { ThemesManager.setThemeDark(false, context) }
    }
}