package pl.jergro.shopinglist.ui.adapters.handlers

import pl.jergro.shopinglist.models.ShoppingListOptionsItemConfiguration

class ShoppingOptionListHandler(private val listener: Listener) {

    interface Listener {
        fun onOptionClicked(shoppingListOptionsItemConfiguration: ShoppingListOptionsItemConfiguration)
    }

    fun onOptionClicked(shoppingListOptionsItemConfiguration: ShoppingListOptionsItemConfiguration) {
        listener.onOptionClicked(shoppingListOptionsItemConfiguration)
    }

}