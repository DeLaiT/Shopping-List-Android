package pl.jergro.shopinglist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.models.ShoppingList

class ShoppingListViewModel : ViewModel() {
    val productsObservable = MutableLiveData<List<Product>>()
    private val realm by lazy { Realm.getDefaultInstance() }
    private lateinit var selectedShoppingList: ShoppingList

    fun loadShoppingListByName(shoppingListName: String) {
        selectedShoppingList = realm.where(ShoppingList::class.java).equalTo("name", shoppingListName).findFirst()!!
        selectedShoppingList.products.addChangeListener { products ->
            productsObservable.postValue(products)
        }
    }

    fun addProductToSelectedShoppingList(product: Product) {
        realm.executeTransaction {
            selectedShoppingList.products.add(product)
        }
    }
}