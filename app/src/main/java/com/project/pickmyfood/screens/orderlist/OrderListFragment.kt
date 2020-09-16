package com.project.pickmyfood.screens.orderlist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.orderlist.OrderListViewModel
import com.project.pickmyfood.data.orderlist.adapter.OrderListRecycleAdapter
import kotlinx.android.synthetic.main.fragment_order_list.*
import javax.inject.Inject


class OrderListFragment : Fragment() {
    @Inject
    lateinit var orderListViewModel: OrderListViewModel
    lateinit var orderListRecycleAdapter: OrderListRecycleAdapter
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
        return inflater.inflate(R.layout.fragment_order_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )
        if (orderListViewModel.orderList == null) {
            Toast.makeText(activity, "Order Food is empty", Toast.LENGTH_SHORT).show()
        } else {
            orderListRecycleViewTransaction.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            orderListViewModel.orderList.observe(viewLifecycleOwner, Observer {
                orderListRecycleAdapter = OrderListRecycleAdapter(it, activity)
                orderListRecycleViewTransaction.adapter = orderListRecycleAdapter
            })
            orderListViewModel.getAllOrderListByID(userID.toString())
        }

    }
}