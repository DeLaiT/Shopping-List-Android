package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import pl.jergro.shopinglist.databinding.DialogAboutBinding

class AboutDialog(context: Context) : BottomSheetDialog(context) {
    val binding = DialogAboutBinding.inflate(LayoutInflater.from(context))

    override fun getView(): View {
        return binding.root
    }

    override fun onCreated() {

    }
}