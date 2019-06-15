package pl.jergro.shopinglist

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.databinding.ViewShoppingListBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.utils.dp

class ShoppingListView(context: Context) : FrameLayout(context) {
    private val binding: ViewShoppingListBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_shopping_list, this, true)

    init {
        visibility = INVISIBLE

        setBackgroundResource(R.drawable.list_item_background)

        post {
            val marginHorizontal = 16.dp(context)
            val marginVertical = 4.dp(context)
            val params = (layoutParams as MarginLayoutParams)

            params.setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical)

            layoutParams = params

            visibility = VISIBLE
        }
    }

    fun bind(shoppingList: ShoppingList) {
        binding.shoppingList = shoppingList
    }
}