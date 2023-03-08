package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentDetailsBinding
import com.vladimir_tsurko.ecommerse.presentation.adapters.colorsAdapter.ColorsAdapter
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.DetailsViewModel
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
        bindViews()
        setupRecyclerView()
        setupBackButton()


    }



    private fun bindViews(){
        viewModel.details.observe(viewLifecycleOwner){ details ->
            with(binding){
                val name = details.name
                val price = details.price.toString()
                val description = details.description
                val reviews = details.number_of_reviews.toString()
                val rating = details.rating.toString()

                nameTextView.text = name
                detailsTextView.text = description
                textViewReviews.text = getString(R.string.reviews_concat, reviews)
                textViewRating.text = rating
                if(details.price - price.toInt() == 0){
                    priceTextView.text = getString(R.string.price_concat_double,price)
                } else{
                    priceTextView.text = getString(R.string.price_concat_int, price)
                }
                slider.setImageList(details.image_urls, ScaleTypes.CENTER_CROP)
                plusButton.setOnClickListener {
                    viewModel.addToCart()
                }
                minusButton.setOnClickListener {
                    viewModel.deleteFromCart()
                }
                viewModel.productQuantity.observe(viewLifecycleOwner){
                    totalPrice.text = getString(R.string.price_concat_int," ${ details.price * it }")
                }
                showDefaultViews()
                hideProgressBar()

            }

        }
    }


    private fun showDefaultViews(){
        binding.tvColor.visibility = View.VISIBLE
        binding.buttonContainer.visibility = View.VISIBLE
        binding.iconFavourite.visibility = View.VISIBLE
        binding.bottomMenu.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }

    private fun setupBackButton(){
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
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