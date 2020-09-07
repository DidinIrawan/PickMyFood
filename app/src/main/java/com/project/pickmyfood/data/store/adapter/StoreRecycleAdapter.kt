package com.project.pickmyfood.data.store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.store.KeyStore
import com.squareup.picasso.Picasso

class StoreRecycleAdapter(
    private val storeList: List<KeyStore>,
    private val getImageResto: FragmentActivity?

) : RecyclerView.Adapter<StoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.resto_list_recycle_view, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.nameResto.text = storeList[position].storeName
        holder.adressResto.text = storeList[position].storeAddress
        Picasso.with(getImageResto).load(storeList[position].storeImage).into(holder.imageResto)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

}

class StoreViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var index: Int = 0
    val imageResto = v.findViewById<ImageView>(R.id.foodImage)
    val nameResto = v.findViewById<TextView>(R.id.foodName)
    val adressResto = v.findViewById<TextView>(R.id.addressFood)

}