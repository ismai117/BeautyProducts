package com.reachplc.interview.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.reachplc.interview.R
import com.reachplc.interview.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

class DetailFragment : Fragment() {

    private var detailBinding: FragmentDetailBinding? = null
    private val binding get() = detailBinding!!
    private var name: String? = null
    private var image: String? = null
    private var description: String? = null
    private var price: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val argument = DetailFragmentArgs.fromBundle(it).model

            if (argument != null) {
                name = argument.name
                image = argument.image
                description = argument.description
                price = "£${argument.price.roundToInt()}"
            } else {
                Log.d("item_shown", "error")
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        Glide.with(this.requireContext()).load(image).into(binding.itemImage)

        binding.itemName.text = name
        binding.itemDescription.text = description
        binding.itemPrice.text = price

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        detailBinding = null
    }

}