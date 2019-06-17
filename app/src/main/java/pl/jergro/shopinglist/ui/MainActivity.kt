package pl.jergro.shopinglist.ui

import android.content.pm.ActivityInfo
import android.graphics.Outline
import android.os.Bundle
import android.view.*
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ActivityMainBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.utils.dp
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: MainActivityViewModel
    lateinit var addShoppingListDialog: BottomSheetDialog

    val shoppingListsObserver = Observer<ArrayList<ShoppingList>> { shoppingLists ->
        Timber.d("Received shopping lists list update")
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

        setupBottomBarAndItsItems()
        createAddShoppingListDialog()
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

    private fun createAddShoppingListDialog() {
        val view = AddShoppingListView(viewmodel, this)

        addShoppingListDialog = BottomSheetDialog(this)
        addShoppingListDialog.window?.setLayout(MATCH_PARENT, MATCH_PARENT)
        addShoppingListDialog.setContentView(view)
    }

    private fun addShoppingListViewToList(shoppingList: ShoppingList) {
        val shoppingListView = ShoppingListView(this)
        shoppingListView.bind(shoppingList)

        binding.shoppingListsList.addView(shoppingListView)
    }
}
