package pl.jergro.shopinglist.ui.activities

import android.graphics.Outline
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialog
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityMainBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.ui.dialogs.AddShoppingListDialog
import pl.jergro.shopinglist.ui.views.ShoppingListView
import pl.jergro.shopinglist.utils.dp
import pl.jergro.shopinglist.utils.hideKeyboard
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: MainActivityViewModel
    private lateinit var addShoppingListDialog: AddShoppingListDialog

    private val shoppingListsObserver = Observer<ArrayList<ShoppingList>> { shoppingLists ->
        removeAllItemsFromList()

        shoppingLists.forEach { shoppingList ->
            Timber.v(shoppingList.name)
            addShoppingListViewToList(shoppingList)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewmodel.loadShoppingLists()

        addShoppingListDialog = AddShoppingListDialog(viewmodel, this)

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

    private fun setupBottomBarAndItsItems() {
        binding.bottomBar.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRect(0, (-3).dp(this@MainActivity), view.width, view.height)
            }
        }

        binding.addShoppingListButton.setOnClickListener {
            addShoppingListDialog.show()
        }
    }

    private fun removeAllItemsFromList() {
        binding.shoppingListsList.removeAllViews()
    }

    private fun addShoppingListViewToList(shoppingList: ShoppingList) {
        val shoppingListView = ShoppingListView(this)
        shoppingListView.bind(shoppingList)

        binding.shoppingListsList.addView(shoppingListView)
    }
}