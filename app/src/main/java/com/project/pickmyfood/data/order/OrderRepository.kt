package com.project.pickmyfood.data.order

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class OrderRepository @Inject constructor(val orderAPI: OrderAPI){
    val orderList: MutableLiveData<Order> = MutableLiveData()
    val orderListData: MutableLiveData<List<SoldItems>> = MutableLiveData<List<SoldItems>>()
    fun getOrderByID(id:String){
        orderAPI.getOrderByID(id).enqueue(object :Callback<Wrapper>{
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val responseData = response.body()
                val res = responseData?.data
                val listOfOrder: Type = object : TypeToken<Order?>() {}.type
                val gson = Gson()
                val outputOrderList: Order = gson.fromJson(gson.toJson(res),listOfOrder)
                orderList.value = outputOrderList
                println("Response order : $res")

                orderListData.value = outputOrderList.soldItems


            }

        })
    }
}