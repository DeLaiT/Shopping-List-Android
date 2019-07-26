package pl.jergro.shopinglist.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityShoppingListBinding
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.BottomBarOutlineProvider
import pl.jergro.shopinglist.ui.adapters.ProductsListAdapter
import pl.jergro.shopinglist.ui.dialogs.AddOrUpdateProductDialog
import pl.jergro.shopinglist.utils.elevateOnScroll
import pl.jergro.shopinglist.viewmodels.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity(), ProductsListAdapter.Listener {
    private lateinit var binding: ActivityShoppingListBinding
    private lateinit var productsListAdapter: ProductsListAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
    }
    private val addProductDialog by lazy { AddOrUpdateProductDialog(viewModel, this) }

    private val productsObserver = Observer { products: List<Product> ->
        productsListAdapter.updateData(products)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shoppingListName = intent.getStringExtra("selectedShoppingList")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)

        viewModel.loadShoppingListByName(shoppingListName)

        binding.shoppingListNameText.text = shoppingListName
        binding.backButton.setOnClickListener { finish() }

        setupBottomBar()
        setupRecyclerView()
        setupActionBar()
    }

    override fun onStart() {
        super.onStart()
        viewModel.productsObservable.observe(this, productsObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.productsObservable.removeObserver(productsObserver)
    }

    private fun setupBottomBar() {
        binding.bottomBar.outlineProvider = BottomBarOutlineProvider()

        binding.addProductButton.setOnClickListener { AddOrUpdateProductDialog(viewModel, this).show() }
    }

    private fun setupActionBar() {
        binding.actionBar.elevateOnScroll(binding.productsRecyclerView)
    }

    private fun setupRecyclerView() {
        productsListAdapter = ProductsListAdapter(emptyList(), this)

        binding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ShoppingListActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = productsListAdapter
        }
    }

    override fun onProductItemClicked(product: Product) {
        addProductDialog.setProduct(product)
        addProductDialog.show()
    }

    override fun onProductChecked(product: Product) {
        viewModel.updateProductStatus(product)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}