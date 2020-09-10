package com.project.pickmyfood.screens.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.user.model.UserLoginModel
import com.project.pickmyfood.data.user.viewmodel.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : Fragment(), View.OnClickListener {

    var sharedPreferences: SharedPreferences? = null
    lateinit var navController: NavController

    @Inject
    lateinit var userLoginViewModel: UserLoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences =
            activity?.getSharedPreferences(
                getString(R.string.shared_preference_name),
                Context.MODE_PRIVATE
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (sharedPreferences?.contains(getString(R.string.user_firstName))!! && sharedPreferences?.contains(
                getString(R.string.login_method_key)
            )!!
        ) {
            view?.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        }
        navController = Navigation.findNavController(view)

        userLoginToRegister.setOnClickListener(this)
        userLoginButton.setOnClickListener(this)

        userLoginViewModel.userLoginResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it.statusCode == 401.toString()){
                Toast.makeText(this.context,"Username or password is wrong", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_global_to_loginFragment)
            }else{
                Toast.makeText(this.context, "Login Success", Toast.LENGTH_SHORT).show()

                userLoginViewModel.userLoginResponseData.observe(viewLifecycleOwner, Observer {
                    if (it !=null) {
                        with(sharedPreferences?.edit()){
                            this?.putString(
                                getString(R.string.id_key),
                                it.userID
                            )
                            this?.putString(
                                getString(R.string.user_firstName),
                                it.userFirstName
                            )
                            this?.putString(
                                getString(R.string.user_lastName),
                                it.userLastName
                            )
                            this?.putString(
                                getString(R.string.user_address),
                                it.userAddress
                            )
                            this?.putString(
                                getString(R.string.user_phone),
                                it.userPhone
                            )
                            this?.putString(
                                getString(R.string.user_poin),
                                it.userPoin
                            )
                            this?.putString(
                                getString(R.string.user_image),
                                it.userImage
                            )
                            this?.putString(
                                getString(R.string.login_method_key),
                                "appLogin"
                            )
                            this?.commit()
                        }
                        navController.navigate(R.id.action_loginFragment_to_homeActivity)
                    }
                })
            }
        })
    }

    override fun onClick(v: View?) {
        when(v){
            userLoginToRegister -> {
                navController.navigate(R.id.action_loginFragment_to_signUpFragment)
            }
            userLoginButton -> {
                val userLoginModel = UserLoginModel(
                    username = usernameInput.text.toString(),
                    password = userPasswordInput.text.toString()
                )
                if (usernameInput.text.toString() == "" ||
                        userPasswordInput.text.toString() == ""
                ){
                    Toast.makeText(this.context, "Isi semua form", Toast.LENGTH_SHORT).show()
                } else {
                    userLoginViewModel.loginUser(userLoginModel)
                }
            }
        }
    }


}