package com.project.pickmyfood.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.project.pickmyfood.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_resto_profile.*


class RestoProfileFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resto_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restoImage = arguments?.getString("imageResto")
        val restoName = arguments?.getString("nameResto")
        val restoAddress = arguments?.getString("addressResto")
        val storeId = arguments?.getString("storeID")
        val storeOwner = arguments?.getString("storeOwner")
        println("ID Store1 ${storeId}")

        nameRestoText.text = "${restoName.toString()}"
        addressRestoText.text = "${restoAddress.toString()}"
        Picasso.get().load(restoImage).into(profile_image)

        storeName.text = "${restoName.toString()}"
        storeAddress.text = "${restoAddress.toString()}"
        storeOwnerText.text = "${storeOwner.toString()}"

        cartIcon.setOnClickListener(this)
        addRating.setOnClickListener(this)
        restoMenuText.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            restoMenuText -> {
                val storeId = arguments?.getString("storeID")
                val restoImage = arguments?.getString("imageResto")
                val restoName = arguments?.getString("nameResto")
                val restoAddress = arguments?.getString("addressResto")
                val storeOwner = arguments?.getString("storeOwner")

                v?.findNavController()?.navigate(
                    R.id.action_restoListFragment_to_foodMenuListFragment, bundleOf(
                        "storeID" to storeId,
                        "imageResto" to restoImage,
                        "nameResto" to restoName,
                        "addressResto" to restoAddress,
                        "storeOwner" to storeOwner
                    )
                )
            }
            addRating -> {
                val storeId = arguments?.getString("storeID")
                v?.findNavController()?.navigate(
                    R.id.action_global_to_ratingFragment, bundleOf(
                        "storeID" to storeId
                    )
                )
            }
            cartIcon -> {
                v?.findNavController()?.navigate(R.id.action_detailFoodFragment2_to_cartFragment2)
            }
        }
    }
}