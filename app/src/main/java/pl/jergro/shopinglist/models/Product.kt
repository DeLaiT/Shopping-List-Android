package pl.jergro.shopinglist.models

import io.realm.RealmObject

open class Product(
    var shoppingList: ShoppingList,
    var name: String,
    var done: Boolean
) : RealmObject() {

}