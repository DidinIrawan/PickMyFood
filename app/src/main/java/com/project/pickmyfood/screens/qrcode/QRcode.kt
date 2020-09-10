package com.project.pickmyfood.screens.qrcode

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.project.pickmyfood.R
import kotlinx.android.synthetic.main.activity_qr_code.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QRcode : AppCompatActivity(), ZXingScannerView.ResultHandler, View.OnClickListener {
    private lateinit var mScannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        initScannerView()
        initDefaultView()
        button_reset.setOnClickListener(this)
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
        button_reset.visibility = View.GONE
        btnPick.visibility = View.GONE
        alignQR.visibility = View.VISIBLE
        frame_layout_camera.visibility = View.VISIBLE
    }

    override fun handleResult(rawResult: Result?) {
        text_view_qr_code_value.text = "Hasil Scan : " + rawResult?.text
        button_reset.visibility = View.VISIBLE
        btnPick.visibility = View.VISIBLE
        alignQR.visibility = View.GONE
        frame_layout_camera.visibility = View.GONE
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_reset -> {
                mScannerView.resumeCameraPreview(this)
                initDefaultView()
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }

}