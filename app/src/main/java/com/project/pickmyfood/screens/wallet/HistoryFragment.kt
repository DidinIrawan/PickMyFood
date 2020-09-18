package com.project.pickmyfood.screens.wallet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.history.HistoryTransactionViewModel
import com.project.pickmyfood.data.history.adapter.HistoryRecycleViewAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

class HistoryFragment : Fragment() {
    @Inject
    lateinit var historyTransactionViewModel: HistoryTransactionViewModel
    lateinit var historyRecycleViewAdapter: HistoryRecycleViewAdapter
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )
        historyListRecycleView.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        historyTransactionViewModel.historyTransactionList.observe(viewLifecycleOwner, Observer {
            historyRecycleViewAdapter = HistoryRecycleViewAdapter(it, activity)
            historyListRecycleView.adapter = historyRecycleViewAdapter
        })
        historyTransactionViewModel.getAllHistoryTransactionByID(userID.toString())
    }
}