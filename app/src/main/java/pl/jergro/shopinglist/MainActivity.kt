package pl.jergro.shopinglist

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.jergro.shopinglist.databinding.ActivityMainBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.utils.dp
import pl.jergro.shopinglist.utils.hideKeyboard
import pl.jergro.shopinglist.utils.showKeyboard
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: MainActivityViewModel
    var addShoppingListDialogVisible = false

    val shoppingListsObserver = Observer<ArrayList<ShoppingList>> { shoppingLists ->
        Timber.d("Received shopping lists list update")
        shoppingLists.forEach { shoppingList ->
            Timber.v(shoppingList.name)
            addShoppingListViewToList(shoppingList)
        }
    }

    override fun onStart() {
        super.onStart()
        viewmodel.shoppingListsObservable.observe(this, shoppingListsObserver)
    }

    override fun onPause() {
        super.onPause()
        viewmodel.shoppingListsObservable.removeObserver(shoppingListsObserver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        setupListeners()
        viewmodel.loadShoppingLists()
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setupListeners() {
        binding.toggleAddShoppingListButton.setOnClickListener {
            toggleShoppingListContainer()
        }

        binding.confirmNewShoppingList.setOnClickListener {
            createNewShoppingList()
            toggleShoppingListContainer()
        }
    }

    private fun createNewShoppingList() {
        val shoppingListName = binding.addShoppingListEt.text.toString()

        if (shoppingListName.isBlank()) return
        if (viewmodel.shoppingListExistsWithName(shoppingListName)) return

        viewmodel.createShoppingListWithName(shoppingListName)
    }

    private fun toggleShoppingListContainer() {
        val targetWidth =
            if (addShoppingListDialogVisible) 42.dp(this)
            else binding.root.width - 16.dp(this) * 2 - 42.dp(this) - 4.dp(this)

        animateInputBarWidth(targetWidth)
        binding.addShoppingListEt.requestFocus()
        binding.addShoppingListEt.setText("")
        if (addShoppingListDialogVisible)
            hideKeyboard()
        else
            showKeyboard()

        addShoppingListDialogVisible = !addShoppingListDialogVisible

        binding.toggleAddShoppingListButton.setImageDrawable(
            getDrawable(
                if (addShoppingListDialogVisible) R.drawable.ic_round_close_24px
                else R.drawable.ic_round_add_24px
            )
        )
    }

    private fun addShoppingListViewToList(shoppingList: ShoppingList) {
        val shoppingListView = ShoppingListView(this)
        shoppingListView.bind(shoppingList)

        binding.shoppingListsList.addView(shoppingListView)
    }

    private fun animateInputBarWidth(targetWidth: Int) {
        val anim = ValueAnimator.ofInt(binding.addShoppingListContainer.measuredWidth, targetWidth)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = binding.addShoppingListContainer.layoutParams
            layoutParams.width = `val`
            binding.addShoppingListContainer.layoutParams = layoutParams
        }
        anim.duration = 160
        anim.interpolator = DecelerateInterpolator()
        anim.start()
    }
}
