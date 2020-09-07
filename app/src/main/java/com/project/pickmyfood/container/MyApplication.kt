package com.project.pickmyfood.container

import android.app.Application

class MyApplication : Application(){
    val applicationComponent:ApplicationComponent = DaggerApplicationComponent.create()
}