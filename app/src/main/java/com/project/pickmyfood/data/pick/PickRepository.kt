package com.project.pickmyfood.data.pick

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response

class PickRepository @Inject constructor(val pickAPI: PickAPI){
    fun pickMyFood(storeID:String,amount:String,orderID:String,userID:String){
        pickAPI.pickMyFood(storeID,amount, orderID, userID).enqueue(object : Callback<Wrapper>{
            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                println("Masuk Repo Pick")
            }

            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
    }
}