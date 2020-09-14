package com.project.pickmyfood.data.cart.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.project.pickmyfood.R
import com.project.pickmyfood.data.cart.classes.Cart
import com.project.pickmyfood.data.cart.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.cart_recycle_view.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import org.w3c.dom.Text
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

class CartRecycleView(
    private val cartList: MutableList<Cart>,
    val cartViewModel: CartViewModel,
    val totalPrice: TextView
):RecyclerView.Adapter<CartViewHolder>(){
//    lateinit var navController: NavController
//    lateinit var cart: RecycleClickCart
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
        val foodQuantity = cartList[position].qty
        val productPrice = cartList[position].price
        val productNote = cartList[position].note
        val productName = cartList[position].productName
        holder.productID.text = productID
        holder.productName.text = productName
        holder.foodQuantity.text = foodQuantity.toString()
        holder.productPrice.text = productPrice.toString()
        holder.productNote.text = productNote
//        holder.itemView.setOnClickListener(holder)
//        holder.itemView.button_remove.setOnClickListener(View.OnClickListener{
//            cart.onItemClick(it,position)
//        })
        holder.buttonRemove.setOnClickListener {
            cartViewModel.removeCart(position)
        }

        var subtotal:Int = 0
        for (i in cartViewModel.cartList.indices){
            println("cartlist : ${cartViewModel.cartList[i].price}")
            subtotal += (cartViewModel.cartList[i].price.toInt())
        }
        totalPrice.text = "Rp. ${subtotal.toString()}"
    }


}
class CartViewHolder(v:View):RecyclerView.ViewHolder(v) {
    val productID = v.findViewById<TextView>(R.id.idProduct)
    val productName = v.findViewById<TextView>(R.id.productName)
    val foodQuantity = v.findViewById<TextView>(R.id.foodQuantity)
    val productPrice = v.findViewById<TextView>(R.id.foodPrice)
    val productNote = v.findViewById<TextView>(R.id.foodNote)

    val buttonRemove = v.findViewById<Button>(R.id.button_remove)
//    override fun onClick(v: View?) {
//        if (v != null) {
//            Toast.makeText(v.context, "cart is null", Toast.LENGTH_SHORT).show()
//        }
//    }

}
//interface RecycleClickCart{
//    fun onItemClick(view:View, index:Int)
//}