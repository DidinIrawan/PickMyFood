package com.project.pickmyfood.data.store

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreAPI {
    @GET("stores")
    fun getAllStore(): Call<Wrapper>

    @GET("store/{id}")
    fun getStoreByID(@Path("id")id:String): Call<Wrapper>

}
