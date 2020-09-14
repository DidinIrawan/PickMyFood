package com.project.pickmyfood.data.rating

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RatingRespository @Inject constructor(val ratingAPI: RatingAPI) {
    val ratingResponse = MutableLiveData<Wrapper>()

    fun ratingResto(rating: Rating) {
        ratingAPI.ratingStore(rating).enqueue(object : Callback<Wrapper> {
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val response = response.body()
                println("ini response ${response}")

                val stringResponse = Gson().toJson(response)
                val ratingResponseObject =
                    Gson().fromJson<Wrapper>(stringResponse, Wrapper::class.java)
                ratingResponse.value = ratingResponseObject

                println("User Register ${ratingResponse.value.toString()}")
            }

        })
    }
}