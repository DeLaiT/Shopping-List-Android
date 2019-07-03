package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pl.jergro.shopinglist.databinding.DialogShoppingOptionsBinding
import pl.jergro.shopinglist.models.ShoppingOptions
import pl.jergro.shopinglist.ui.adapters.ShoppingOptionsAdapter
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel

class OptionsShoppingDialog(
    private val viewModel: MainActivityViewModel,
    private val lifecycleOwner: LifecycleOwner,
    context: Context
) : BottomSheetDialog(context), ShoppingOptionsAdapter.Listener {

    private val shopAdp = ShoppingOptionsAdapter(ArrayList(), this)

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
        viewModel.shopOptions.observe(lifecycleOwner, Observer {
            shopAdp.uptData(it)
        })
    }

    override fun onOptionItemClicked(shoppingOptions: ShoppingOptions) {

    }
}