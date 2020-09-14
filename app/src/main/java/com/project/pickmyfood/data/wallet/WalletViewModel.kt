package com.project.pickmyfood.data.wallet

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class WalletViewModel @Inject constructor(val walletRepository: WalletRepository) {
    val wallet: MutableLiveData<Wallet> = walletRepository.wallet

    fun getWalletByID(id: String) {
        println("masuk view model wallet")
        walletRepository.getWalletByID(id)
    }

    fun topUpWallet(topUpWallet: TopUpWallet, id: String) {
        println("masuk view model top up wallet")
        walletRepository.topUpWallet(topUpWallet, id)
    }
}