package pl.jergro.shopinglist.models

import io.realm.RealmObject

open class Product(
    var name: String,
    var done: Boolean,
    var price: Double
) : RealmObject() {

}