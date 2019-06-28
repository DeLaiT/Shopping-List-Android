package pl.jergro.shopinglist.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityShoppingListBinding
import pl.jergro.shopinglist.ui.BottomBarOutlineProvider
import pl.jergro.shopinglist.ui.dialogs.AddProductDialog
import pl.jergro.shopinglist.viewmodels.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel
    private val addProductDialog by lazy { AddProductDialog(viewModel, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shoppingListName = intent.getStringExtra("selectedShoppingList")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)
        viewModel = ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)

        viewModel.loadShoppingListByName(shoppingListName)

        binding.shoppingListNameText.text = shoppingListName

        setupBottomBar()
    }

    private fun setupBottomBar() {
        binding.bottomBar.outlineProvider = BottomBarOutlineProvider()

        binding.addProductButton.setOnClickListener {
            addProductDialog.show()
        }
    }
}