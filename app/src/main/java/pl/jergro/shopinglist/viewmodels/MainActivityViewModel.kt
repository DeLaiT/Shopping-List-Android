package pl.jergro.shopinglist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import pl.jergro.shopinglist.models.ShoppingList
import timber.log.Timber

class MainActivityViewModel : ViewModel() {
    val shoppingListsObservable = MutableLiveData<ArrayList<ShoppingList>>()

    private val realm = Realm.getDefaultInstance()


    fun shoppingListExistsWithName(name: String): Boolean {
        val foundShoppingList = realm.where(ShoppingList::class.java).equalTo("name", name).findFirst()

        return foundShoppingList != null
    }

    fun createShoppingListWithName(name: String) {
        val shoppingList = ShoppingList(name)

        realm.beginTransaction()
        realm.copyToRealm(shoppingList)
        realm.commitTransaction()

        syncShoppingListsWithDatabase()
        Timber.d("Created shopping list with name: $name")
    }

    fun loadShoppingLists() {
        syncShoppingListsWithDatabase()
    }

    private fun syncShoppingListsWithDatabase() {
        val shoppingListResults = realm.where(ShoppingList::class.java).findAll()
        val shoppingLists = ArrayList<ShoppingList>()

        shoppingListResults.forEach {
            shoppingLists.add(it)
        }

        shoppingListsObservable.postValue(shoppingLists)
    }
}