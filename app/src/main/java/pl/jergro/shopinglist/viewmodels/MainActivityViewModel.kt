package pl.jergro.shopinglist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.RealmList
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.models.ShoppingOptions
import timber.log.Timber

class MainActivityViewModel : ViewModel() {
    val shoppingListSelected = MutableLiveData<ShoppingList>()
    private val _shoppingListsObservable = MutableLiveData<ArrayList<ShoppingList>>()
    private val realm = Realm.getDefaultInstance()
    private val _shopOptions = MutableLiveData<List<ShoppingOptions>>()

    fun shoppingOptions() {
        val shop = listOf(
            ShoppingOptions(0, R.drawable.ic_share, "Share", R.color.md_grey_900),
            ShoppingOptions(1, R.drawable.ic_restore, "Reset Products", R.color.md_yellow_A700),
            ShoppingOptions(2, R.drawable.ic_round_delete_forever_24px, "Delete forever", R.color.md_red_900)
        )
        _shopOptions.postValue(shop)
    }

    fun shoppingListExistsWithName(name: String): Boolean {
        val foundShoppingList = realm.where(ShoppingList::class.java).equalTo("name", name).findFirst()

        return foundShoppingList != null
    }

    fun createShoppingListWithName(name: String) {
        val shoppingList = ShoppingList(name, RealmList())

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

        _shoppingListsObservable.postValue(shoppingLists)
    }

    fun shareShoppingList(shopList: ShoppingList) {

    }

    fun resetShoppingList(shopList: ShoppingList) {

    }

    fun deleteShoppingList(shopList: ShoppingList) {
        realm.executeTransaction {
            val result = it.where(ShoppingList::class.java).equalTo("name", shopList.name).findAll()
            result.deleteAllFromRealm()
        }
        syncShoppingListsWithDatabase()
    }


    val shopOptions: LiveData<List<ShoppingOptions>>
        get() = _shopOptions

    val shoppingListSel: LiveData<ShoppingList>
        get() = shoppingListSelected

    val shoppingListsObservable: LiveData<ArrayList<ShoppingList>>
        get() = _shoppingListsObservable
}