package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.views.ProductView

class ProductsListAdapter(var data: List<Product>, private val listener: Listener) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    class ViewHolder(val shoppingListView: ProductView) : RecyclerView.ViewHolder(shoppingListView)

    interface Listener {
        fun onProductItemClicked(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        return ViewHolder(ProductView(context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shoppingListView.bind(data[position])
        holder.itemView.setOnClickListener { listener.onProductItemClicked(data[position]) }
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Product>) {
        data = newData
        notifyDataSetChanged()
    }
}