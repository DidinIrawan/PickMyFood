package com.project.pickmyfood.screens.user

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.profil.ProfilViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_profile.*
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.project.pickmyfood.data.user.model.UserEditModel
import com.project.pickmyfood.data.user.viewmodel.UserEditViewModel
import kotlinx.android.synthetic.main.fragment_edit_user_profile.*
import kotlinx.android.synthetic.main.fragment_user_profile.profile_image


class EditUserProfile : Fragment(),View.OnClickListener {
    @Inject
    lateinit var profilViewModel: ProfilViewModel
    @Inject
    lateinit var  userEditViewModel: UserEditViewModel
    lateinit var navController: NavController
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
        return inflater.inflate(R.layout.fragment_edit_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )
        navController = Navigation.findNavController(view)
        profilViewModel.profil?.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.userImage).into(profile_image1)
        })
        profilViewModel.getUserProfil(userID.toString())

        btn_save.setOnClickListener(this)
        userEditViewModel.userEditResponse.observe(viewLifecycleOwner,androidx.lifecycle.Observer{
            if (it.statusCode == 400.toString()){
                println(it.statusCode)
                Toast.makeText(
                    this.context,"Update  Failed", Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(this.context, "Update Data Succesfully", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_global_to_userProfileFragment)
            }
            println("OKE MASUK SINI")
        })

    }

    override fun onClick(v: View?) {
        when(v){
            btn_save ->{
                val userEditModel = UserEditModel(
                    userFirstName = inputFirstname.text.toString(),
                    userLastName = inputLastName.text.toString(),
                    userAddress = inputAdress.text.toString(),
                    userPhone = inputNoTlp.text.toString(),
                    userEmail = inputEmail.text.toString()

                )
                if (inputFirstname.text.toString()==""||
                    inputLastName.text.toString()==""||
                    inputAdress.text.toString()=="" ||
                    inputNoTlp.text.toString()==""||
                    inputEmail.text.toString()==""
                ){
                    Toast.makeText(this.context,"Fill out all forms",Toast.LENGTH_SHORT).show()
                }else{
                    val userID = sharedPreferences?.getString(
                        getString(R.string.id_key),
                        getString(R.string.default_value)
                    )
                    userEditViewModel.editUser(userID.toString(),userEditModel)
                    println(userEditModel.userFirstName)
                    println(userEditModel.userLastName)
                    println(userEditModel.userAddress)
                    println(userEditModel.userPhone)
                    println(userEditModel.userEmail)
                }
            }
        }
    }

}