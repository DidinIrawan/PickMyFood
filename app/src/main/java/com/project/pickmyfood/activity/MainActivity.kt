package com.project.pickmyfood.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.pickmyfood.R
import com.project.pickmyfood.container.AppPickMyFood

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as AppPickMyFood).applicationComponent.inject(this)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }
}