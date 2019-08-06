package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.views.EditProductView
import pl.jergro.shopinglist.ui.views.ProductView

class EditProductsListAdapter(var data: List<Product>) : RecyclerView.Adapter<EditProductsListAdapter.ViewHolder>(){
    class ViewHolder(val productView: EditProductView) : RecyclerView.ViewHolder(productView.binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EditProductView(parent))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productView.bind(data[position], position+1)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Product>) {
        data = newData
        notifyDataSetChanged()
    }
}