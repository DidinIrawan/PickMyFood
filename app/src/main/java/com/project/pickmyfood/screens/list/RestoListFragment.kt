package com.project.pickmyfood.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.store.StoreViewModel
import com.project.pickmyfood.data.store.adapter.StoreRecycleAdapter
import kotlinx.android.synthetic.main.fragment_resto_list.*
import javax.inject.Inject


class RestoListFragment : Fragment(), View.OnClickListener {
    @Inject
    lateinit var storeViewModel: StoreViewModel
    lateinit var adapter: StoreRecycleAdapter
    var searchRestoText = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeRecycleView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        storeViewModel.store?.observe(viewLifecycleOwner, Observer {
            adapter = StoreRecycleAdapter(it, activity)
            storeRecycleView.adapter = adapter
        })


        storeViewModel.getAllStore(searchRestoText)

        searchButton.setOnClickListener(this)
//        println("GetALl ${adapter.toString()}")
//        restoListCard.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        searchRestoText = searchResto.text.toString()

        when (p0) {
            searchButton -> {
                println("search OnClick $searchRestoText")
                storeViewModel.getAllStore(searchRestoText)
            }
        }
    }


}