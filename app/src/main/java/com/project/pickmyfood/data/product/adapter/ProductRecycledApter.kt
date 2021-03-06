package com.project.pickmyfood.data.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.product.Product
import com.squareup.picasso.Picasso

class ProductRecycledApter(
    private val productList: List<Product>,
    private val activity: FragmentActivity?,
    val storeID:String

) : RecyclerView.Adapter<ProductViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_list_recycle_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
       return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productImage = productList[position].productImage
        val productID = productList[position].productID
        val nameProduct = productList[position].productName
        val priceProduct = productList[position].productPrice.price
        val productStock = productList[position].productStock
        Picasso.get().load(productImage).into(holder.imageFood)

        holder.productName.text = productList[position].productName
        holder.productPrice.text = "Rp. ${productList[position].productPrice.price}"
        println("productList ${productList[position].productName}")
        println("productListPrice ${productList[position].productPrice.price}")
//        Picasso.with(getImageResto).load(storeList[position].storeImage).into(holder.imageResto)
        holder.buttonFood.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_global_to_detailFoodFragment, bundleOf(
                    "productID" to productID,
                    "nameProduct" to nameProduct,
                    "priceProduct" to priceProduct,
                    "productImage" to productImage,
                    "productStock" to productStock,
                    "storeID" to storeID
                )
                )
        }
    }

}

class ProductViewHolder(v:View): RecyclerView.ViewHolder(v){
    var index: Int = 0
    val imageFood = v.findViewById<ImageView>(R.id.imageFood)
    val productName = v.findViewById<TextView>(R.id.foodNameProduct)
    val productPrice = v.findViewById<TextView>(R.id.priceFood)
    val buttonFood = v.findViewById<Button>(R.id.buttonToCard)
}