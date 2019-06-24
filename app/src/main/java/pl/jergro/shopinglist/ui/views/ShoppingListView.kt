package pl.jergro.shopinglist.ui.views

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ViewShoppingListBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.utils.dp
import kotlin.math.roundToInt

class ShoppingListView(context: Context) : FrameLayout(context) {
    private val binding: ViewShoppingListBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_shopping_list, this, true
        )

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
        val productsDone = shoppingList.products.count { it.done }
        val productsCount = shoppingList.products.size
        val donePrecentage = if (productsCount == 0) 0.0 else productsDone.toDouble() / productsCount

        binding.nameText.text = shoppingList.name
        binding.itemsDoneText.text = "$productsDone / $productsCount - ${(donePrecentage * 100).toInt()}%"

        post {
            val params = binding.progressBarIndeterminate.layoutParams as LayoutParams
            params.width = (binding.progressBar.width * donePrecentage).toInt()
            binding.progressBarIndeterminate.layoutParams = params
        }
    }
}