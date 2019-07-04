package pl.jergro.shopinglist.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.jergro.shopinglist.databinding.ViewProductBinding
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.adapters.ProductsListAdapter

class ProductView(parent: ViewGroup, private val productsListAdapter: ProductsListAdapter) {

    val binding = ViewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    fun bind(product: Product) {
        binding.product = product
        binding.adp = productsListAdapter
        binding.executePendingBindings()
    }

}