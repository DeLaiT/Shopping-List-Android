package pl.jergro.shopinglist

import android.app.Application
import io.realm.Realm
import timber.log.Timber

class ShoppingListApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Timber.plant(Timber.DebugTree())
    }
}