package pl.jergro.shopinglist

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.databinding.ViewShoppingListBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.utils.dp

class ShoppingListView(context: Context) : FrameLayout(context) {
    val binding: ViewShoppingListBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_shopping_list, this, true)

    init {
        visibility = INVISIBLE

        setBackgroundResource(R.drawable.list_item_background)

        post {
            val margin = 8.dp(context)
            val params = (layoutParams as MarginLayoutParams)

            params.setMargins(margin, margin/2, margin, margin/2)

            layoutParams = params

            visibility = VISIBLE
        }
    }

    fun bind(shoppingList: ShoppingList) {
        binding.shoppingList = shoppingList
    }
}