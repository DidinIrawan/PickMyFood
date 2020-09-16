package com.project.pickmyfood.data.orderlist

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class OrderListViewModel @Inject constructor(val orderListRepository: OrderListRepository) {
    val orderList: MutableLiveData<List<OrderList>> = orderListRepository.orderLists

    fun getAllOrderListByID(id: String) {
        println("masuk view model orderList")
        orderListRepository.getAllOrderListByID(id)
    }
}