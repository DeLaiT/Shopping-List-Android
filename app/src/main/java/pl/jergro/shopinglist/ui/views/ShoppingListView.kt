package pl.jergro.shopinglist.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.jergro.shopinglist.databinding.ViewShoppingListBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.adapters.handlers.ShoppingListHandler

class ShoppingListView(parent: ViewGroup, private val listener: Listener) : ShoppingListHandler.HandlerListener {

    interface Listener {
        fun onShopItemClicked(shoppingList: ShoppingList)
        fun onClickOpenMenu(shoppingList: ShoppingList)
    }

    val binding = ViewShoppingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    fun bind(shoppingList: ShoppingList) {
        binding.shoppingList = shoppingList
        binding.shopListHandler = ShoppingListHandler(this)
        binding.executePendingBindings()

        val productsDone = shoppingList.products.count { it.done }
        val productsCount = shoppingList.products.size
        val donePercentage = if (productsCount == 0) {
            0.0
        } else {
            productsDone.toDouble() / productsCount
        }

        binding.itemsDoneText.text = "$productsDone / $productsCount - ${(donePercentage * 100).toInt()}%"

        binding.progressBar.progress = (donePercentage * 100).toInt()

    }

    override fun onShopItemClicked(shoppingList: ShoppingList) {
        listener.onShopItemClicked(shoppingList)
    }

    override fun onClickOpenMenu(shoppingList: ShoppingList) {
        listener.onClickOpenMenu(shoppingList)
    }
}