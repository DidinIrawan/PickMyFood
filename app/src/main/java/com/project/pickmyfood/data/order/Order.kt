package com.project.pickmyfood.data.order



class Order(
    var orderID: String = "default",
    var orderCreated: String = "default",
    var storeID: String = "default",
    var soldItems: List<SoldItems>
)

class SoldItems(
    val productID: String = "",
    val productName: String = "",
    val Qty: String = "",
    val price: String = "",
    val note: String = ""
)