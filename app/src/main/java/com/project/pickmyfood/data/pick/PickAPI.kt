package com.project.pickmyfood.data.pick

import com.project.pickmyfood.utils.Wrapper
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call

interface PickAPI {
    @POST ("transaction")
    fun pickMyFood (@Query("storeID")storeID:String,
                    @Query("amount")amount:String,
                    @Query("orderID")orderID:String,
                    @Query("userID")userID:String):Call<Wrapper>
}