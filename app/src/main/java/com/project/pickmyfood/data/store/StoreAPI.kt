package com.project.pickmyfood.data.store

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.GET

interface StoreAPI {
    @GET("stores")
    fun getAllStore(): Call<Wrapper>
}
