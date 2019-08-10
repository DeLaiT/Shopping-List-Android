package pl.jergro.shopinglist.ui.adapters

import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.views.EditProductView
import pl.jergro.shopinglist.utils.dp
import timber.log.Timber

class EditProductsListAdapter(var data: ArrayList<Product>) :
    RecyclerView.Adapter<EditProductsListAdapter.ViewHolder>(), ItemTouchHelperAdapter {
    class ViewHolder(val productView: EditProductView) : RecyclerView.ViewHolder(productView.binding.root),
        ItemTouchHelperViewHolder {
        override fun onItemSelected() {
            Timber.v("Selected")
            val view = productView.binding.root
            val context = view.context

            view.animate()
                .translationZ(2.dp(context).toFloat())
                .scaleX(1.03f)
                .scaleY(1.03f)
                .setDuration(200L)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }

        override fun onItemClear() {
            val view = productView.binding.root

            view.animate()
                .translationZ(0.0f)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setDuration(200L)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EditProductView(parent))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productView.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onItemMove(fromPos: Int, toPos: Int) {
        val prod = data.removeAt(fromPos)

        data.add(toPos, prod)
        notifyItemMoved(fromPos, toPos)
    }

    fun updateData(newData: ArrayList<Product>) {
        data = newData
        notifyDataSetChanged()
    }
}