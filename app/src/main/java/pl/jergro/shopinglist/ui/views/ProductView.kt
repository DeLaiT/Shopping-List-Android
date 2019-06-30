package pl.jergro.shopinglist.ui.views

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import io.realm.Realm
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.databinding.ViewProductBinding
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.utils.runOnMainThread

class ProductView(context: Context) : ConstraintLayout(context) {
    val binding = DataBindingUtil.inflate<ViewProductBinding>(
        LayoutInflater.from(context),
        R.layout.view_product,
        this,
        true
    )
    val realm = Realm.getDefaultInstance()
    lateinit var product: Product

    init {
        binding.checkbox.setOnClickListener {
            realm.executeTransaction {
                product.done = !product.done

                runOnMainThread {
                    updateViewAccordingToData()

                }
            }
        }
    }

    fun bind(product: Product) {
        this.product = product

        binding.productNameText.text = product.name

        updateViewAccordingToData()
    }

    private fun updateViewAccordingToData() {
        binding.checkbox.setImageResource(
            if (product.done) R.drawable.ic_round_check_circle_outline_24px
            else R.drawable.ic_round_radio_button_unchecked_24px
        )

        binding.checkbox.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                if (product.done) R.color.md_blue_A400
                else R.color.md_grey_400
            )
        )

        binding.productNameText.setTextColor(
            (ContextCompat.getColor(
                context,
                if (product.done) R.color.md_grey_400
                else R.color.md_grey_900
            ))
        )
    }
}