package pl.jergro.shopinglist.models

import io.realm.RealmList
import io.realm.RealmObject

open class ShoppingList(var name: String, var products: RealmList<Product>) : RealmObject() {
    constructor() : this("undefined", RealmList())
}