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
import java.util.*

class AddOrUpdateProductDialog(private val viewModel: ShoppingListViewModel, context: Context) :
    BottomSheetDialog(context) {
    private var binding = DialogAddProductBinding.inflate(layoutInflater, null, false)
    private var editedProduct: Product? = null

    override fun getView(): View {
        return binding.root
    }

    override fun onCreated() {
        binding.isEditing = editedProduct != null

        if (binding.isEditing) {
            binding.newProductNameEditText.setText(editedProduct?.name)
            if(editedProduct?.price != 0.0) {
                binding.newProductPriceEditText.setText(editedProduct?.price.toString())
            } else {
                binding.newProductPriceEditText.text?.clear()
            }
        } else {
            binding.newProductNameEditText.text?.clear()
            binding.newProductPriceEditText.text?.clear()
        }

        binding.addButton.setOnClickListener {
            tryToAddOrUpdateProduct()
        }
    }

    private fun tryToAddOrUpdateProduct() {
        val productName = binding.newProductNameEditText.text.toString()
        val productPrice = if (binding.newProductPriceEditText.text.isNullOrBlank()) {
            "0.0"
        } else {
            binding.newProductPriceEditText.text.toString()
        }

        if (productName.isBlank())
            Toast.makeText(context, "Please enter correct shopping list name", Toast.LENGTH_SHORT).show()
        else {
            if (editedProduct != null) {
                val product = Product(editedProduct?.id!!, productName, false, productPrice.toDouble(), 0)
                viewModel.updateProduct(product)
            } else {
                val product = Product(UUID.randomUUID().toString(), productName, false, productPrice.toDouble(), 0)
                viewModel.addProductToSelectedShoppingList(product)
            }

            dismiss()
        }
    }

    fun setProduct(product: Product) {
        this.editedProduct = product
    }
}