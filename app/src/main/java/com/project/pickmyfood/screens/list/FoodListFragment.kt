package com.project.pickmyfood.screens.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.product.ProductViewModel
import com.project.pickmyfood.data.product.adapter.ProductRecycledApter
import com.project.pickmyfood.data.store.adapter.StoreRecycleAdapter
import kotlinx.android.synthetic.main.fragment_food_list.*
import javax.inject.Inject


class FoodListFragment : Fragment() {
    @Inject
    lateinit var productViewModel: ProductViewModel
    lateinit var adapter: ProductRecycledApter
    lateinit var storeRecycleAdapter: StoreRecycleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRecycleView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        productViewModel.product?.observe(viewLifecycleOwner, Observer {
            adapter = ProductRecycledApter(it, activity)
            productRecycleView.adapter = adapter
        })
//        productViewModel.getAllProductByIdProduct()
    }
}