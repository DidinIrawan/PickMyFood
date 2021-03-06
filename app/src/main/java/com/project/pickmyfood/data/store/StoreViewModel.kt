package com.project.pickmyfood.data.store

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class StoreViewModel @Inject constructor(var storeRepository: StoreRepository) {
    val store: MutableLiveData<List<KeyStore>>? = storeRepository.storeList
    fun getAllStore(keyword: String) {
        println("masuk view model")
        storeRepository.getAllStore(keyword)
    }

    fun getStore(id: String) {
        storeRepository.getStore(id)
    }
}