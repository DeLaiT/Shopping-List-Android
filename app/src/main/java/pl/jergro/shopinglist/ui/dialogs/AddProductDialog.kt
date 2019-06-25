package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.DialogAddProductBinding

class AddProductDialog(val viewModel: ViewModel, context: Context) : BottomSheetDialog(context) {
    private lateinit var binding: DialogAddProductBinding

    override fun getView(): View {
        val layoutInflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_add_product, null, false)

        return binding.root
    }

    override fun onCreated() {

    }

}