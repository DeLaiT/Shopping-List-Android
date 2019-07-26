package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.ShoppingListOptionsItemConfiguration
import pl.jergro.shopinglist.ui.views.ShoppingOptionsListView

class ShoppingOptionsListAdapter(
    var data: MutableList<ShoppingListOptionsItemConfiguration>,
    private val listener: Listener
) : RecyclerView.Adapter<ShoppingOptionsListAdapter.ViewHolder>(), ShoppingOptionsListView.Listener {
    class ViewHolder(val shopOptListView: ShoppingOptionsListView) :
        RecyclerView.ViewHolder(shopOptListView.binding.root)

    interface Listener {
        fun onOptionItemClicked(shoppingListOptionsItemConfiguration: ShoppingListOptionsItemConfiguration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ShoppingOptionsListView(parent, this))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shopOptListView.bindView(data[position])
    }

    fun updateData(it: List<ShoppingListOptionsItemConfiguration>) {
        data.clear()
        data.addAll(it)
        notifyDataSetChanged()
    }

    override fun onOptionClicked(shoppingListOptionsItemConfiguration: ShoppingListOptionsItemConfiguration) {
        listener.onOptionItemClicked(shoppingListOptionsItemConfiguration)
    }

}