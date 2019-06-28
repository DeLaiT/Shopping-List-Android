package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.views.ProductView
import pl.jergro.shopinglist.ui.views.ShoppingListView

class ProductsListAdapter(var data: List<Product>) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    class ViewHolder(val shoppingListView: ProductView) : RecyclerView.ViewHolder(shoppingListView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        return ViewHolder(ProductView(context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shoppingListView.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Product>) {
        data = newData
        notifyDataSetChanged()
    }
}