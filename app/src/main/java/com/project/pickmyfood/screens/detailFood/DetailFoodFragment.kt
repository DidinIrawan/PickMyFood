package com.project.pickmyfood.screens.detailFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_food.*
import kotlinx.android.synthetic.main.fragment_food_menu_list.*
import javax.inject.Inject


class DetailFoodFragment : Fragment() {

//    @Inject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restoImage = arguments?.getString("imageResto")
        val nameProduct = arguments?.getString("nameProduct")
        val priceProduct = arguments?.getString("priceProduct")
        val storeId = arguments?.getString("storeID")
        println("ID Store ${storeId}")

        nameFoodText.text = "${nameProduct.toString()}"
//        priceProductText.text = "${priceProduct.toString()}"
//        Picasso.get().load(restoImage).into(profile_image)
    }
}