package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import pl.jergro.shopinglist.databinding.DialogAboutBinding
import pl.jergro.shopinglist.databinding.DialogSettingsBinding

class SettingsDialog(context: Context) : BottomSheetDialog(context) {
    val binding = DialogSettingsBinding.inflate(LayoutInflater.from(context))

    override fun getView(): View {
        return binding.root
    }

    override fun onCreated() {

    }
}