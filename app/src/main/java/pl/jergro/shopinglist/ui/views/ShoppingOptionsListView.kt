package pl.jergro.shopinglist.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.jergro.shopinglist.databinding.ViewShoppingOptionsBinding
import pl.jergro.shopinglist.models.ShoppingOptions
import pl.jergro.shopinglist.ui.adapters.ShoppingOptionsAdapter

class ShoppingOptionsListView(parent: ViewGroup, private val optShopAdapter: ShoppingOptionsAdapter) {

    val binding =
        ViewShoppingOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)!!

    fun bindView(shop: ShoppingOptions) {
        binding.opt = shop
        binding.adp = optShopAdapter
        binding.executePendingBindings()
    }
}