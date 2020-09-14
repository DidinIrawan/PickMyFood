package com.project.pickmyfood.data.profil

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfilAPI {
    @GET("user/{id}")
    fun getUserProfil(@Path("id") id: String): Call<Wrapper>
    
}