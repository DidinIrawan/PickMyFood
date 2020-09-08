package com.project.pickmyfood.data.user.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.data.user.api.UserLoginAPI
import com.project.pickmyfood.data.user.model.UserLoginModel
import com.project.pickmyfood.data.user.model.UserLoginResponseDataModel
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserLoginRepository  @Inject constructor(val userLoginAPI: UserLoginAPI) {
    var userLoginResponse = MutableLiveData<Wrapper>()
    var userLoginResponseData = MutableLiveData<UserLoginResponseDataModel>()

    fun loginUser(userLoginModel: UserLoginModel) {
        userLoginAPI.loginUser(userLoginModel).enqueue(object : Callback<Wrapper> {
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val response = response.body()
                val stringResponse = Gson().toJson(response)
                val stringResponseData = Gson().toJson(response?.data)
                val userLoginResponseObject =
                    Gson().fromJson<Wrapper>(stringResponse, Wrapper::class.java)
                val userLoginResponseDataObject =
                    Gson().fromJson<UserLoginResponseDataModel>(stringResponseData,UserLoginResponseDataModel::class.java)
                userLoginResponse.value = userLoginResponseObject
                userLoginResponseData.value = userLoginResponseDataObject

            }
        })
    }
}