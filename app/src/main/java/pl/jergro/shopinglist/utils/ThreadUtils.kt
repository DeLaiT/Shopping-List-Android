package pl.jergro.shopinglist.utils

import android.os.Handler
import android.os.Looper

fun runOnMainThread(run: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        run()
    }
}