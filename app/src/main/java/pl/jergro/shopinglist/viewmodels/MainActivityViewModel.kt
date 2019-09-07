package pl.jergro.shopinglist.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.RealmList
import pl.jergro.shopinglist.R
import pl.jergro.shopinglist.models.ShoppingList
import pl.jergro.shopinglist.models.ShoppingListOptionsItemConfiguration
import timber.log.Timber

class MainActivityViewModel : ViewModel() {
    val shoppingListSelected = MutableLiveData<ShoppingList>()
    private val _shoppingListsObservable = MutableLiveData<ArrayList<ShoppingList>>()
    private val _realm = Realm.getDefaultInstance()
    private val _shopOptions = MutableLiveData<List<ShoppingListOptionsItemConfiguration>>()
    private val _msgFeedback = MutableLiveData<String>()

    fun shoppingOptions(context: Context) {
        val shopOptions = listOf(
            ShoppingListOptionsItemConfiguration(
                1,
                R.drawable.ic_restore,
                context.getString(R.string.resetProducts),
                R.color.md_amber_A400
            ),
            ShoppingListOptionsItemConfiguration(
                2,
                R.drawable.ic_round_delete_forever_24px,
                context.getString(R.string.deleteForever),
                R.color.md_red_A400
            )
        )
        _shopOptions.postValue(shopOptions)
    }

    fun shoppingListExistsWithName(name: String): Boolean {
        val foundShoppingList =
            _realm.where(ShoppingList::class.java).equalTo("name", name).findFirst()

        return foundShoppingList != null
    }

    fun createShoppingListWithName(name: String) {
        val shoppingList = ShoppingList(name, RealmList())

        _realm.executeTransaction {
            _realm.copyToRealm(shoppingList)
        }

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

    fun shareShoppingList(shoppingList: ShoppingList) {

    }

    fun resetShoppingList(shopList: ShoppingList) {
        _realm.executeTransaction {
            shopList.products
                .filter { it.done }
                .map { it.done = !it.done }
        }
        _msgFeedback.postValue("Shopping list has been reseted")
        syncShoppingListsWithDatabase()
    }

    fun deleteShoppingList(shopList: ShoppingList) {
        _realm.executeTransaction {
            val result = it.where(ShoppingList::class.java).equalTo("name", shopList.name).findAll()
            result.deleteAllFromRealm()
        }
        syncShoppingListsWithDatabase()
        _msgFeedback.postValue("Shopping list has been deleted")
    }


    val shopListOptionsItemConfiguration: LiveData<List<ShoppingListOptionsItemConfiguration>>
        get() = _shopOptions

    val shoppingListSel: LiveData<ShoppingList>
        get() = shoppingListSelected

    val shoppingListsObservable: LiveData<ArrayList<ShoppingList>>
        get() = _shoppingListsObservable

    val msgFeedback: LiveData<String>
        get() = _msgFeedback
}