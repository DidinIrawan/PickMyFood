package com.project.pickmyfood.screens.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.product.ProductViewModel
import com.project.pickmyfood.data.product.adapter.ProductRecycledApter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_food_menu_list.*
import javax.inject.Inject


class FoodMenuListFragment : Fragment(),View.OnClickListener {
    @Inject
    lateinit var productViewModel: ProductViewModel

    lateinit var productRecycledApter: ProductRecycledApter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_menu_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restoImage = arguments?.getString("imageResto")
        val restoName = arguments?.getString("nameResto")
        val restoAddress = arguments?.getString("addressResto")
        val storeId = arguments?.getString("storeID")
        println("ID Store ${storeId}")

        nameRestoText.text = "${restoName.toString()}"
        addressRestoText.text = "${restoAddress.toString()}"
        Picasso.get().load(restoImage).into(profile_image)

        productListRecycleView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        productViewModel.product?.observe(viewLifecycleOwner, Observer {
            productRecycledApter = ProductRecycledApter(it, activity)
            productListRecycleView.adapter = productRecycledApter
        })
        productViewModel.getAllProductByIdStore(storeId.toString())
//        productListRecycleView.layoutManager = LinearLayoutManager(activity)
//        productViewModel.product?.observe(viewLifecycleOwner, Observer {
//            productRecycledApter = ProductRecycledApter(it, activity)
//            productListRecycleView.adapter = productRecycledApter
//        })
//        productViewModel.getAllProductByIdStore(storeId.toString())
        cartIcon.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            cartIcon ->{
                v?.findNavController()?.navigate(R.id.action_detailFoodFragment_to_cartFragment)
            }
        }
    }
}