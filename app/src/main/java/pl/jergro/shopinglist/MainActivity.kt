package pl.jergro.shopinglist

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pl.jergro.shopinglist.databinding.ActivityMainBinding
import pl.jergro.shopinglist.utils.dp
import pl.jergro.shopinglist.utils.hideKeyboard
import pl.jergro.shopinglist.utils.showKeyboard


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupListeners()
    }

    private fun setupListeners() {
        binding.addShoppingListButton.setOnClickListener {
            val targetWidth = binding.root.width - 16.dp(this) * 2

            animateInputBarWidth(targetWidth)
            binding.addShoppingListEt.requestFocus()
            showKeyboard()
        }
        binding.closeAddShoppingListDialog.setOnClickListener {
            val targetWidth = 42.dp(this)

            animateInputBarWidth(targetWidth)
            hideKeyboard()
        }
    }

    private fun animateInputBarWidth(targetWidth: Int) {
        val anim = ValueAnimator.ofInt(binding.searchContainer.measuredWidth, targetWidth)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = binding.searchContainer.layoutParams
            layoutParams.width = `val`
            binding.searchContainer.layoutParams = layoutParams
        }
        anim.duration = 160
        anim.interpolator = DecelerateInterpolator()
        anim.start()
    }
}
