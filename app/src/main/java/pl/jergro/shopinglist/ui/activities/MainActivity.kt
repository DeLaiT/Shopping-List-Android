package pl.jergro.shopinglist.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityMainBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.BottomBarOutlineProvider
import pl.jergro.shopinglist.ui.adapters.ShoppingListAdapter
import pl.jergro.shopinglist.ui.dialogs.AboutDialog
import pl.jergro.shopinglist.ui.dialogs.AddShoppingListDialog
import pl.jergro.shopinglist.ui.dialogs.ShoppingListOptionsDialog
import pl.jergro.shopinglist.utils.elevateOnScroll
import pl.jergro.shopinglist.utils.hideKeyboard
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel


class MainActivity : BaseActivity(), ShoppingListAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val shoppingListAdapter = ShoppingListAdapter(ArrayList(), this)

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainActivityViewModel::class.java) }
    private val addShoppingListDialog by lazy { AddShoppingListDialog(viewModel, this) }
    private val shoppingListOptionsDialog by lazy { ShoppingListOptionsDialog(viewModel, this, this) }
    private val infoDialog by lazy { AboutDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupBottomBarAndItsItems()
        setupObservers()
        setupActionBar()
    }

    private fun setupObservers() {
        viewModel.shoppingListsObservable.observe(this, Observer {
            shoppingListAdapter.updateData(it)
        })
    }

    private fun setupActionBar() {
        binding.actionBar.elevateOnScroll(binding.shoppingListsRecyclerView)
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
        viewModel.loadShoppingLists()
        setupShoppingListRecyclerView()

    }

    private fun setupShoppingListRecyclerView() {
        binding.shoppingListsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shoppingListAdapter

            //`if` prevents from duplicating dividers
            if(itemDecorationCount == 0) {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    private fun setupBottomBarAndItsItems() {
        binding.bottomBar.outlineProvider = BottomBarOutlineProvider()

        binding.addShoppingListButton.setOnClickListener {
            addShoppingListDialog.show()
        }

        binding.infoButton.setOnClickListener {
            infoDialog.show()
        }
    }

    override fun onMenuClicked(shoppingList: ShoppingList) {
        viewModel.shoppingListSelected.postValue(shoppingList)
        shoppingListOptionsDialog.show()
    }

    override fun startSelectedShoppingList(shoppingList: ShoppingList) {
        val intent = Intent(this, ShoppingListActivity::class.java)
        intent.putExtra("selectedShoppingList", shoppingList.name)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}