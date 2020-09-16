package com.project.pickmyfood.data.orderlist

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderListAPI {
    @GET("transactions/user/{id}")
    fun getOrderListByID(@Path("id") id: String): Call<Wrapper>
}