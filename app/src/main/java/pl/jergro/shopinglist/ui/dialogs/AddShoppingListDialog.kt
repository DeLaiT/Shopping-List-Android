package pl.jergro.shopinglist.ui.dialogs

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ViewAddShoppingListBinding
import pl.jergro.shopinglist.utils.hideKeyboardFrom
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel

@SuppressLint("ViewConstructor")
class AddShoppingListDialog(val viewmodel: MainActivityViewModel, context: Context) : BottomSheetDialog(context) {
    val binding = DataBindingUtil.inflate<ViewAddShoppingListBinding>(
        LayoutInflater.from(context),
        R.layout.dialog_add_shopping_list,
        null,
        false
    )

    override fun getView(): View = binding.root

    override fun onCreated() {
        binding.addButton.setOnClickListener {
            onAddShoppingListButtonClick()
        }

        setOnShowListener {
            binding.newShoppingListNameEditText.text?.clear()
        }
    }

    private fun onAddShoppingListButtonClick() {
        val newShoppingListName = binding.newShoppingListNameEditText.text.toString()

        when {
            newShoppingListName.isBlank() -> Toast.makeText(context, "Please enter correct shopping list name", Toast.LENGTH_SHORT).show()
            viewmodel.shoppingListExistsWithName(newShoppingListName) -> Toast.makeText(context, "Shopping list with this name already exists!", Toast.LENGTH_SHORT).show()
            else -> {
                viewmodel.createShoppingListWithName(newShoppingListName)
                hideKeyboardFrom(context, binding.newShoppingListNameEditText)
                dismiss()
            }
        }
    }
}