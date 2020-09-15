package com.project.pickmyfood.data.store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.product.ProductViewModel
import com.project.pickmyfood.data.store.KeyStore
import com.project.pickmyfood.data.store.StoreViewModel
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
        val storeImage = storeList[position].storeImage
        val nameResto = storeList[position].storeName
        val addressResto = storeList[position].storeAddress
        val idResto = storeList[position].storeID
        val storeOwner = storeList[position].storeOwner
        Picasso.get().load(storeImage).into(holder.imageResto)
        holder.nameResto.text = storeList[position].storeName
        holder.adressResto.text = storeList[position].storeAddress
        holder.idResto.text = storeList[position].storeID
//        Picasso.with(getImageResto).load(storeList[position].storeImage).into(holder.imageResto)
        holder.imageResto.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_restoListFragment_to_foodMenuListFragment, bundleOf(
                    "imageResto" to storeImage,
                    "nameResto" to nameResto,
                    "addressResto" to addressResto,
                    "storeID" to idResto,
                    "storeOwner" to storeOwner
                ))
        }
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

}

class StoreViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
    lateinit var storeViewModel: StoreViewModel
    lateinit var productViewModel: ProductViewModel
    var index: Int = 0
    val idResto= v.findViewById<TextView>(R.id.idFood)
    val imageResto = v.findViewById<ImageView>(R.id.foodImage)
    val nameResto = v.findViewById<TextView>(R.id.foodName)
    val adressResto = v.findViewById<TextView>(R.id.addressFood)

    override fun onClick(v: View?) {
        when(v){
            imageResto -> {
                storeViewModel.getStore(idResto.toString())
                productViewModel.getAllProductByIdStore(idResto.toString())
            }
        }
    }

}
