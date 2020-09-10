package com.project.pickmyfood.screens.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.project.pickmyfood.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tukarPoinButton.setOnClickListener(this)

        val userFirstName = sharedPreferences?.getString(
            getString(R.string.user_firstName),
            getString(R.string.default_value)
        )
        menuHomeText.text = "Hi, $userFirstName"
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