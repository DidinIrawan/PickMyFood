package com.project.pickmyfood.container

import com.project.pickmyfood.activity.MainActivity
import com.project.pickmyfood.screens.list.RestoListFragment
import dagger.Component

@Component(modules = [NetworkModul::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(storeList: RestoListFragment)
}