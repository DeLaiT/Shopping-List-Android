package pl.jergro.shopinglist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import pl.jergro.shopinglist.models.Product
import pl.jergro.shopinglist.models.ShoppingList

class ShoppingListViewModel : ViewModel() {
    val productsList = MutableLiveData<List<Product>>()
    private val realm by lazy { Realm.getDefaultInstance() }
    private lateinit var selectedShoppingList: ShoppingList

    fun loadProductsByShoppingListName(shoppingListName: String) {
        selectedShoppingList =
            realm.where(ShoppingList::class.java).equalTo("name", shoppingListName).findFirst()!!

        productsList.postValue(selectedShoppingList.products)

        selectedShoppingList.products.addChangeListener { products ->
            productsList.postValue(products)
        }
    }

    fun addProductToSelectedShoppingList(product: Product) {
        realm.executeTransaction {
            selectedShoppingList.products.add(product)
        }
    }

    fun updateProduct(product: Product) {
        realm.executeTransaction {
            realm.insertOrUpdate(product)
        }
    }

    fun getProductsListForSharing(shoppingListName: String) : String{
        val productsStringBuilder = StringBuilder()

        productsList.value?.forEach {
            productsStringBuilder.append(" - ${it.name}\n")
        }

        return "Shopping list '${shoppingListName}'\n${productsStringBuilder}"
    }

    fun updateProductStatus(product: Product) {
        realm.executeTransaction { product.done = !product.done }
    }
}