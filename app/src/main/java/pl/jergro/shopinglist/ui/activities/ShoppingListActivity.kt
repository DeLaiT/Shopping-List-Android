package pl.jergro.shopinglist.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityShoppingListBinding
import pl.jergro.shopinglist.ui.BottomBarOutlineProvider
import pl.jergro.shopinglist.viewmodels.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity(){
    private lateinit var binding: ActivityShoppingListBinding
    private val viewModel = ShoppingListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shoppingListName = intent.getStringExtra("shoppingList")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)

        viewModel.loadShoppingListByName(shoppingListName)

        binding.shoppingListNameText.text = shoppingListName
        binding.bottomBar.outlineProvider = BottomBarOutlineProvider()
    }
}