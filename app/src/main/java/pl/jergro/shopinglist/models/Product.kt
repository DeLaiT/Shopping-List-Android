package pl.jergro.shopinglist.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Product(
    @PrimaryKey
    var id: String,
    var name: String,
    var done: Boolean,
    var price: Double,
    var index: Int
) : RealmObject() {
    constructor() : this("", "undefined", false, 0.0, 0)
}