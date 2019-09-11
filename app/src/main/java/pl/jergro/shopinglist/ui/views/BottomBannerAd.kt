package pl.jergro.shopinglist.ui.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import pl.jergro.shopinglist.R

class BottomBannerAd(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private lateinit var adView: AdView

    init {
        addReservedSpaceTextView()
        addAdView()
        loadAds()
    }

    private fun addReservedSpaceTextView() {
        val textView = TextView(context)
        textView.setText(R.string.adReservedSpace)
        val params = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            gravity = Gravity.CENTER
        }

        textView.layoutParams = params
        textView.setTextColor(Color.parseColor("#ff888888"))

        addView(textView)
    }

    private fun addAdView() {
        val adParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        adView = AdView(context).apply {
            adSize = AdSize.BANNER
            adUnitId = context.getString(R.string.admob_bottom_unit_id)
            layoutParams = adParams
        }

        addView(adView)
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().addTestDevice("CCFEBCA3319D228F3365470FD5424D98").build()

        adView.loadAd(adRequest)
    }
}