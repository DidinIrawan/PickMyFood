package com.project.pickmyfood.container

import com.project.pickmyfood.config.RetrofitBuilder
import com.project.pickmyfood.data.checkout.CheckOutApi
import com.project.pickmyfood.data.order.OrderAPI
import com.project.pickmyfood.data.payment.PaymentAPI
import com.project.pickmyfood.data.product.ProductAPI
import com.project.pickmyfood.data.store.StoreAPI
import com.project.pickmyfood.data.user.api.UserLoginAPI
import com.project.pickmyfood.data.user.api.UserRegisterAPI
import com.project.pickmyfood.data.wallet.WalletAPI
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

    @Provides
    fun provideProductAPI(): ProductAPI {
        return RetrofitBuilder.createRetrofit().create(ProductAPI::class.java)
    }
    @Provides
    fun provideCheckOutAPI(): CheckOutApi {
        return RetrofitBuilder.createRetrofit().create(CheckOutApi::class.java)
    }

    @Provides
    fun provideOrderAPI(): OrderAPI {
        return RetrofitBuilder.createRetrofit().create(OrderAPI::class.java)
    }

    @Provides
    fun provideWalletAPI(): WalletAPI {
        return RetrofitBuilder.createRetrofit().create(WalletAPI::class.java)
    }

    @Provides
    fun providePaymentAPI(): PaymentAPI {
        return RetrofitBuilder.createRetrofit().create(PaymentAPI::class.java)
    }
}