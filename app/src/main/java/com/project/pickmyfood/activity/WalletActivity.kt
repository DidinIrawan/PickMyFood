package com.project.pickmyfood.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.wallet.WalletViewModel
import kotlinx.android.synthetic.main.activity_wallet.*
import javax.inject.Inject

class WalletActivity : AppCompatActivity() {

    @Inject
    lateinit var walletViewModel: WalletViewModel
    lateinit var navController: NavController
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        (applicationContext as MyApplication).applicationComponent.inject(this)

        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )

        val userFirstName = sharedPreferences?.getString(
            getString(R.string.user_firstName),
            getString(R.string.default_value)
        )
        nameUser.text = userFirstName.toString()
        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )

        walletViewModel.wallet.observe(this, Observer {
            walletUser.text = "IDR ${it.amount}"
        })
        walletViewModel.getWalletByID(userID.toString())

        navController = (fragment_nav_host as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottom_navigation_wallet, navController)
        bottom_navigation_wallet.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeMenu -> {
                    navController.navigate(R.id.action_topUpFragment_to_historyFragment)
                    true
                }
                R.id.restoMenu -> {
                    navController.navigate(R.id.action_historyFragment_to_topUpFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }
}