package com.project.pickmyfood.data.checkout

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CheckOutViewModel @Inject constructor(var checkOutRepository: CheckOutRepository): ViewModel(){
    var checkOutResponse = checkOutRepository.checkOutResponse
    var checkOutResponseData = checkOutRepository.checkOutResponseData
    fun checkOutOrder(checkOut: CheckOut){
        checkOutRepository.checkOutOrder(checkOut)
    }
}