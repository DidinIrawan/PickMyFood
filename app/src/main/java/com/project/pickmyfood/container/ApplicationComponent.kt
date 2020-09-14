package com.project.pickmyfood.container

import com.project.pickmyfood.screens.qrcode.QRcode
import com.project.pickmyfood.activity.MainActivity
import com.project.pickmyfood.activity.WalletActivity
import com.project.pickmyfood.screens.cart.CartFragment
import com.project.pickmyfood.screens.cart.ListCartFragment
import com.project.pickmyfood.screens.home.HomeFragment
import com.project.pickmyfood.screens.list.FoodListFragment
import com.project.pickmyfood.screens.list.FoodMenuListFragment
import com.project.pickmyfood.screens.list.RestoListFragment
import com.project.pickmyfood.screens.login.LoginFragment
import com.project.pickmyfood.screens.rating.RatingFragment
import com.project.pickmyfood.screens.signup.SignUpFragment
import com.project.pickmyfood.screens.wallet.TopUpFragment
import dagger.Component

@Component(modules = [NetworkModul::class])
interface ApplicationComponent {
    fun inject(userLoginFragment: LoginFragment)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(storeList: RestoListFragment)
    fun inject(foodListFragment: FoodListFragment)
    fun inject(foodMenuListFragment: FoodMenuListFragment)
    fun inject(walletActivity: WalletActivity)
    fun inject(topUpFragment: TopUpFragment)
//    fun inject(detailFoodFragment: DetailFoodFragment)
    fun inject(cartFragment: CartFragment)
    fun inject(listCartFragment: ListCartFragment)
    fun inject(ratingFragment: RatingFragment)
    fun inject(qRcode: QRcode)
    fun inject(homeFragment: HomeFragment)
}