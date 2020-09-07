package com.project.pickmyfood.data.user.api

import com.project.pickmyfood.data.user.model.UserRegisterModel
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterAPI {
    @POST("user/register")
    fun registerNewUser(@Body userRegisterModel: UserRegisterModel): Call<Wrapper>
}