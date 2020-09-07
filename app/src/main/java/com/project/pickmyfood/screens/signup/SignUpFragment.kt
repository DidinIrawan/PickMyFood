package com.project.pickmyfood.screens.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.user.model.UserRegisterAuth
import com.project.pickmyfood.data.user.model.UserRegisterModel
import com.project.pickmyfood.data.user.viewmodel.UserRegisterViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject


class SignUpFragment : Fragment(), View.OnClickListener {

    @Inject lateinit var  userRegisterViewModel: UserRegisterViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController =Navigation.findNavController(view)
        userRegisterToLoginText.setOnClickListener(this)
        userRegisterButton.setOnClickListener(this)
        userRegisterViewModel.userRegisterResponse.observe(viewLifecycleOwner,
        androidx.lifecycle.Observer {
            if (it.statusCode == 400.toString()){
                Toast.makeText(
                    this.context,"Username or name already registered", Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(this.context, "Create Account is success",Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_to_loginFragment)
            }
        })
    }

    override fun onClick(v: View?) {
        when(v){
            userRegisterToLoginText -> {
                navController.navigate(R.id.action_to_loginFragment)
            }
            userRegisterButton ->{
//                val userRegisterAuth = UserRegisterAuth(
//                    username = usernameInput.text.toString(),
//                    password = passwordInputText.text.toString()
//                )
                val userRegisterModel = UserRegisterModel(
                    userFirstName = firstNameInputText.text.toString(),
                    userLastName = lastNameInputText.text.toString(),
                    userPhone = userPhoneInputText.text.toString(),
                    auth = UserRegisterAuth(
                        username = usernameInput.text.toString(),
                        password = passwordInputText.text.toString()
                    )
                )

                if (firstNameInputText.text.toString()=="" ||
                    lastNameInputText.text.toString()=="" ||
                    userPhoneInputText.text.toString()=="" ||
                    usernameInput.text.toString()=="" ||
                    passwordInputText.text.toString()=="" ||
                    passwordConfirmInput.text.toString()==""
                ){
                    Toast.makeText(this.context,"Fill out all forms",Toast.LENGTH_SHORT).show()
                }else if (passwordInputText.text.toString() != passwordConfirmInput.text.toString()){
                    Toast.makeText(this.context,"Incorrect confirmation password", Toast.LENGTH_SHORT).show()
                }else{
                    userRegisterViewModel.registerNewUser(userRegisterModel)
                    println(userRegisterModel.auth.username.toString())
                }
            }
        }
    }
}