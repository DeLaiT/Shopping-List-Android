package pl.jergro.shopinglist.models

import io.realm.RealmObject

open class ShoppingList(var name: String, val products: ArrayList<Product>) : RealmObject() {
    constructor() : this("undefined", ArrayList())
}