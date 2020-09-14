package com.project.pickmyfood.screens.detailFood

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.project.pickmyfood.R
import com.project.pickmyfood.data.cart.viewmodel.CartViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_recycle_view.*
import kotlinx.android.synthetic.main.fragment_detail_food.*
import kotlinx.android.synthetic.main.fragment_food_menu_list.*


class DetailFoodFragment : Fragment(),View.OnClickListener {
    val cartViewModel by activityViewModels<CartViewModel>()
    var sharedPreferences: SharedPreferences? = null
//    @Inject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
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
        return inflater.inflate(R.layout.fragment_detail_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restoImage = arguments?.getString("productImage")
        val productID = arguments?.getString("productID")
        val nameProduct = arguments?.getString("nameProduct")
        val priceProduct = arguments?.getString("priceProduct")
        val storeId = arguments?.getString("storeID")
        println("ID Store ${storeId}")

        nameFoodText.text = "${nameProduct.toString()}"
        priceFood.text = "Rp. ${priceProduct.toString()}"
        Picasso.get().load(restoImage).into(imageFoodDetail)
//        priceProductText.text = "${priceProduct.toString()}"
//        Picasso.get().load(restoImage).into(profile_image)


        buttonCart.setOnClickListener(this)
//        val productQuantity = foodQuantity?.text.toString()
//        println("Produc QTY $productQuantity")
//        val note = foodNote?.text.toString()
//        println("note $note")
        var quantity = 0
        buttonPlus.setOnClickListener {
            quantity++
            quantityFood.text = quantity.toString()
        }
        buttonMinus.setOnClickListener {
            quantity--
            quantityFood.text = quantity.toString()
        }

    }

    override fun onClick(v: View?) {
        val foodQuantity = quantityFood.text.toString()
        val quantityTotal=foodQuantity.toInt()
        val note = noteInput.text.toString()
//        val note = v?.findViewById<EditText>(R.id.foodNote)
        val productID = arguments?.getString("productID")
        val priceProduct = arguments?.getString("priceProduct")!!.toInt()
        val priceTotal: Int = quantityTotal*priceProduct
        val nameProduct = arguments?.getString("nameProduct")

        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )
        when(v){

            buttonCart -> {
                val storeId = arguments?.getString("storeID")
                println("productName $nameProduct")
                println("Button Cart $storeId")
                cartViewModel.addCartList(foodQuantity,productID.toString(),nameProduct.toString(),userID.toString(),priceTotal.toString(),note)
                Toast.makeText(activity, "Added to cart", Toast.LENGTH_SHORT).show()
                v?.findNavController()?.navigate(R.id.action_detailFoodFragment_to_cartFragment, bundleOf(
                    "storeID" to storeId,
                    "nameProduct" to nameProduct
                ))
            }
        }
    }
}