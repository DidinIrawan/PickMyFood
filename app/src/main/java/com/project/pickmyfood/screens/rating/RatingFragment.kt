package com.project.pickmyfood.screens.rating

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.rating.Rating
import com.project.pickmyfood.data.rating.RatingViewModel
import kotlinx.android.synthetic.main.fragment_rating.*
import javax.inject.Inject


class RatingFragment : Fragment() {
    @Inject
    lateinit var ratingViewModel: RatingViewModel
    var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonRating.setOnClickListener {
            val userID = sharedPreferences?.getString( // for get sharedPreferences
                getString(R.string.id_key),
                getString(R.string.default_value)
            )
            val storeID = arguments?.getString("storeID")
            val rating = Rating(
                userID = userID.toString(),
                storeID = storeID.toString(),
                ratingValue = ratingResto.rating.toString(),
                ratingDescription = ratingDescription.text.toString()
            )
            ratingViewModel.ratingResto(rating)
            view?.findNavController()
                ?.navigate(R.id.action_global_to_restoListFragment)
        }
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        buttonRating.setOnClickListener(this)
//    }

//    override fun onClick(v: View?) {
//        when(v){
//            buttonRating ->{
//                val userID = sharedPreferences?.getString( // for get sharedPreferences
//                    getString(R.string.id_key),
//                    getString(R.string.default_value)
//                )
//                val storeID = arguments?.getString("storeID")
//                val rating = Rating(
//                    userID = userID.toString(),
//                    storeID = storeID.toString(),
//                    ratingValue = ratingResto.rating.toString(),
//                    ratingDescription = ratingDescription.text.toString()
//                )
//                ratingViewModel.ratingResto(rating)
//            }
//        }
//    }
}