package com.project.pickmyfood.data.orderlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.orderlist.OrderList

class OrderListRecycleAdapter(
    private val orderLists: List<OrderList>,
    private val activity: FragmentActivity?
) : RecyclerView.Adapter<OrderListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_list_recycle_view, parent, false)
        return OrderListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderLists.size
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        val transactionIDnya = orderLists[position].transactionID
        val orderID = orderLists[position].orderID
        val userID = orderLists[position].userID
        val userFirstName = orderLists[position].userFirstName
        val amount = orderLists[position].amount
        val transactionCreated = orderLists[position].transactionCreated
        val transactionStatus = orderLists[position].transactionStatus
        println("TransactionID $transactionIDnya")
        holder.transactionIDOrder.text = transactionIDnya
//        holder.orderID.text = orderLists[position].orderID
        holder.userFirstName.text = orderLists[position].userFirstName
        holder.amountOrder.text = "Rp.${orderLists[position].amount}"
        holder.transactionCreated.text = orderLists[position].transactionCreated
        holder.transactionStatus.text = orderLists[position].transactionStatus
        holder.button_pick.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    R.id.action_global_to_qrcode, bundleOf(
                        "orderID" to orderID,
                        "userID" to userID,
                        "total" to amount
                    )
                )
        }
    }

}

class OrderListViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val transactionIDOrder = v.findViewById<TextView>(R.id.transactionIDText)

    //    val orderID = v.findViewById<TextView>(R.id.orderIDText)
    val userFirstName = v.findViewById<TextView>(R.id.userFirstNameText)
    val amountOrder = v.findViewById<TextView>(R.id.amountOrder)
    val transactionCreated = v.findViewById<TextView>(R.id.transactionCreatedText)
    val transactionStatus = v.findViewById<TextView>(R.id.transactionStatusText)

    val button_pick = v.findViewById<Button>(R.id.buttonPick)
}