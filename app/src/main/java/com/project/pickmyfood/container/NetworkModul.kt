package com.project.pickmyfood.container

import com.project.pickmyfood.config.RetrofitBuilder
import com.project.pickmyfood.data.store.StoreAPI
import com.project.pickmyfood.data.user.api.UserLoginAPI
import com.project.pickmyfood.data.user.api.UserRegisterAPI
import dagger.Module
import dagger.Provides

@Module
class NetworkModul {
    @Provides
    fun providerStoreAPI(): StoreAPI {
        return RetrofitBuilder.createRetrofit().create(StoreAPI::class.java)
    }
    @Provides
    fun provideUserLoginAPI(): UserLoginAPI {
        return RetrofitBuilder.createRetrofit().create(UserLoginAPI::class.java)
    }
    @Provides
    fun provideUserRegisterAPI(): UserRegisterAPI {
        return RetrofitBuilder.createRetrofit().create(UserRegisterAPI::class.java)
    }

}