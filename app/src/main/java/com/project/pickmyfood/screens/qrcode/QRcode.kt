package com.project.pickmyfood.screens.qrcode

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.zxing.Result
import com.project.pickmyfood.R
import com.project.pickmyfood.activity.HomeActivity
import com.project.pickmyfood.activity.MainActivity
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.pick.PickViewModel
import kotlinx.android.synthetic.main.activity_qr_code.*
import kotlinx.android.synthetic.main.activity_wallet.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import javax.inject.Inject

class QRcode : AppCompatActivity(), ZXingScannerView.ResultHandler, View.OnClickListener {

    @Inject
    lateinit var pickViewModel: PickViewModel
    private lateinit var mScannerView: ZXingScannerView
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        (applicationContext as MyApplication).applicationComponent.inject(this)
        initScannerView()
        initDefaultView()
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
        btnScan.setOnClickListener(this)
        btnDone.setOnClickListener(this)
    }

    private fun initScannerView() {

        mScannerView = ZXingScannerView(this)
        mScannerView.setAutoFocus(true)
        mScannerView.setResultHandler(this)
        frame_layout_camera.addView(mScannerView)
    }

    override fun onStart() {
        mScannerView.startCamera()
        doRequestPermission()
        super.onStart()
    }

    private fun doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }

    override fun onPause() {
        mScannerView.stopCamera()
        super.onPause()
    }

    private fun initDefaultView() {
        text_view_qr_code_value.text = " "
        btnDone.visibility = View.GONE
        btnScan.visibility = View.GONE
        alignQR.visibility = View.VISIBLE
        frame_layout_camera.visibility = View.VISIBLE
    }

    override fun handleResult(rawResult: Result?) {

        val amountUser = sharedPreferences?.getString( // for get sharedPreferences
            getString(R.string.user_amount),
            getString(R.string.default_value)
        )
        val orderID = intent?.getStringExtra("orderID").toString()
        var total = intent?.getStringExtra("total").toString()
        var userID = intent?.getStringExtra("userID").toString()


        pickViewModel.pickMyFood(
            rawResult.toString(),
            amountUser.toString(),
            orderID,
            userID
        )

        pickViewModel.statusCode?.observe(this, Observer {
            if (it.message == "Success") {
                text_view_qr_code_value.text = "Picked Up Succesfully"
                btnDone.visibility = View.VISIBLE
                btnScan.visibility = View.GONE
            } else  {
                text_view_qr_code_value.text = "Not Valid"
                btnScan.visibility = View.VISIBLE
                btnDone.visibility = View.GONE
            }
        })



        alignQR.visibility = View.GONE
        frame_layout_camera.visibility = View.GONE
    }

    override fun onClick(view: View?) {
        when (view) {
btnDone -> {
    val intent = Intent(this,HomeActivity::class.java)
    startActivity(intent)

} btnScan->{
            mScannerView.resumeCameraPreview(this)
            initDefaultView()
        }
            else -> {
                /* nothing to do in here */
            }
        }
    }

}