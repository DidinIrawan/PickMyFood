package com.project.pickmyfood.data.rating

import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RatingAPI {
    @POST("rating/post")
    fun ratingStore(@Body rating: Rating): Call<Wrapper>
}