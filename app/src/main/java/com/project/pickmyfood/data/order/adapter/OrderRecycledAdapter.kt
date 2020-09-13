package com.project.pickmyfood.data.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.order.Order
import com.project.pickmyfood.data.order.SoldItems
import com.project.pickmyfood.data.product.adapter.ProductViewHolder

class OrderRecycledAdapter(
    private val orderList: List<SoldItems>
):RecyclerView.Adapter<OrderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_recycle_view, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
//        val productID = orderList[position].soldItems
//        val orderID = orderList[position].orderID

//        holder.productName.text = orderList[position]
//        val productID:String="",
//        val productName:String="",
//        val Qty:String="",
//        val price:String="",
//        val note:String=""


    }

}

class OrderViewHolder(v: View): RecyclerView.ViewHolder(v){
    val orderID = v.findViewById<TextView>(R.id.orderIDText)
    val storeID = v.findViewById<TextView>(R.id.storeIDText)
    val orderCreated = v.findViewById<TextView>(R.id.orderDateText)
    val productName = v.findViewById<TextView>(R.id.foodNameProduct)
    val priceProduct = v.findViewById<TextView>(R.id.priceFood)
    val qtyProduct = v.findViewById<TextView>(R.id.qty)

}