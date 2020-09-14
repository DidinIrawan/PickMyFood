package com.project.pickmyfood.data.payment

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PaymentViewModel @Inject constructor(var paymentRepository: PaymentRepository) : ViewModel() {
    var paymentResponse = paymentRepository.paymentResponse
    fun paymentOrder(payment: Payment) {
        paymentRepository.paymentOrder(payment)
    }
}