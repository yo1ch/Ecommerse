package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentHomeBinding
import com.vladimir_tsurko.ecommerse.presentation.MainActivity
import com.vladimir_tsurko.ecommerse.presentation.adapters.MainScreenAdapter
import com.vladimir_tsurko.ecommerse.presentation.adapters.categoriesAdapter.CategoriesAdapter
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.HomeViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    private lateinit var categoriesListAdapter: CategoriesAdapter

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding== null")

    private val adapter = MainScreenAdapter(
        onClickListener = this::clickListener
    )


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.showBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        setupToolbarSpannableText()
        setProfilePhoto()
        setupCategoriesRecyclerVIew()
        setupSearchView()
        setupProductsRecyclerView()

    }

    private fun setupToolbarSpannableText(){
        val spannableString = SpannableStringBuilder("Trade by bata")
        val color = ForegroundColorSpan(Color.parseColor("#4E55D7"))
        spannableString.setSpan(color,9,13, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.toolbarTitle.text = spannableString
    }

    private fun setProfilePhoto(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.getLoggedUser()
        }
        viewModel.loggedUser.observe(viewLifecycleOwner){
            if(it?.imageUri != ""){
                binding.accountImage.setImageURI(it?.imageUri?.toUri())
            }
        }
    }

    private fun setupSearchView(){
        viewModel.suggestions.observe(viewLifecycleOwner){ suggestions ->
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,suggestions)
            binding.autoComplete.setAdapter(arrayAdapter)
        }
    }

    private fun setupProductsRecyclerView(){
        with(binding){
            recyclerView.adapter = adapter
            viewModel.data.observe(viewLifecycleOwner){
                adapter.items = it
            }
        }
    }

    private fun setupCategoriesRecyclerVIew(){
        categoriesListAdapter = CategoriesAdapter()
        binding.recyclerViewCategories.adapter = categoriesListAdapter
        categoriesListAdapter.onItemClickListener = {}
        viewModel.categories.observe(viewLifecycleOwner){
            categoriesListAdapter.submitList(it)
        }
    }


    private fun clickListener(){
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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