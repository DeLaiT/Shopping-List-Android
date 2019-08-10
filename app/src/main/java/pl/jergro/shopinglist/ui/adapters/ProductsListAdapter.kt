package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.views.ProductView

class ProductsListAdapter(private val listener: Listener) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>(), ProductView.Listener {
    class ViewHolder(val productView: ProductView) : RecyclerView.ViewHolder(productView.binding.root)

    private var data: List<Product>? = null

    interface Listener {
        fun onProductItemClicked(product: Product)
        fun onProductChecked(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductView(parent, this))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productView.bind(data!![position])
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun updateData(newData: List<Product>) {
        data = newData.sortedBy { it.index }
        notifyDataSetChanged()
    }

    override fun onProductChecked(product: Product) {
        listener.onProductChecked(product)
    }

    override fun onProductClicked(product: Product) {
        listener.onProductItemClicked(product)
    }
}