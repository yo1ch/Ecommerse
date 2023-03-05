package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentHomeBinding
import com.vladimir_tsurko.ecommerse.presentation.MainActivity
import com.vladimir_tsurko.ecommerse.presentation.adapters.MainScreenAdapter
import com.vladimir_tsurko.ecommerse.presentation.adapters.ProductsAdapter
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.HomeViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding== null")

    private val adapter = MainScreenAdapter{
        Toast.makeText(activity, "Successful registration", Toast.LENGTH_SHORT).show()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.showBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        with(binding){
//            val itemAnimator = binding.recyclerView.itemAnimator
//            if(itemAnimator is DefaultItemAnimator){
//                itemAnimator.supportsChangeAnimations = false
//            }
            recyclerView.adapter = adapter
            viewModel.data.observe(viewLifecycleOwner){
                adapter.items = it
            }
        }



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