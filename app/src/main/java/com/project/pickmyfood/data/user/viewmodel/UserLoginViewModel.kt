package com.project.pickmyfood.data.user.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.project.pickmyfood.data.user.model.UserLoginModel
import com.project.pickmyfood.data.user.repository.UserLoginRepository
import javax.inject.Inject

class UserLoginViewModel @Inject constructor(var userLoginRepository: UserLoginRepository) :
    ViewModel() {
    var userLoginResponse = userLoginRepository.userLoginResponse
    var userLoginResponseData = userLoginRepository.userLoginResponseData
    fun loginUser(userLoginModel: UserLoginModel,context:Context) {
        userLoginRepository.loginUser(userLoginModel,context)
    }
}