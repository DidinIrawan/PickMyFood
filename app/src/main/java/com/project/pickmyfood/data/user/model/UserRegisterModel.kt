package com.project.pickmyfood.data.user.model

class UserRegisterModel(
    var userFirstName: String = "default",
    var userLastName: String = "default",
    var userAddress: String = "Mojoaja",
    var userPhone: String = "default",
    var userImage: String = "defaultUserPhoto.jpeg",
    var userStatus: String ="Cobacoba",
    var auth: UserRegisterAuth
){}

class UserRegisterAuth(
    var username: String = "default",
    var password: String = "default"
){}

//fun main(){
//    var user = UserRegisterModel("guss", "gusaja", "Mojo", "081245553",
//        "image.jpg","saya gus",userRegisterAuth = UserRegisterAuth("asasa","asasas")
//    )
//    println(user.userRegisterAuth.username.toString())
//}
