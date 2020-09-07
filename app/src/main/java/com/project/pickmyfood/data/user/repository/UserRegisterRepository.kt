package com.project.pickmyfood.data.user.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.data.user.api.UserRegisterAPI
import com.project.pickmyfood.data.user.model.UserRegisterModel
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRegisterRepository @Inject constructor(val userRegisterAPI: UserRegisterAPI){
    var userRegisterResponse = MutableLiveData<Wrapper>()

    fun registerNewUser(userRegisterModel: UserRegisterModel){
        userRegisterAPI.registerNewUser(userRegisterModel).enqueue(object : Callback<Wrapper> {
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val response = response.body()
                println("ini response ${response}")

                val stringResponse = Gson().toJson(response)
                val userRegisterResponseObject =
                    Gson().fromJson<Wrapper>(stringResponse, Wrapper::class.java)
                userRegisterResponse.value = userRegisterResponseObject

                println("User Register ${userRegisterResponse.value.toString()}")
            }
        })
    }
}