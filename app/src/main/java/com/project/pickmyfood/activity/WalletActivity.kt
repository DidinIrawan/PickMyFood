package com.project.pickmyfood.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.pickmyfood.R

class WalletActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }
}