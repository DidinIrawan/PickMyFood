package com.project.pickmyfood.data.product

class Product(
    var productID:String = "",
    var productName:String = "",
    var productStock:String = "",
    var productStatus:String = "",
    var productImage:String="",
    var productPrice: Price
)
class Price(
    var productPriceID: String = "",
    var price: String = ""
)