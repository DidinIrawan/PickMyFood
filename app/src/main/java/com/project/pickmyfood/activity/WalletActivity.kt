package com.project.pickmyfood.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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