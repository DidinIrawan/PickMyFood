package com.project.pickmyfood.data.checkout

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CheckOutRepository @Inject constructor(val checkOutApi: CheckOutApi){
    var checkOutResponse = MutableLiveData<Wrapper>()
    var checkOutResponseData = MutableLiveData<CheckOutResponseDataModel>()
    fun checkOutOrder(checkOut: CheckOut){
        checkOutApi.checkOutOrder(checkOut).enqueue(object : Callback<Wrapper>{
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                t.printStackTrace()
                println("Failed CheckOut")
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
//                val response = response.body()
//                val stringResponse = Gson().toJson(response)
//                val checkOutResponseObject =
//                    Gson().fromJson<Wrapper>(stringResponse, Wrapper::class.java)
//                checkOutResponse.value = checkOutResponseObject
                val response = response.body()
                val stringResponseData = Gson().toJson(response?.data)
                val checkOutResponseDataObject =
                    Gson().fromJson<CheckOutResponseDataModel>(
                        stringResponseData,
                        CheckOutResponseDataModel::class.java
                    )
                checkOutResponseData.value = checkOutResponseDataObject
                println("Response CheckOut $stringResponseData")
                println("Success CheckOut")

//                println(response.body().toString())
            }

        })
    }
}