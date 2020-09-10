package com.project.pickmyfood.data.cart.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.cart.classes.Cart
import org.w3c.dom.Text

class CartRecycleView(
    private val cartList: MutableList<Cart>
):RecyclerView.Adapter<CartViewHolder>(){
    lateinit var navController: NavController
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_recycle_view, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val productID = cartList[position].productID
        val foodQuantity = cartList[position].quantity
        val productPrice = cartList[position].productPrice
        val productNote = cartList[position].note
        holder.productID.text = productID
        holder.foodQuantity.text = foodQuantity.toString()
        holder.productPrice.text = productPrice
        holder.productNote.text = productNote
    }

}
class CartViewHolder(v:View):RecyclerView.ViewHolder(v){
    val productID = v.findViewById<TextView>(R.id.idProduct)
    val foodQuantity = v.findViewById<TextView>(R.id.foodQuantity)
    val productPrice = v.findViewById<TextView>(R.id.foodPrice)
    val productNote = v.findViewById<TextView>(R.id.foodNote)
}