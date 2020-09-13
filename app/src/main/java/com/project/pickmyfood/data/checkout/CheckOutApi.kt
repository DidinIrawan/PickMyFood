package com.project.pickmyfood.data.checkout

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CheckOutApi{
    @POST("order/add")
    fun checkOutOrder(@Body checkOutViewModel: CheckOut): Call<Wrapper>

}