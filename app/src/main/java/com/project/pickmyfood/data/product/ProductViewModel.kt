package com.project.pickmyfood.data.product

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ProductViewModel @Inject constructor(val productRepository: ProductRepository){
    val product: MutableLiveData<List<Product>>? =  productRepository.productList

    fun getAllProductByIdStore(id: String){
        println("masuk view model product")
        productRepository.getAllProductByIdStore(id)
    }
}