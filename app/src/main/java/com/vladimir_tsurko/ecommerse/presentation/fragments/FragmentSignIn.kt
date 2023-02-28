package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.databinding.FragmentSignInBinding
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import com.vladimir_tsurko.ecommerse.utils.Resource
import javax.inject.Inject

class FragmentSignIn : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: AuthViewModel

    private val component by lazy{
        (requireActivity().application as App).component
    }


    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
        loginSpan()
        val isUserLogged = viewModel.checkLoggedUser()
        if(isUserLogged){
            Navigation.findNavController(binding.root).navigate(R.id.action_fragmentSignIn_to_navigation)
        }



        binding.registrationButton.setOnClickListener {
            viewModel.registerUser(UserEntity(
                    firstName = binding.firstName.text.toString(),
                    secondName = binding.secondName.text.toString(),
                    email = binding.email.text.toString(),
                    password = binding.password.text.toString(),
                ))

            viewModel.registrationStatus.observe(viewLifecycleOwner){
                when(it) {

                    is Resource.Loading -> {

                    }

                    is Resource.Success ->{
                        Toast.makeText(activity, "Successful registration", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(binding.root).navigate(R.id.action_fragmentSignIn_to_navigation)
                    }

                    is Resource.Error -> Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loginSpan(){

        val spannableText = SpannableString("Please log in")

        val clickableSpan = object: ClickableSpan() {
            override fun onClick(widget: View) {
                Navigation.findNavController(binding.root).navigate(R.id.action_fragmentSignIn_to_fragmentLogIn)
            }
        }
        spannableText.setSpan(clickableSpan,7,13, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.loginText.text = spannableText
        binding.loginText.movementMethod = LinkMovementMethod.getInstance()

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