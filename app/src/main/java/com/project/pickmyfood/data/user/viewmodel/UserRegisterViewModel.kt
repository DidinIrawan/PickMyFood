package com.project.pickmyfood.data.user.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pickmyfood.data.user.model.UserRegisterModel
import com.project.pickmyfood.data.user.repository.UserRegisterRepository
import javax.inject.Inject

class UserRegisterViewModel @Inject constructor(var userRegisterRepository: UserRegisterRepository): ViewModel(){
    var userRegisterResponse = userRegisterRepository.userRegisterResponse
    fun registerNewUser(userRegisterModel: UserRegisterModel) {
        userRegisterRepository.registerNewUser(userRegisterModel)
    }
}