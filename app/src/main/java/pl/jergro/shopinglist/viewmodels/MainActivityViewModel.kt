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
    private val _realm = Realm.getDefaultInstance()
    private val _shopOptions = MutableLiveData<List<ShoppingOptions>>()
    private val _msgFeedback = MutableLiveData<String>()

    fun shoppingOptions() {
        val shopOptions = listOf(
            ShoppingOptions(0, R.drawable.ic_share, "Share", R.color.md_grey_900),
            ShoppingOptions(1, R.drawable.ic_restore, "Reset products", R.color.md_yellow_A700),
            ShoppingOptions(2, R.drawable.ic_round_delete_forever_24px, "Delete forever", R.color.md_red_900)
        )
        _shopOptions.postValue(shopOptions)
    }

    fun shoppingListExistsWithName(name: String): Boolean {
        val foundShoppingList = _realm.where(ShoppingList::class.java).equalTo("name", name).findFirst()

        return foundShoppingList != null
    }

    fun createShoppingListWithName(name: String) {
        val shoppingList = ShoppingList(name, RealmList())

        _realm.beginTransaction()
        _realm.copyToRealm(shoppingList)
        _realm.commitTransaction()

        syncShoppingListsWithDatabase()
        Timber.d("Created shopping list with name: $name")
    }

    fun loadShoppingLists() {
        syncShoppingListsWithDatabase()
    }

    private fun syncShoppingListsWithDatabase() {
        val shoppingListResults = _realm.where(ShoppingList::class.java).findAll()
        val shoppingLists = ArrayList<ShoppingList>()

        shoppingListResults.forEach {
            shoppingLists.add(it)
        }

        _shoppingListsObservable.postValue(shoppingLists)
    }

    fun shareShoppingList(shopList: ShoppingList) {

    }

    fun resetShoppingList(shopList: ShoppingList) {
        _realm.executeTransaction {
            shopList.products
                .filter { it.done }
                .map { it.done = !it.done }
        }
        _msgFeedback.postValue("Shopping list has been reseted")
    }

    fun deleteShoppingList(shopList: ShoppingList) {
        _realm.executeTransaction {
            val result = it.where(ShoppingList::class.java).equalTo("name", shopList.name).findAll()
            result.deleteAllFromRealm()
        }
        syncShoppingListsWithDatabase()
        _msgFeedback.postValue("Shopping list has been deleted")
    }


    val shopOptions: LiveData<List<ShoppingOptions>>
        get() = _shopOptions

    val shoppingListSel: LiveData<ShoppingList>
        get() = shoppingListSelected

    val shoppingListsObservable: LiveData<ArrayList<ShoppingList>>
        get() = _shoppingListsObservable

    val msgFeedback: LiveData<String>
        get() = _msgFeedback
}