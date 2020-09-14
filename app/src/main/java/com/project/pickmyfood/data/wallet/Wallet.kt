package com.project.pickmyfood.data.wallet

class Wallet(
    var walletID: String = "",
    var userID: String = "",
    var amount: String = ""
)

class TopUpWallet(var topUpAmount: String = "")