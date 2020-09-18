package com.project.pickmyfood.data.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.history.HistoryTransaction

class HistoryRecycleViewAdapter(
    private val historyTransaction: List<HistoryTransaction>,
    private val activity: FragmentActivity?
) : RecyclerView.Adapter<HistoryTransactionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryTransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_transaction_recycle_view, parent, false)
        return HistoryTransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyTransaction.size
    }

    override fun onBindViewHolder(holder: HistoryTransactionViewHolder, position: Int) {
        holder.topUpIDText.text = historyTransaction[position].topUpID
        holder.topUpAmountText.text = historyTransaction[position].topUpAmount
        holder.userFirstNameText.text = historyTransaction[position].userFirstName
        holder.topUpDateText.text = historyTransaction[position].topUpDate
        holder.topUpStatusText.text = historyTransaction[position].topUpStatus
    }

}

class HistoryTransactionViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val topUpIDText = v.findViewById<TextView>(R.id.topUpIdText)
    val topUpAmountText = v.findViewById<TextView>(R.id.amountText)
    val userFirstNameText = v.findViewById<TextView>(R.id.userNameText)
    val topUpDateText = v.findViewById<TextView>(R.id.topUpDateText)
    val topUpStatusText = v.findViewById<TextView>(R.id.topUpStatusText)
}