package com.project.pickmyfood.data.wallet

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WalletRepository @Inject constructor(val walletAPI: WalletAPI){
    var userWallet : MutableLiveData<KeyWallet> = MutableLiveData()


    fun getUserWallet(id:String) {

        walletAPI.getUserWallet(id).enqueue(object : Callback<Wrapper> {
            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val responseData = response.body()
                val res = responseData?.data
                val gson = Gson()
                val stringResponse = gson.toJson(res)
                val walletObject = gson.fromJson<KeyWallet>(stringResponse,KeyWallet::class.java)
                userWallet.value = walletObject

            }

            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println("cannot load data")
                println(t.localizedMessage)
            }

        })
    }
}