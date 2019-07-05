package pl.jergro.shopinglist.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.jergro.shopinglist.databinding.ViewProductBinding
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.adapters.handlers.ProductListHandler

class ProductView(parent: ViewGroup, private val listener: Listener) :
    ProductListHandler.Listener {

    interface Listener {
        fun onProductChecked(product: Product)
        fun onProductClicked(product: Product)
    }

    val binding = ViewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    fun bind(product: Product) {
        binding.product = product
        binding.prodListHandler = ProductListHandler(this)
        binding.executePendingBindings()
    }

    override fun onProductChecked(product: Product) {
        listener.onProductChecked(product)
    }

    override fun onProductClicked(product: Product) {
        listener.onProductClicked(product)
    }

}