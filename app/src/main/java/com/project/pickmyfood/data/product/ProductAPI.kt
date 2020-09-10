package com.project.pickmyfood.data.product

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI{

    @GET("products/{id}")
    fun getAllProductByIdStore(@Path("id")id:String): Call<Wrapper>

}