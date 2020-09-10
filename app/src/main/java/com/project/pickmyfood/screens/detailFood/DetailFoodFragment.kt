package com.project.pickmyfood.screens.detailFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.project.pickmyfood.R
import com.project.pickmyfood.data.cart.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.cart_recycle_view.*
import kotlinx.android.synthetic.main.fragment_detail_food.*


class DetailFoodFragment : Fragment(),View.OnClickListener {
    val cartViewModel by activityViewModels<CartViewModel>()
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
        val productID = arguments?.getString("productID")
        val nameProduct = arguments?.getString("nameProduct")
        val priceProduct = arguments?.getString("priceProduct")
        val storeId = arguments?.getString("storeID")
        println("ID Store ${storeId}")

        nameFoodText.text = "${nameProduct.toString()}"
//        priceProductText.text = "${priceProduct.toString()}"
//        Picasso.get().load(restoImage).into(profile_image)
//        buttonCart.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_detailFoodFragment_to_cartFragment, bundleOf(
//                "productID" to productID,
//                "priceProduct" to priceProduct
//            ))
//        }

        buttonCart.setOnClickListener(this)
//        val productQuantity = foodQuantity?.text.toString()
//        println("Produc QTY $productQuantity")
//        val note = foodNote?.text.toString()
//        println("note $note")



    }

    override fun onClick(v: View?) {
//        var productQuantity = v?.findViewById<EditText>(R.id.foodQuantity)
        val foodQuantity = quantityFood.text.toString()
        val note = noteInput.text.toString()
//        val note = v?.findViewById<EditText>(R.id.foodNote)
        val productID = arguments?.getString("productID")
        val priceProduct = arguments?.getString("priceProduct")
        when(v){
            buttonCart -> {
                cartViewModel.addCartList(productID.toString(),foodQuantity,priceProduct.toString(),note)
                Toast.makeText(activity, "Added to cart", Toast.LENGTH_SHORT).show()
                v?.findNavController()?.navigate(R.id.action_detailFoodFragment_to_cartFragment)
            }
        }
    }
}