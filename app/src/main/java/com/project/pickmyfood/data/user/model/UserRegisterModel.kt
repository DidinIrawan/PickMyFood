package com.project.pickmyfood.data.user.model

class UserRegisterModel(
    var userFirstName: String = "default",
    var userLastName: String = "default",
    var userAddress: String = "default",
    var userPhone: String = "default",
    var userEmail: String = "default@gmail.com",
    var userImage:String= "https://res.cloudinary.com/itproject13/image/upload/v1599465140/defaultImg_tinocb.png",
    var userStatus: String ="Cobacoba",
    var auth: UserRegisterAuth
){}

class UserRegisterAuth(
    var username: String = "default",
    var password: String = "default"
){}
//anggas25@live.com ayomabargees123
//clever-cloud.com
//fun main(){
//    var user = UserRegisterModel("guss", "gusaja", "Mojo", "081245553",
//        "image.jpg","saya gus",userRegisterAuth = UserRegisterAuth("asasa","asasas")
//    )
//    println(user.userRegisterAuth.username.toString())
//}
