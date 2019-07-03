package pl.jergro.shopinglist.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.jergro.shopinglist.databinding.ViewShoppingListBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.adapters.ShoppingListAdapter

class ShoppingListView(parent: ViewGroup, private val shoppingListAdapter: ShoppingListAdapter) {
    val binding = ViewShoppingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    fun bind(shoppingList: ShoppingList) {
        binding.shop = shoppingList
        binding.adp = shoppingListAdapter
        binding.executePendingBindings()

        val productsDone = shoppingList.products.count { it.done }
        val productsCount = shoppingList.products.size
        val donePercentage = if (productsCount == 0) {
            0.0
        } else {
            productsDone.toDouble() / productsCount
        }

        binding.itemsDoneText.text = "$productsDone / $productsCount - ${(donePercentage * 100).toInt()}%"

        /*post {
            val params = binding.progressBarIndeterminate.layoutParams as LayoutParams
            params.width = (binding.progressBar.width * donePercentage).toInt()
            binding.progressBarIndeterminate.layoutParams = params
        }*/

    }
}