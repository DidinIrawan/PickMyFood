package com.project.pickmyfood.data.pick

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.project.pickmyfood.data.profil.KeyProfil
import com.project.pickmyfood.utils.Wrapper
import javax.inject.Inject

class PickViewModel @Inject constructor(var pickRepository: PickRepository) {
    val statusCode : MutableLiveData<Wrapper>? = pickRepository.responseStatusCode
    fun pickMyFood(storeID:String,amount:String,orderID:String,userID:String){
        pickRepository.pickMyFood(storeID, amount, orderID, userID)
        println("MASUK VIEWMODEL")
    }
}