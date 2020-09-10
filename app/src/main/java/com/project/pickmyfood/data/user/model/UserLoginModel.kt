package com.project.pickmyfood.data.user.model

class UserLoginModel(
    var username: String = "default",
    var password: String = "default"
)

class UserLoginResponseDataModel(
    var userID: String = "default",
    var userFirstName: String = "default",
    var userLastName: String = "default",
    var userAddress: String = "default",
    var userPhone: String = "default",
    var userPoin: String = "default",
    var userEmail: String = "default",
    var userImage: String = "default"
)