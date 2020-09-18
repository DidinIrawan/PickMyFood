package com.project.pickmyfood.data.history

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class HistoryTransactionRepository @Inject constructor(val historyTransactionAPI: HistoryTransactionAPI) {
    val historyTransactionList: MutableLiveData<List<HistoryTransaction>> = MutableLiveData()

    fun getAllHistoryTransactionByID(id: String) {
        historyTransactionAPI.getAllHistoryTransactionByID(id).enqueue(object : Callback<Wrapper> {
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println(t.localizedMessage)
            }

            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val responseData = response.body()
                val res = responseData?.data
                val listOfHistoryTransaction: Type =
                    object : TypeToken<List<HistoryTransaction>?>() {}.type
                val gson = Gson()
                val outputHistoryTransactionList: List<HistoryTransaction> =
                    gson.fromJson(gson.toJson(res), listOfHistoryTransaction)
                historyTransactionList.value = outputHistoryTransactionList

                println("Ini Response ${res.toString()}")
            }

        })
    }
}