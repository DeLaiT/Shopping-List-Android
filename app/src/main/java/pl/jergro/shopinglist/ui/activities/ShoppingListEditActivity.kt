package pl.jergro.shopinglist.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityShoppingListEditBinding
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.ui.BottomBarOutlineProvider
import pl.jergro.shopinglist.ui.adapters.EditProductsListAdapter
import pl.jergro.shopinglist.ui.adapters.ItemTouchHelperAdapter
import pl.jergro.shopinglist.ui.adapters.SimpleItemTouchHelperCallback
import pl.jergro.shopinglist.viewmodels.ShoppingListEditViewModel

class ShoppingListEditActivity : BaseActivity() {
    private lateinit var binding: ActivityShoppingListEditBinding
    private lateinit var editProductsListAdapter: EditProductsListAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ShoppingListEditViewModel::class.java)
    }

    private val productsObserver = Observer { products: List<Product> ->
        val arrayList = ArrayList<Product>()
        products.forEach { arrayList.add(it) }
        arrayList.sortBy { it.index }
        editProductsListAdapter.updateData(arrayList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list_edit)

        val shoppingListName = intent.getStringExtra("selectedShoppingList")
        viewModel.loadProductsByShoppingListName(shoppingListName)

        setupInitialUI(shoppingListName)
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

    private fun setupBottomBar() {
        binding.bottomBar.outlineProvider = BottomBarOutlineProvider()

        binding.saveButton.setOnClickListener {
            updateProductsIndexes()
            finish()
        }
        binding.cancelButton.setOnClickListener { finish() }
    }

    private fun updateProductsIndexes() {
        viewModel.updateProductsIndexesByListOrder(editProductsListAdapter.data)
    }

    private fun setupRecyclerView() {
        editProductsListAdapter = EditProductsListAdapter(ArrayList())

        binding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ShoppingListEditActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = editProductsListAdapter

            val touchCallback = SimpleItemTouchHelperCallback(adapter as ItemTouchHelperAdapter)
            val itemTouchHelper = ItemTouchHelper(touchCallback)
            itemTouchHelper.attachToRecyclerView(binding.productsRecyclerView)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.still, R.anim.fade_out)
    }
}