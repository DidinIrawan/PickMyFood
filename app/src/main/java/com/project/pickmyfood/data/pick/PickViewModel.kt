package com.project.pickmyfood.data.pick

import javax.inject.Inject

class PickViewModel @Inject constructor(var pickRepository: PickRepository) {
    fun pickMyFood(storeID:String,amount:String,orderID:String,userID:String){
        pickRepository.pickMyFood(storeID, amount, orderID, userID)
    }
}