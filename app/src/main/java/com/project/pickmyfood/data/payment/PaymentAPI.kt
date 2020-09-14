package com.project.pickmyfood.data.payment

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentAPI {
    @POST("payment")
    fun paymentOrder(@Body payment: Payment): Call<Wrapper>
}