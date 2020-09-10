package com.project.pickmyfood.data.cart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pickmyfood.data.cart.classes.Cart

class CartViewModel : ViewModel(){
    val cartList = mutableListOf<Cart>()

    val cartLiveData: MutableLiveData<MutableList<Cart>> = MutableLiveData(cartList)

    fun addCartList(productID:String, quantity:Int,productPrice:String,note:String){
        cartList.add(
            Cart(
                productID,
                quantity
                ,productPrice
                ,note
            )
        )
        cartLiveData.value = cartList
    }
}