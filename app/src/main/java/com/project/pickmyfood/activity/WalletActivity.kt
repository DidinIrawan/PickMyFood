package com.project.pickmyfood.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.wallet.WalletViewModel
import kotlinx.android.synthetic.main.activity_wallet.*
import javax.inject.Inject

class WalletActivity : AppCompatActivity() {

    @Inject
    lateinit var walletViewModel: WalletViewModel
    var sharedPreferences : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        (applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )

        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )
        println("USER ID $userID")
        walletViewModel.getUserWallet(userID.toString())
        walletViewModel.walletUser.observe(this, Observer{
            walletUser.text = "Rp." + " " + it.amount

        }

        )
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }
}