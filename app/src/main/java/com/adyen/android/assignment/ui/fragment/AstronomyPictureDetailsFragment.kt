package com.adyen.android.assignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.adyen.android.assignment.R
import com.adyen.android.assignment.databinding.FragmentAstronomyPictureDetailsBinding


class AstronomyPictureDetailsFragment : Fragment() {

    private var _binding: FragmentAstronomyPictureDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<AstronomyPictureDetailsFragmentArgs>()
    private var clicked = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataOnUI()
        setFevImageOfAstronomyPicture()
    }

    private fun setDataOnUI() {
        binding.discription.text = args.value.explanation
        binding.headerTitle.text = args.value.title
        binding.dateTime.text = args.value.date.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAstronomyPictureDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setFevImageOfAstronomyPicture() {
        binding.fev.setOnClickListener {
            if (clicked) {
                binding.fev.setImageDrawable(context?.let { it1 ->
                    ContextCompat.getDrawable(
                        it1,
                        R.drawable.ic_favorite_filled
                    )
                })
                clicked = false
            } else {
                clicked = true
                binding.fev.setImageDrawable(context?.let { it1 ->
                    ContextCompat.getDrawable(
                        it1,
                        R.drawable.ic_favorite_border
                    )
                })
            }
        }
    }
}