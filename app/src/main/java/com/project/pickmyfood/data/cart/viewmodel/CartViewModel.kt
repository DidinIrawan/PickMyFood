package com.project.pickmyfood.data.cart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pickmyfood.data.cart.classes.Cart

class CartViewModel : ViewModel(){
    val cartList = mutableListOf<Cart>()

    val cartLiveData: MutableLiveData<MutableList<Cart>> = MutableLiveData(cartList)

    fun addCartList(qty:String,productID:String,userID:String, price:String,note:String){
        cartList.add(
            Cart(
                qty,
                productID,
                userID,
                price,
                note
            )
        )
        cartLiveData.value = cartList
    }
    fun removeCart(position: Int){
        cartList.removeAt(position)
        cartLiveData.value = cartList
    }

}