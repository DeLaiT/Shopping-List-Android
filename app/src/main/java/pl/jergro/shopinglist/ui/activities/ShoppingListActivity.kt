package pl.jergro.shopinglist.ui.activities

import android.content.Intent
import android.os.Bundle
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
import timber.log.Timber

class ShoppingListActivity : BaseActivity(), ProductsListAdapter.Listener {
    private lateinit var binding: ActivityShoppingListBinding
    private lateinit var productsListAdapter: ProductsListAdapter
    private lateinit var shoppingListName: String

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
    }
    private val addProductDialog by lazy { AddOrUpdateProductDialog(viewModel, this) }

    private val productsObserver = Observer { products: List<Product> ->
        productsListAdapter.updateData(products)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)

        shoppingListName = intent.getStringExtra("selectedShoppingList")
        viewModel.loadProductsByShoppingListName(shoppingListName)

        binding.shoppingListNameText.text = shoppingListName
        binding.backButton.setOnClickListener { finish() }

        setupBottomBar()
        setupRecyclerView()
        setupActionBar()
    }

    override fun onStart() {
        super.onStart()
        viewModel.productsList.observe(this, productsObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.productsList.removeObserver(productsObserver)
    }

    private fun setupBottomBar() {
        binding.bottomBar.outlineProvider = BottomBarOutlineProvider()
        binding.editButton.setOnClickListener { startShoppingListEditActivity() }
        binding.shareButton.setOnClickListener { shareShoppingList() }
        binding.addProductButton.setOnClickListener {
            addProductDialog.setProduct(null)
            addProductDialog.show()
        }
    }

    private fun setupActionBar() {
        binding.actionBar.elevateOnScroll(binding.productsRecyclerView)
    }

    private fun setupRecyclerView() {
        productsListAdapter = ProductsListAdapter(this)

        binding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ShoppingListActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = productsListAdapter
        }
    }

    private fun shareShoppingList() {
        val productsListDataForSharing = viewModel.getProductsListForSharing(shoppingListName)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Shopping list '$shoppingListName'")
            putExtra(Intent.EXTRA_TEXT, productsListDataForSharing)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.shareUsing)))

        Timber.v(productsListDataForSharing)
    }

    override fun onProductItemClicked(product: Product) {
        addProductDialog.setProduct(product)
        addProductDialog.show()
    }

    override fun onProductChecked(product: Product) {
        viewModel.updateProductStatus(product)
    }

    private fun startShoppingListEditActivity() {
        val intent = Intent(this, ShoppingListEditActivity::class.java)
        intent.putExtra("selectedShoppingList", shoppingListName)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.still)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.still, R.anim.fade_out)
    }
}