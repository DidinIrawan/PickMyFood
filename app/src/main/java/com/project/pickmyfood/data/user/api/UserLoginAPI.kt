package com.project.pickmyfood.data.user.api

import com.project.pickmyfood.data.user.model.UserLoginModel
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginAPI {
    @POST("user/login")
    fun loginUser(@Body userLoginModel: UserLoginModel): Call<Wrapper>
}