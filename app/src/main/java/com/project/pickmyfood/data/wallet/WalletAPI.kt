package com.project.pickmyfood.data.wallet

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WalletAPI {

    @GET("wallet/{id}")
    fun getWalletByID(@Path("id") id: String): Call<Wrapper>

    @POST("wallet/topUp/{id}")
    fun topUpWallet(@Body topUpWallet: TopUpWallet, @Path("id") id: String): Call<Wrapper>
}