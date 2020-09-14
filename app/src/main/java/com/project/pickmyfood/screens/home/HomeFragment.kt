package com.project.pickmyfood.screens.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.project.pickmyfood.R
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),View.OnClickListener {
    var sharedPreferences: SharedPreferences? = null
    var sampleImages = intArrayOf(
        R.drawable.imgslide1,
        R.drawable.imageslide2,
        R.drawable.imageslide3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            imageView.setImageResource(sampleImages[position])
            imageView.scaleType = ImageView.ScaleType.FIT_XY
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tukarPoinButton.setOnClickListener(this)
        val carouselView = view.findViewById(R.id.carouselView) as CarouselView
        carouselView.setImageListener(imageListener)
        carouselView.pageCount = sampleImages.size
        val userFirstName = sharedPreferences?.getString( // for get sharedPreferences
            getString(R.string.user_firstName),
            getString(R.string.default_value)
        )
        val poin = sharedPreferences?.getString( // for get sharedPreferences
            getString(R.string.user_poin),
            getString(R.string.default_value)
        )
        menuHomeText.text = "Hi, $userFirstName"
        point.text = "$poin Point"
    }

    override fun onClick(v: View?) {
        when(v){
            tukarPoinButton -> {
                with(sharedPreferences?.edit()){
                    this?.remove(getString(R.string.user_firstName))
                    this?.commit()
                    v?.findNavController()?.navigate(R.id.action_userProfileFragment_to_mainActivity)
                }

            }
        }
    }
}