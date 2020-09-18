package com.project.pickmyfood.data.history

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HistoryTransactionAPI {
    @GET("topUp/user/{id}")
    fun getAllHistoryTransactionByID(@Path("id") id: String): Call<Wrapper>
}