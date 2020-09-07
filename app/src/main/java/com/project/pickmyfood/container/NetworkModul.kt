package com.project.pickmyfood.container

import com.project.pickmyfood.config.RetrofitBuilder
import com.project.pickmyfood.data.store.StoreAPI
import dagger.Module
import dagger.Provides

@Module
class NetworkModul {
    @Provides
    fun providerStoreAPI(): StoreAPI {
        return RetrofitBuilder.createRetrofit().create(StoreAPI::class.java)
    }
}