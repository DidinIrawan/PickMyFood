package com.project.pickmyfood.data.wallet

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class WalletRepository @Inject constructor(val walletAPI: WalletAPI) {

    var wallet: MutableLiveData<Wallet> = MutableLiveData()

    fun getWalletByID(id: String) {
        walletAPI.getWalletByID(id).enqueue(object : Callback<Wrapper> {

            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val responseData = response.body()
                val res = responseData?.data
                val walletObject: Type = object : TypeToken<Wallet>() {}.type
                val gson = Gson()
                val outputWallet = gson.fromJson<Wallet>(gson.toJson(res), walletObject)
                wallet.value = outputWallet
                println("Ini Response ${responseData.toString()}")
                println("Ini Response ${res.toString()}")
                println("Ini Response ${wallet.value.toString()}")

            }

        })
    }

    fun topUpWallet(topUpWallet: TopUpWallet, id: String) {
        walletAPI.topUpWallet(topUpWallet, id).enqueue(object : Callback<Wrapper> {
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println("FAILED TO CONNECT API")
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                println("MASUK RESPONSE")
                println(response.isSuccessful)
            }

        })

    }
}