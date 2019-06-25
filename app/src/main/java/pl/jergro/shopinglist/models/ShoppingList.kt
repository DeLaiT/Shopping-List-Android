package pl.jergro.shopinglist.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ShoppingList(@PrimaryKey var name: String, var products: RealmList<Product>) : RealmObject() {
    constructor() : this("undefined", RealmList())
}