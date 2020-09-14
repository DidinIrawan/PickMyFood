package com.project.pickmyfood.data.wallet

import com.project.pickmyfood.utils.Wrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
interface WalletAPI {

    @GET("wallet/{id}")
    fun getUserWallet(@Path("id")id :String): Call<Wrapper>
}