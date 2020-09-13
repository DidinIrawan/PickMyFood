package com.project.pickmyfood.data.checkout

import com.project.pickmyfood.data.cart.classes.Cart

class CheckOut(
    val storeID:String = "default",
    val soldItems: List<Cart>
)
class CheckOutResponseDataModel(
    var orderID: String = "default",
    var orderCreated:String = "default",
    var storeID: String = "default",
    var soldItems: List<SoldItems>
)

class SoldItems(
    val productID:String="",
    val productName:String="",
    val Qty:String="",
    val price:String="",
    val note:String=""
)
