package com.project.pickmyfood.data.payment

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PaymentRepository @Inject constructor(val paymentAPI: PaymentAPI) {
    val paymentResponse = MutableLiveData<Wrapper>()

    fun paymentOrder(payment: Payment) {
        paymentAPI.paymentOrder(payment).enqueue(object : Callback<Wrapper> {
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val response = response.body()
                println("ini response ${response}")

                val stringResponse = Gson().toJson(response)
                val paymentResponseObject =
                    Gson().fromJson<Wrapper>(stringResponse, Wrapper::class.java)
                paymentResponse.value = paymentResponseObject

                println("User Register ${paymentResponse.value.toString()}")
            }

        })
    }
}