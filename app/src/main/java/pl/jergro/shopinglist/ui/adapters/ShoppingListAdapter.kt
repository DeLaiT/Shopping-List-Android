package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.views.ShoppingListView

class ShoppingListAdapter(var data: ArrayList<ShoppingList>) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    class ViewHolder(val shoppingListView: ShoppingListView) : RecyclerView.ViewHolder(shoppingListView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        return ViewHolder(ShoppingListView(context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shoppingListView.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: ArrayList<ShoppingList>) {
        data = newData
        notifyDataSetChanged()
    }
}