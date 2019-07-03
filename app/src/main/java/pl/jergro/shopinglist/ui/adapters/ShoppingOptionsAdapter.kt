package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.ShoppingOptions
import pl.jergro.shopinglist.ui.views.ShoppingOptionsListView

class ShoppingOptionsAdapter(
    var data: MutableList<ShoppingOptions>,
    private val listener: Listener
) :
    RecyclerView.Adapter<ShoppingOptionsAdapter.ViewHolder>() {
    class ViewHolder(val shopOptListView: ShoppingOptionsListView) :
        RecyclerView.ViewHolder(shopOptListView.binding.root)

    interface Listener {
        fun onOptionItemClicked(shoppingOptions: ShoppingOptions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ShoppingOptionsListView(parent, this))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shopOptListView.bindView(data[position])
    }

    fun uptData(it: List<ShoppingOptions>) {
        data.clear()
        data.addAll(it)
        notifyDataSetChanged()
    }

    fun onOptionClicked(shoppingOptions: ShoppingOptions) {
        listener.onOptionItemClicked(shoppingOptions)
    }

}