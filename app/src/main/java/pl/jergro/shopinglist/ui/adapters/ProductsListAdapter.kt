package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.views.ProductView

class ProductsListAdapter(var data: List<Product>, private val listener: Listener) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    class ViewHolder(val shoppingListView: ProductView) : RecyclerView.ViewHolder(shoppingListView.binding.root)

    interface Listener {
        fun onProductItemClicked(product: Product)
        fun onProductChecked(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductView(parent, this))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shoppingListView.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Product>) {
        data = newData
        notifyDataSetChanged()
    }

    fun onProdChecked(product: Product) {
        listener.onProductChecked(product)
    }

    fun onProdClicked(product: Product) {
        listener.onProductItemClicked(product)
    }

}