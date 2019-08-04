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
import pl.jergro.shopinglist.ui.adapters.ProductsListAdapter
import pl.jergro.shopinglist.utils.elevateOnScroll
import pl.jergro.shopinglist.viewmodels.ShoppingListViewModel

class ShoppingListEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListEditBinding
    private lateinit var productsListAdapter: ProductsListAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)
    }

    private val productsObserver = Observer { products: List<Product> ->
        //TODO observe product list for changes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list_edit)

        val shoppingListName = intent.getStringExtra("selectedShoppingList")
        viewModel.loadShoppingListByName(shoppingListName)

        setupInitialUI(shoppingListName)
        setupActionBar()
        setupRecyclerView()
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
        binding.saveButton.setOnClickListener { finish() }
        binding.cancelButton.setOnClickListener { finish() }
    }

    private fun setupActionBar() {
        binding.actionBar.elevateOnScroll(binding.productsRecyclerView)
    }

    private fun setupRecyclerView() {
//        productsListAdapter = ProductsListAdapter(emptyList(), this)
//
//        binding.productsRecyclerView.apply {
//            layoutManager = LinearLayoutManager(this@ShoppingListActivity)
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            adapter = productsListAdapter
//        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}