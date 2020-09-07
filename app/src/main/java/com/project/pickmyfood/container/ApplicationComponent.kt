package com.project.pickmyfood.container

import com.project.pickmyfood.screens.login.LoginFragment
import com.project.pickmyfood.screens.signup.SignUpFragment
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(userLoginFragment: LoginFragment)
    fun inject(signUpFragment: SignUpFragment)
}