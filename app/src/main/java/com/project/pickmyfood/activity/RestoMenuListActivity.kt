package com.project.pickmyfood.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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