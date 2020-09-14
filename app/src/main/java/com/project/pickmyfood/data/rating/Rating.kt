package com.project.pickmyfood.data.rating

//{
//    "storeID":"74c16924-796f-4777-a127-448717c0c3fd",
//    "userID":"8116c8a1-5f5b-47e7-b50a-ce33fa7db293",
//    "ratingValue":"5",
//    "ratingDescription":"Pelayanannya ramah"
//}
//http://localhost:8000/rating/post
class Rating(
    var storeID: String = "",
    var userID: String = "",
    var ratingValue: String = "",
    var ratingDescription: String = ""
)