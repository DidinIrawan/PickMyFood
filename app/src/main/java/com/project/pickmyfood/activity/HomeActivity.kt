package com.project.pickmyfood.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.project.pickmyfood.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        navController = (nav_home_host_fragment_container as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomHomeNavigation, navController)
        bottomHomeNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.homeMenu -> {
                    navController.navigate(R.id.action_global_to_homeFragment)
                    true
                }
                R.id.restoMenu -> {
                    navController.navigate(R.id.action_global_to_restoListFragment)
                    true
                }
                R.id.dompetMenu -> {
                    navController.navigate(R.id.action_global_to_walletFragment)
                    true
                }
                R.id.profileMenu -> {
                    navController.navigate(R.id.action_global_to_userProfileFragment)
                    true
                }
                R.id.pickMenu -> {
                    navController.navigate(R.id.action_global_to_orderListFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }
}