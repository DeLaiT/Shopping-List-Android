package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.DialogAddProductBinding
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.viewmodels.ShoppingListViewModel

class AddProductDialog(val viewModel: ShoppingListViewModel, context: Context) : BottomSheetDialog(context) {
    private lateinit var binding: DialogAddProductBinding

    override fun getView(): View {
        val layoutInflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_add_product, null, false)

        return binding.root
    }

    override fun onCreated() {
        binding.newProductNameEditText.text?.clear()
        binding.newProductPriceEditText.text?.clear()

        binding.addButton.setOnClickListener {
            tryToAddProduct()
        }
    }

    private fun tryToAddProduct() {
        val productName = binding.newProductNameEditText.text.toString()
        val productPrice = binding.newProductPriceEditText.text.toString()

        if(productName.isBlank())
            Toast.makeText(context, "Please enter correct shopping list name", Toast.LENGTH_SHORT).show()
        else {
            val product = Product(productName, false, productPrice.toDouble())

            viewModel.addProductToSelectedShoppingList(product)
            dismiss()
        }
    }
}