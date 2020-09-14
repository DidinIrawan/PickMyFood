package com.project.pickmyfood.data.wallet

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class WalletViewModel @Inject constructor(var walletRepository: WalletRepository) {
    val walletUser : MutableLiveData<KeyWallet> = walletRepository.userWallet

    fun getUserWallet(id:String){
        println("MASUK VIEW MODEL")
        walletRepository.getUserWallet(id)
    }
}