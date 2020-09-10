package com.project.pickmyfood.data.profil

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.project.pickmyfood.utils.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfilRepository @Inject constructor(val profilAPI: ProfilAPI) {
    var profilUser: MutableLiveData<KeyProfil> = MutableLiveData()

    fun getUserProfil(id: String) {

        profilAPI.getUserProfil(id).enqueue(object : Callback<Wrapper> {
            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                val responseData = response.body()
                val response = response.body()
                val gson = Gson()
                val stringResponse = gson.toJson(response)
                val profilObject = gson.fromJson<KeyProfil>(stringResponse, KeyProfil::class.java)
                profilUser.value = profilObject
            }

            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                println("gatot bos")
                println(t.localizedMessage)
            }

        })
    }
}