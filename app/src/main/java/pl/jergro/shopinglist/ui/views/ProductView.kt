package pl.jergro.shopinglist.ui.views

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ViewProductBinding
import pl.jergro.shopinglist.models.Product

class ProductView(context: Context) : ConstraintLayout(context) {
    val binding = DataBindingUtil.inflate<ViewProductBinding>(
        LayoutInflater.from(context),
        R.layout.view_product,
        this,
        true
    )
    lateinit var product: Product

    fun bind(product: Product) {
        this.product = product

        binding.productNameText.text = product.name
    }
}