package com.project.pickmyfood.data.store

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class StoreRepository @Inject constructor(val storeAPI: StoreAPI) {
    var storeList: MutableLiveData<List<KeyStore>> = MutableLiveData()

    fun getAllStore() {
        storeAPI.getAllStore().enqueue(object : Callback<Wrapper> {
            override fun onResponse(
                call: Call<Wrapper>,
                response: Response<Wrapper>
            ) {
                val responseData = response.body()
                val res = responseData?.data
                val listOfStore: Type = object : TypeToken<List<KeyStore>?>() {}.type
                val gson = Gson()
                val outputRestoList: List<KeyStore> = gson.fromJson(gson.toJson(res), listOfStore)
                storeList.value = outputRestoList
            }

            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
    }
}
