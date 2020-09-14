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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_profile.*


class UserProfileFragment : Fragment(), View.OnClickListener {
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val imageUser = sharedPreferences?.getString(
            getString(R.string.user_image),
            getString(R.string.default_value)
        )
        val userFirstname = sharedPreferences?.getString(
            getString(R.string.user_firstName),
            getString(R.string.default_value)
        )
        val userLastname = sharedPreferences?.getString(
            getString(R.string.user_lastName),
            getString(R.string.default_value)
        )
        val userEmail = sharedPreferences?.getString(
            getString(R.string.user_email),
            getString(R.string.default_value)
        )
        val userPhone = sharedPreferences?.getString(
            getString(R.string.user_phone),
            getString(R.string.default_value)
        )
        val userPoin = sharedPreferences?.getString(
            getString(R.string.user_poin),
            getString(R.string.default_value)
        )
        val userAdress = sharedPreferences?.getString(
            getString(R.string.user_address),
            getString(R.string.default_value)
        )
      userName.text = "$userFirstname " + " " + "$userLastname"
        userTlp.text = "$userPhone"
        profilEmail.text = "$userEmail"
        userPoint.text = "$userPoin"
        userAdress1.text = "$userAdress"
        Picasso.get().load(imageUser).into(profile_image)

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