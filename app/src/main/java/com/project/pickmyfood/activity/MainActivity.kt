package com.project.pickmyfood.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication

class MainActivity : AppCompatActivity() {
    val REQUEST_READ_STORAGE_PERMISSION = 201
    val REQUEST_OPEN_CAMERA_PERMISSION = 202
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApplication).applicationComponent.inject(this)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_READ_STORAGE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            secondCameraPermission()
        } else if (requestCode == REQUEST_READ_STORAGE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Mohon berikan perizinan", Toast.LENGTH_SHORT).show()
            firstStoragePermission()
        }
    }

    fun firstStoragePermission() {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), REQUEST_READ_STORAGE_PERMISSION
            )
        }
    }

    fun secondCameraPermission() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.CAMERA
                ), REQUEST_OPEN_CAMERA_PERMISSION
            )
        }
    }
}