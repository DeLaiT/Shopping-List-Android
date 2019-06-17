package pl.jergro.shopinglist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ViewAddShoppingListBinding
import pl.jergro.shopinglist.viewmodels.MainActivityViewModel

@SuppressLint("ViewConstructor")
class AddShoppingListView(val viewmodel: MainActivityViewModel, context: Context) : FrameLayout(context) {
    val binding = DataBindingUtil.inflate<ViewAddShoppingListBinding>(
        LayoutInflater.from(context),
        R.layout.view_add_shopping_list,
        this,
        true
    )


}