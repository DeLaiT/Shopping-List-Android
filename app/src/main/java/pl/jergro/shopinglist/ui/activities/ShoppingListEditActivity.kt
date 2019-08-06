package pl.jergro.shopinglist.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityShoppingListEditBinding
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.BottomBarOutlineProvider
import pl.jergro.shopinglist.ui.adapters.EditProductsListAdapter
import pl.jergro.shopinglist.ui.adapters.ProductsListAdapter
import pl.jergro.shopinglist.ui.views.EditProductView
import pl.jergro.shopinglist.utils.elevateOnScroll
import pl.jergro.shopinglist.viewmodels.ShoppingListViewModel

class ShoppingListEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListEditBinding
    private lateinit var productsListAdapter: EditProductsListAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
    }

    private val productsObserver = Observer { products: List<Product> ->
       productsListAdapter.updateData(products)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list_edit)

        val shoppingListName = intent.getStringExtra("selectedShoppingList")
        viewModel.loadShoppingListByName(shoppingListName)

        setupInitialUI(shoppingListName)
        setupActionBar()
        setupRecyclerView()
        setupBottomBar()
    }

    override fun onStart() {
        super.onStart()
        viewModel.productsList.observe(this, productsObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.productsList.removeObserver(productsObserver)
    }

    private fun setupInitialUI(shoppingListName: String) {
        binding.shoppingListNameText.text = shoppingListName
    }

    private fun setupActionBar() {
        binding.actionBar.elevateOnScroll(binding.productsRecyclerView)
    }

    private fun setupBottomBar() {
        binding.bottomBar.outlineProvider = BottomBarOutlineProvider()

        binding.saveButton.setOnClickListener { finish() }
        binding.cancelButton.setOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        productsListAdapter = EditProductsListAdapter(emptyList())

        binding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ShoppingListEditActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = productsListAdapter
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}