package com.project.pickmyfood.data.order

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderAPI{

    @GET("order/{id}")
    fun getOrderByID(@Path("id")id:String): Call<Wrapper>

}