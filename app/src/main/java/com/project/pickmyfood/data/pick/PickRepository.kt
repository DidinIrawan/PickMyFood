package com.project.pickmyfood.data.pick


import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response

class PickRepository @Inject constructor(val pickAPI: PickAPI){
   var responseStatusCode : MutableLiveData<Wrapper> =  MutableLiveData()
    fun pickMyFood(storeID:String,amount:String,orderID:String,userID:String){
        pickAPI.pickMyFood(storeID,amount, orderID, userID).enqueue(object : Callback<Wrapper>{
            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                if (response.body()?.message == "Success"){
                    val response = response.body()
                    val res = response
                    val gson = Gson()
                    val stringResponse = gson.toJson(res)
                    val statusCodeObject = gson.fromJson<Wrapper>(stringResponse,Wrapper::class.java)
                    responseStatusCode.value = statusCodeObject
                    println("INI STATUS sukses ${responseStatusCode.value}")
                } else {
                    println("INI RESPON BODY KALO SALAH ${response.body()?.message}")
                    val statusCodeObject =Wrapper("", "", "")
                    responseStatusCode.value = statusCodeObject
                    println("INI STATUS bad ${responseStatusCode.value}")
                }
//
                println("Masuk Repo Pick")
            }

            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println(t.localizedMessage)
                println("Masuk Repo Pick onfailure")

            }

        })
    }
}