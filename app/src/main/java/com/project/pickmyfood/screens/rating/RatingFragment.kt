package com.project.pickmyfood.screens.rating

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userID = sharedPreferences?.getString( // for get sharedPreferences
            getString(R.string.id_key),
            getString(R.string.default_value)
        )

        buttonRating.setOnClickListener {
//            ratingViewModel.ratingResto(
//                rating = Rating(
//                    userID = userID.toString(),
//                    storeID =
//                    ratingValue = ratingResto.rating.toString(),
//                    ratingDescription = ratingDescription.text.toString()
//                    )
//                )
//            )
        }
    }
}