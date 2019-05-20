package pl.jergro.shopinglist.models

import io.realm.RealmObject

open class ShoppingList(var name: String) : RealmObject() {
    constructor() : this("undefined")
}