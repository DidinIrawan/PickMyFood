package com.project.pickmyfood.container

import android.app.Application


class AppPickMyFood : Application() {
    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.create()
}