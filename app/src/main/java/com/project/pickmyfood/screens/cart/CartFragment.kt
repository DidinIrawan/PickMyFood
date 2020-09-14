package com.project.pickmyfood.screens.cart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.cart.recycleview.CartRecycleView
import com.project.pickmyfood.data.cart.viewmodel.CartViewModel
import com.project.pickmyfood.data.checkout.CheckOut
import com.project.pickmyfood.data.checkout.CheckOutViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject


class CartFragment : Fragment(),View.OnClickListener {
    @Inject lateinit var checkOutViewModel: CheckOutViewModel

    val cartViewModel by activityViewModels<CartViewModel>()
    lateinit var cartRecyclerView: CartRecycleView
    lateinit var navController: NavController

    var sharedPreferences: SharedPreferences? = null
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
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartListRecycleView.layoutManager = LinearLayoutManager(activity)
        cartRecyclerView =
            CartRecycleView(cartViewModel.cartLiveData.value!!, cartViewModel, totalPrice )//ngirim ke recycleview
        cartListRecycleView.adapter = cartRecyclerView
        cartViewModel.cartLiveData.observe(viewLifecycleOwner, Observer {
//            cartRecyclerView = CartRecycleView(it,cartViewModel)
            cartRecyclerView.notifyDataSetChanged()
        })


//        totalPrice.text = subtotal.toString()
        buttonContinue.setOnClickListener(this)

        //CheckOut
        checkoutText.setOnClickListener(this)
        if (sharedPreferences?.contains(getString(R.string.order_key))!! && sharedPreferences?.contains(
                getString(R.string.checkout_method_key)
            )!!
        ){
            view.findNavController().navigate(R.id.action_cartFragment_to_listCartFragment)
        }
        checkOutViewModel.checkOutResponse.observe(viewLifecycleOwner,
        androidx.lifecycle.Observer {
            if (it.statusCode == 502.toString()){
                Toast.makeText(
                    this.context,"Get all history Error", Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(this.context, "Get All History is success",Toast.LENGTH_SHORT).show()

                checkOutViewModel.checkOutResponseData.observe(viewLifecycleOwner, Observer {
                    if (it!=null){
                        with(sharedPreferences?.edit()){
                            this?.putString(
                                getString(R.string.order_key),
                                it.orderID
                            )
                            this?.putString(
                                getString(R.string.order_created),
                                it.orderCreated
                            )
                            this?.putString(
                                getString(R.string.store_key),
                                it.storeID
                            )
                            this?.putString(
                                getString(R.string.checkout_method_key),
                                "appLogin"
                            )
                            this?.commit()
                        }
                        navController.navigate(R.id.action_cartFragment_to_listCartFragment)
                    }
                })


            }
        })

    }


    override fun onClick(v: View?) {

        when(v){

            checkoutText -> {
                var subtotal: Int = 0
                for (i in cartViewModel.cartList.indices) {
                    println("cartlist : ${cartViewModel.cartList[i].price}")
                    subtotal += (cartViewModel.cartList[i].price.toInt())
                }

                println("Total Card : $subtotal")
                val storeID = arguments?.getString("storeID")
                val checkOut = CheckOut(
                    storeID = storeID.toString(),
                    soldItems = cartViewModel.cartList
                )
                println(storeID.toString())
                println(cartViewModel.cartList[0].qty)
                if (cartViewModel.cartList.isEmpty()) {
                    Toast.makeText(
                        this.context, "Cart Not be null", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    checkOutViewModel.checkOutOrder(checkOut)
                }

                checkOutViewModel.checkOutResponseData.observe(viewLifecycleOwner, Observer {
                    v?.findNavController()?.navigate(
                        R.id.action_cartFragment_to_listCartFragment,
                        bundleOf(
                            "orderID" to it.orderID,
                            "total" to subtotal
                        )
                    )
                })

            }

            buttonContinue -> {
                v?.findNavController()?.navigate(R.id.action_global_to_restoListFragment)
            }
        }
    }

//    override fun onItemClick(view: View, index: Int) {
//        cartViewModel.removeCart(index)
////        Toast.makeText(this,"${index.plus(1)}  Deleted!", Toast.LENGTH_SHORT).show()
//    }
}