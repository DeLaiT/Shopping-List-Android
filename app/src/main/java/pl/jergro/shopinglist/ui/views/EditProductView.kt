package pl.jergro.shopinglist.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.jergro.shopinglist.databinding.ViewEditProductBinding
import pl.jergro.shopinglist.models.Product

class EditProductView(parent: ViewGroup) {
    val binding = ViewEditProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    fun bind(product: Product) {
        binding.product = product
        binding.executePendingBindings()
    }
}