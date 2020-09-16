package com.project.pickmyfood.data.orderlist

//http://localhost:8000/transactions/user/fa4e31bc-2898-464d-b79f-995c3c28ab40
//{
//    "transactionID": "c7c31a08-80c3-4575-94fc-110a65930331",
//    "orderID": "016fce52-6221-4585-9d81-4c100e54f333",
//    "userID": "fa4e31bc-2898-464d-b79f-995c3c28ab40",
//    "userFirstName": "mesut",
//    "amount": "8000",
//    "transactionCreated": "2020-09-14 17:12:02",
//    "transactionStatus": "Unpick"
//},

class OrderList(
    var transactionID: String = "",
    var orderID: String = "",
    var userID: String = "",
    var userFirstName: String = "",
    var amount: String = "",
    var transactionCreated: String = "",
    var transactionStatus: String = ""
)