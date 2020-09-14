package com.project.pickmyfood.screens.user

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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user_profile.*
import javax.inject.Inject
import androidx.lifecycle.Observer


class UserProfileFragment : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_user_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_Logout.setOnClickListener(this)

        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )
        profilViewModel.profil?.observe(viewLifecycleOwner, Observer {
            userName.text = it.userFirstName + " " + it.userLastName
            userPoint.text = it.userPoin
            userTlp.text = it.userPhone
            profilEmail.text = it.userEmail
            userAdress1.text = it.userAddress
            Picasso.get().load(it.userImage).into(profile_image)

        })
        profilViewModel.getUserProfil(userID.toString())

    }

    override fun onClick(v: View?) {
        when (v) {
            btn_Logout -> {
                with(sharedPreferences?.edit()) {
//                    this?.remove(getString(R.string.user_firstName))
//                    this?.remove(getString(R.string.user_lastName))
//                    this?.remove(getString(R.string.user_phone))
//                    this?.remove(getString(R.string.user_email))
//                    this?.remove(getString(R.string.user_poin))
//                    this?.remove(getString(R.string.id_key))
//                    this?.remove(getString(R.string.user_address))
//                    this?.remove(getString(R.string.user_image))

                    this?.clear() // for all

                    this?.commit()
                    v?.findNavController()
                        ?.navigate(R.id.action_userProfileFragment_to_mainActivity)
                }

            }
        }

    }

}