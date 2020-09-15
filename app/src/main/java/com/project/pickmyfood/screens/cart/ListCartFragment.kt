package com.project.pickmyfood.screens.cart

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.order.OrderViewModel
import com.project.pickmyfood.data.order.adapter.OrderRecycledAdapter
import com.project.pickmyfood.data.payment.Payment
import com.project.pickmyfood.data.payment.PaymentViewModel
import com.project.pickmyfood.screens.qrcode.QRcode
import kotlinx.android.synthetic.main.fragment_list_cart.*
import javax.inject.Inject


class ListCartFragment : Fragment(), View.OnClickListener {
    @Inject
    lateinit var orderViewModel: OrderViewModel

    @Inject
    lateinit var paymentViewModel: PaymentViewModel

    //    lateinit var checkOutViewModel: CheckOutViewModel
    lateinit var orderRecycledAdapter: OrderRecycledAdapter
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
        return inflater.inflate(R.layout.fragment_list_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val orderID = sharedPreferences?.getString( // for get sharedPreferences
//            getString(R.string.order_key),
//            getString(R.string.default_value)
//        )
        orderListRecycleView.layoutManager = LinearLayoutManager(this.context)

        val orderID = arguments?.getString("orderID")
        orderIDText.text = orderID
        orderViewModel.getOrderByID(orderID.toString())
//        orderListRecycleView.layoutManager = LinearLayoutManager(activity)
        orderViewModel.order?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                orderRecycledAdapter = OrderRecycledAdapter(it.soldItems, totalOrderPrice)
                orderListRecycleView.adapter = orderRecycledAdapter
                println("SoldItems ${it.soldItems[0].productName}")
                orderDateText.text = it.orderCreated
                storeIDText.text = it.storeID
            }
        })

        println("order ID $orderIDText")
        button_pay.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            button_pay -> {
                val storeID = arguments?.getString("storeID")
                val orderID = arguments?.getString("orderID")

                val amountUser = sharedPreferences?.getString( // for get sharedPreferences
                    getString(R.string.user_amount),
                    getString(R.string.default_value)
                )
                val userID = sharedPreferences?.getString( // for get sharedPreferences
                    getString(R.string.id_key),
                    getString(R.string.default_value)
                )
                val amount = amountUser?.toInt()
                println("Total amaunt $amount")

                val total = arguments?.getInt("total")
                println("Total List $total")
//
                if (total!! > amount!!) {
                    Toast.makeText(
                        this.context, "Ammount Kurang dari Total", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    paymentViewModel.paymentOrder(
                        payment = Payment(
                            orderID.toString(),
                            userID.toString(),
                            total.toString()
                        )
                    )
                    Toast.makeText(
                        this.context, "Payment Success :)", Toast.LENGTH_SHORT
                    ).show()
                    v?.findNavController()?.navigate(
                        R.id.action_global_to_qrcode,
                        bundleOf(
                            "storeID" to storeID,
                            "orderID" to orderID,
                            "userID" to userID,
                            "total" to total
                        )
                    )
                    val intent = Intent (requireContext(),QRcode::class.java).apply{
                        putExtra("orderID",orderID.toString())
                        putExtra("userID",userID.toString())
                        putExtra("total",total.toString())
                    }
                    startActivity(intent)
                }
            }
        }
    }
}