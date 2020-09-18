package com.project.pickmyfood.data.history

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class HistoryTransactionViewModel @Inject constructor(val historyTransactionRepository: HistoryTransactionRepository) {
    val historyTransactionList: MutableLiveData<List<HistoryTransaction>> =
        historyTransactionRepository.historyTransactionList

    fun getAllHistoryTransactionByID(id: String) {
        println("masuk view model HistoryList")
        historyTransactionRepository.getAllHistoryTransactionByID(id)
    }
}