package com.project.pickmyfood.screens.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.cart.recycleview.CartRecycleView
import com.project.pickmyfood.data.cart.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : Fragment() {
    val cartViewModel by activityViewModels<CartViewModel>()
    lateinit var cartRecyclerView: CartRecycleView
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartListRecycleView.layoutManager = LinearLayoutManager(activity)
        cartRecyclerView =
            CartRecycleView(cartViewModel.cartLiveData.value!!)
        cartListRecycleView.adapter = cartRecyclerView
        cartViewModel.cartLiveData.observe(viewLifecycleOwner, Observer {
            cartRecyclerView.notifyDataSetChanged()
        })

    }
}