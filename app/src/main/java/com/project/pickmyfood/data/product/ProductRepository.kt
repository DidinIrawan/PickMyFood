package com.project.pickmyfood.data.product

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class ProductRepository @Inject constructor(val productAPI: ProductAPI){
    var productList: MutableLiveData<List<Product>> = MutableLiveData()

    fun getAllProductByIdStore(id:String){
        productAPI.getAllProductByIdStore(id).enqueue(object : Callback<Wrapper>{
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val responseData = response.body()
                val res = responseData?.data
                val listOfStore: Type = object : TypeToken<List<Product>?>() {}.type
                val gson = Gson()
                val outputProductList: List<Product> = gson.fromJson(gson.toJson(res), listOfStore)
                productList.value = outputProductList
                println("Ini Response ${responseData.toString()}")
            }

        })
    }
}