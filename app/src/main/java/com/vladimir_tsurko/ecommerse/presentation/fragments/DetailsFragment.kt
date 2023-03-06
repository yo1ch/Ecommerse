package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentDetailsBinding
import com.vladimir_tsurko.ecommerse.databinding.FragmentHomeBinding
import com.vladimir_tsurko.ecommerse.domain.models.ColorModel
import com.vladimir_tsurko.ecommerse.presentation.MainActivity
import com.vladimir_tsurko.ecommerse.presentation.adapters.MainScreenAdapter
import com.vladimir_tsurko.ecommerse.presentation.adapters.colorsAdapter.ColorsAdapter
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.DetailsViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.HomeViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class DetailsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailsViewModel

    private lateinit var colorsListAdapter: ColorsAdapter

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding== null")



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]

        setupImageCarousel()
        setupRecyclerView()

    }



    private fun setupImageCarousel(){
        viewModel.details.observe(viewLifecycleOwner){ details ->
            with(binding){
                nameTextView.text = details.name
                priceTextView.text = "$${details.price}"
                detailsTextView.text = details.description
                textViewReviews.text = "(${details.number_of_reviews} reviews)"
                textViewRating.text = details.rating.toString()
                slider.setImageList(details.image_urls, ScaleTypes.CENTER_CROP)
            }

        }
    }

    private fun setupRecyclerView(){
        colorsListAdapter = ColorsAdapter()
        binding.rvColors.adapter = colorsListAdapter
        val itemAnimator = binding.rvColors.itemAnimator
        if(itemAnimator is DefaultItemAnimator){
            itemAnimator.supportsChangeAnimations = false
        }
        viewModel.colors.observe(viewLifecycleOwner){
            colorsListAdapter.submitList(it?.toList())
        }

        colorsListAdapter.onItemClickListener = {
            viewModel.changeSelectedState(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }
}