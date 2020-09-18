package com.project.pickmyfood.data.user.api

import com.project.pickmyfood.data.user.model.UserEditModel
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserEditAPI {
    @PUT("user/changeProfile/{id}")
    fun editUser (@Path("id") id: String,@Body userEditModel : UserEditModel): Call<Wrapper>
}