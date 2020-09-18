package com.project.pickmyfood.data.user.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.data.user.api.UserEditAPI
import com.project.pickmyfood.data.user.model.UserEditModel
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserEditRepository @Inject constructor(val userEditAPI: UserEditAPI){
    var userEditResponse = MutableLiveData<Wrapper>()
    fun editUser (id :String,userEditModel: UserEditModel){

        userEditAPI.editUser(id,userEditModel).enqueue(object : Callback<Wrapper> {
            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
               val response = response.body()

                val gson= Gson()
                val stringResponse = gson.toJson(response)
                val editObject = gson.fromJson<Wrapper>(stringResponse,Wrapper::class.java)
userEditResponse.value = editObject
                println("User edit ${userEditResponse.value.toString()}")
            }

            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                t.printStackTrace()
                println("gatot bos")
            }

        })
    }
}