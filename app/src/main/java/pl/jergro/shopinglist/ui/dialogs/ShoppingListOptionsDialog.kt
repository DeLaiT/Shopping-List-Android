package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pl.jergro.shopinglist.databinding.DialogShoppingOptionsBinding
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.models.ShoppingListOptionsItemConfiguration
import pl.jergro.shopinglist.ui.adapters.ShoppingOptionsListAdapter
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel

class ShoppingListOptionsDialog(
    private val viewModel: MainActivityViewModel,
    private val lifecycleOwner: LifecycleOwner,
    context: Context
) : BottomSheetDialog(context), ShoppingOptionsListAdapter.Listener {

    private val shopAdp = ShoppingOptionsListAdapter(ArrayList(), this)
    private lateinit var _shoppingList: ShoppingList

    private val binding =
        DialogShoppingOptionsBinding.inflate(LayoutInflater.from(context), null, false)

    override fun getView(): View = binding.root

    override fun onCreated() {
        viewModel.shoppingOptions()
        observers()
        binding.recyclerOptions.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = shopAdp
        }
    }

    private fun observers() {
        viewModel.shopListOptionsItemConfiguration.observe(lifecycleOwner, Observer {
            shopAdp.uptData(it)
        })
        viewModel.shoppingListSel.observe(lifecycleOwner, Observer {
            _shoppingList = it
            binding.shoppingList = it
        })
        viewModel.msgFeedback.observe(lifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onOptionItemClicked(shoppingListOptionsItemConfiguration: ShoppingListOptionsItemConfiguration) {
        when (shoppingListOptionsItemConfiguration.id) {
            0 -> _shoppingList.let(viewModel::shareShoppingList)
            1 -> _shoppingList.let(viewModel::resetShoppingList)
            2 -> _shoppingList.let(viewModel::deleteShoppingList)
        }
        dismiss()
    }

}