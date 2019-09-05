package pl.jergro.shopinglist.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.jergro.shopinglist.databinding.ViewShoppingListBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.adapters.handlers.ShoppingListHandler

@SuppressLint("SetTextI18n")
class ShoppingListView(val parent: ViewGroup, private val listener: Listener) :
    ShoppingListHandler.HandlerListener {

    interface Listener {
        fun onShopItemClicked(shoppingList: ShoppingList)
        fun onClickOpenMenu(shoppingList: ShoppingList)
    }

    val binding =
        ViewShoppingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    val context: Context by lazy { parent.context }

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

        binding.itemsDoneText.text =
            "$productsDone / $productsCount - ${(donePercentage * 100).toInt()}%"

        if (donePercentage > 0) {
            binding.progressBar.progress = (donePercentage * 100).toInt()
            binding.progressBar.progressDrawable.colorFilter = null
        } else {
            binding.progressBar.progress = 1
        }
    }

    override fun onShopItemClicked(shoppingList: ShoppingList) {
        listener.onShopItemClicked(shoppingList)
    }

    override fun onClickOpenMenu(shoppingList: ShoppingList) {
        listener.onClickOpenMenu(shoppingList)
    }
}