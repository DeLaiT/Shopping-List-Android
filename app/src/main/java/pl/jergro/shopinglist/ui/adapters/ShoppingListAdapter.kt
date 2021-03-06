package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.views.ShoppingListView

class ShoppingListAdapter(var data: ArrayList<ShoppingList>, private val listener: Listener) :
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>(), ShoppingListView.Listener {
    class ViewHolder(val shoppingListView: ShoppingListView) : RecyclerView.ViewHolder(shoppingListView.binding.root)

    interface Listener {
        fun startSelectedShoppingList(shoppingList: ShoppingList)
        fun onMenuClicked(shoppingList: ShoppingList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShoppingListView(parent, this))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shoppingListView.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: ArrayList<ShoppingList>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onShopItemClicked(shoppingList: ShoppingList) {
        listener.startSelectedShoppingList(shoppingList)
    }

    override fun onClickOpenMenu(shoppingList: ShoppingList) {
        listener.onMenuClicked(shoppingList)
    }
}