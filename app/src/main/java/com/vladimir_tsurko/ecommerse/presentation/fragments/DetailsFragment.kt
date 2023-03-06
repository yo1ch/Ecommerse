package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.HomeViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject


class DetailsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    private lateinit var colorsListAdapter: ColorsAdapter

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding== null")


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.hideBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        val slideModels = listOf<SlideModel>(
            SlideModel("https://asset.brandfetch.io/id4pDar7o9/id1a6Qkd5F.jpeg"),
            SlideModel("https://asset.brandfetch.io/idjR6yqXUb/idNxhmnlFq.jpeg"),
            SlideModel("https://asset.brandfetch.io/id2S-kXbuK/idWvKxYIpS.png"),
        )


        binding.slider.setImageList(slideModels, ScaleTypes.CENTER_CROP)

        colorsListAdapter = ColorsAdapter()
        binding.rvColors.adapter = colorsListAdapter
        val itemAnimator = binding.rvColors.itemAnimator
        if(itemAnimator is DefaultItemAnimator){
            itemAnimator.supportsChangeAnimations = false
        }
        viewModel.colors.observe(viewLifecycleOwner){
            colorsListAdapter.submitList(it)
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