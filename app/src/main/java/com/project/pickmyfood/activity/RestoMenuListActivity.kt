package com.project.pickmyfood.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.pickmyfood.R

class RestoMenuListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resto_menu_list)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }
}