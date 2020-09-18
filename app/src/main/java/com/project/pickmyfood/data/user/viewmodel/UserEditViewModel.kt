package com.project.pickmyfood.data.user.viewmodel

import androidx.lifecycle.ViewModel
import com.project.pickmyfood.data.user.model.UserEditModel
import com.project.pickmyfood.data.user.repository.UserEditRepository
import javax.inject.Inject

class UserEditViewModel @Inject constructor(var userEditRepository : UserEditRepository): ViewModel(){
    var userEditResponse = userEditRepository.userEditResponse
    fun editUser(id :String,userEditModel: UserEditModel){
        userEditRepository.editUser(id,userEditModel)
        println("MASUK VIEW MODEL EDIT PROFIL")
        println("ID EDIT PROFIL $id")
        println("USER EDIT MODEL ${userEditModel.userFirstName}")
    }
}
