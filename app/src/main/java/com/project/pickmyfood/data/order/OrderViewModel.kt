package com.project.pickmyfood.data.order

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class OrderViewModel @Inject constructor(val orderRepository: OrderRepository){
    val order: MutableLiveData<Order>? = orderRepository.orderList
    fun getOrderByID(id:String){
        println("masuk view model order")
        orderRepository.getOrderByID(id)
    }
}