package pl.jergro.shopinglist.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BottomSheetDialog(context: Context) : BottomSheetDialog(context) {
    abstract fun getView(): View
    abstract fun onCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(getView())
        onCreated()
    }
}