package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentLogInBinding
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import com.vladimir_tsurko.ecommerse.utils.Resource
import javax.inject.Inject

class FragmentLogIn : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: AuthViewModel

    private val component by lazy{
        (requireActivity().application as App).component
    }


    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
        binding.loginButton.setOnClickListener {
            viewModel.login(
                firstName = binding.firstName.text.toString(),
                password = binding.password.text.toString(),
            )
            viewModel.loginStatus.observe(viewLifecycleOwner){
                when(it) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success ->{
                        Toast.makeText(activity, "Successful login", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(binding.root).navigate(R.id.action_fragmentLogIn_to_homeFragment)
                    }

                    is Resource.Error -> Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
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