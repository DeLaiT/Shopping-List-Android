package pl.jergro.shopinglist.ui.adapters.handlers

import pl.jergro.shopinglist.models.ShoppingList

class ShoppingListHandler(private val listener: HandlerListener) {

    interface HandlerListener {
        fun onShopItemClicked(shoppingList: ShoppingList)
        fun onClickOpenMenu(shoppingList: ShoppingList)
    }

    fun onShoppingListItemClicked(shoppingList: ShoppingList) {
        listener.onShopItemClicked(shoppingList)
    }

    fun onShopListClickMenu(shoppingList: ShoppingList) {
        listener.onClickOpenMenu(shoppingList)
    }
}