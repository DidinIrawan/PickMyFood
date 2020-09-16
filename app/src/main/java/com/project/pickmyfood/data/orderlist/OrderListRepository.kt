package com.project.pickmyfood.data.orderlist

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class OrderListRepository @Inject constructor(val orderListAPI: OrderListAPI) {
    val orderLists: MutableLiveData<List<OrderList>> = MutableLiveData()

    fun getAllOrderListByID(id: String) {
        orderListAPI.getOrderListByID(id).enqueue(object : Callback<Wrapper> {
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val responseData = response.body()
                val res = responseData?.data
                val listOfStore: Type = object : TypeToken<List<OrderList>?>() {}.type
                val gson = Gson()
                val outputOrderList: List<OrderList> = gson.fromJson(gson.toJson(res), listOfStore)
                orderLists.value = outputOrderList
                println("Ini Response ${responseData.toString()}")
            }

        })
    }
}