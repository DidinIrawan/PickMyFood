package com.project.pickmyfood.container

import com.project.pickmyfood.config.RetrofitBuilder
import com.project.pickmyfood.data.user.api.UserLoginAPI
import com.project.pickmyfood.data.user.api.UserRegisterAPI
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideUserLoginAPI(): UserLoginAPI {
        return RetrofitBuilder.createRetrofit().create(UserLoginAPI::class.java)
    }
    @Provides
    fun provideUserRegisterAPI(): UserRegisterAPI {
        return RetrofitBuilder.createRetrofit().create(UserRegisterAPI::class.java)
    }
}