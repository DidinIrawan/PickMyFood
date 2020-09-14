package com.project.pickmyfood.screens.wallet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import com.project.pickmyfood.R
import com.project.pickmyfood.container.MyApplication
import com.project.pickmyfood.data.wallet.TopUpWallet
import com.project.pickmyfood.data.wallet.WalletViewModel
import kotlinx.android.synthetic.main.fragment_top_up.*
import javax.inject.Inject


class TopUpFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var walletViewModel: WalletViewModel
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
        (activity?.application as MyApplication).applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        topUpInputText.text
        topUpButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val userID = sharedPreferences?.getString(
            getString(R.string.id_key),
            getString(R.string.default_value)
        )
        println("USER ID $userID")
        when (v) {
            topUpButton -> {
                val topUpWallet = TopUpWallet(topUpAmount = topUpInputText.text.toString())

                if (topUpInputText.text.toString() == "") {
                    Toast.makeText(this.context, "Must be Field", LENGTH_SHORT).show()
                } else {
                    walletViewModel.topUpWallet(topUpWallet, userID.toString())
                    Toast.makeText(
                        this.context,
                        "Top UP Success, Waiting For Confirmation",
                        LENGTH_SHORT
                    ).show()
                }
            }

        }
    }
}