package com.project.pickmyfood.screens.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.profil.ProfilViewModel
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import androidx.lifecycle.Observer


class HomeFragment : Fragment(),View.OnClickListener {
    @Inject
    lateinit var profilViewModel: ProfilViewModel
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tukarPoinButton.setOnClickListener(this)

        val userFirstName = sharedPreferences?.getString( // for get sharedPreferences
            getString(R.string.user_firstName),
            getString(R.string.default_value)
        )
        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )

        menuHomeText.text = "Hi, $userFirstName"
        profilViewModel.profil?.observe(viewLifecycleOwner, Observer {
           point.text = it.userPoin
        })
        println("USER ID YANG DI HOME $userID")
profilViewModel.getUserProfil(userID.toString())

    }

    override fun onClick(v: View?) {
        when(v){
            tukarPoinButton -> {
                with(sharedPreferences?.edit()){
                    this?.remove(getString(R.string.user_firstName))
                    this?.commit()
                    v?.findNavController()?.navigate(R.id.action_userProfileFragment_to_mainActivity)
                }

            }
        }
    }
}