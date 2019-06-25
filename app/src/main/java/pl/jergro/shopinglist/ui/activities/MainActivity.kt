package pl.jergro.shopinglist.ui.activities

import android.graphics.Outline
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityMainBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.BottomBarOutlineProvider
import pl.jergro.shopinglist.ui.adapters.ShoppingListAdapter
import pl.jergro.shopinglist.ui.dialogs.AddShoppingListDialog
import pl.jergro.shopinglist.ui.views.ShoppingListView
import pl.jergro.shopinglist.utils.dp
import pl.jergro.shopinglist.utils.hideKeyboard
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: MainActivityViewModel
    private val addShoppingListDialog: AddShoppingListDialog by lazy { AddShoppingListDialog(viewmodel, this) }
    private val shoppingListAdapter = ShoppingListAdapter(ArrayList())

    private val shoppingListsObserver = Observer<ArrayList<ShoppingList>> { shoppingLists ->
        shoppingListAdapter.updateData(shoppingLists)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewmodel.loadShoppingLists()

        setupShoppingListRecyclerView()
        setupBottomBarAndItsItems()
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    override fun onStart() {
        super.onStart()
        viewmodel.shoppingListsObservable.observe(this, shoppingListsObserver)
    }

    override fun onPause() {
        super.onPause()
        viewmodel.shoppingListsObservable.removeObserver(shoppingListsObserver)
    }

    private fun setupShoppingListRecyclerView() {
        binding.shoppingListsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = shoppingListAdapter
        }
    }

    private fun setupBottomBarAndItsItems() {
        binding.bottomBar.outlineProvider = BottomBarOutlineProvider()

        binding.addShoppingListButton.setOnClickListener {
            addShoppingListDialog.show()
        }
    }
}