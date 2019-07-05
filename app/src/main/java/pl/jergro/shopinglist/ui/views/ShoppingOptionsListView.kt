package pl.jergro.shopinglist.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.jergro.shopinglist.databinding.ViewShoppingOptionsBinding
import pl.jergro.shopinglist.models.ShoppingListOptionsItemConfiguration
import pl.jergro.shopinglist.ui.adapters.handlers.ShoppingOptionListHandler

class ShoppingOptionsListView(parent: ViewGroup, private val listener: Listener) :
    ShoppingOptionListHandler.Listener {

    interface Listener {
        fun onOptionClicked(shoppingListOptionsItemConfiguration: ShoppingListOptionsItemConfiguration)
    }

    val binding =
        ViewShoppingOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    fun bindView(shop: ShoppingListOptionsItemConfiguration) {
        binding.shopListOptItemConfig = shop
        binding.shopOptListHandler = ShoppingOptionListHandler(this)
        binding.executePendingBindings()
    }

    override fun onOptionClicked(shoppingListOptionsItemConfiguration: ShoppingListOptionsItemConfiguration) {
        listener.onOptionClicked(shoppingListOptionsItemConfiguration)
    }
}