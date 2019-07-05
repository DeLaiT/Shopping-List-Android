package pl.jergro.shopinglist.ui.adapters.handlers

import pl.jergro.shopinglist.models.Product

class ProductListHandler(private val listener: Listener) {

    interface Listener {
        fun onProductChecked(product: Product)
        fun onProductClicked(product: Product)
    }

    fun onProductChecked(product: Product) {
        listener.onProductChecked(product)
    }

    fun onProductClicked(product: Product) {
        listener.onProductClicked(product)
    }

}