package pl.jergro.shopinglist.models

import io.realm.RealmObject

open class ShoppingList(var title: String) : RealmObject() {
    constructor() : this("undefined")
}