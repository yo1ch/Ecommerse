package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentSignInBinding
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.LoginViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class FragmentSignIn : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LoginViewModel

    private val component by lazy{
        (requireActivity().application as App).component
    }


    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
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