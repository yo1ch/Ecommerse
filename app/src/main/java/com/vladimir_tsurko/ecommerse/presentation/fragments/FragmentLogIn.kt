package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentLogInBinding
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import com.vladimir_tsurko.utils.Constants.LOGIN_SUCCESS
import kotlinx.android.synthetic.main.fragment_log_in.view.*
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
        setupClickListeners()
        setupIputErrors()
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

    private fun setupClickListeners(){
        binding.loginButton.setOnClickListener {
            viewModel.login(
                inputFirstName = binding.firstName.et_name.text.toString(),
                inputPassword = binding.password.et_password.text.toString(),
            )
            observeRegistrationStatus()
        }
    }

    private fun observeRegistrationStatus(){
        viewModel.loginStatus.observe(viewLifecycleOwner){
            if(it == LOGIN_SUCCESS){
                navigateSafe(R.id.action_fragmentLogIn_to_main_graph)
            } else{
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupIputErrors(){
        viewModel.errorInputFirstName.observe(viewLifecycleOwner){ errorMessage ->
            if(errorMessage.isEmpty()){
                binding.etName.error = null
            } else {
                binding.etName.error = errorMessage
            }
        }
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputFirstName()
            }
            override fun afterTextChanged(s: Editable?) {
            }

        })

        viewModel.errorInputPassword.observe(viewLifecycleOwner){ errorMessage ->

            if(errorMessage.isEmpty()){
                binding.etPassword.error = null
            } else {
                binding.etPassword.error = errorMessage
            }
        }
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputPassword()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun Fragment.navigateSafe(@IdRes resIdRes: Int, args: Bundle? = null){
        val controller = findNavController()
        val currentDestination = (controller.currentDestination as? FragmentNavigator.Destination)?.className
            ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className

        if(currentDestination == this.javaClass.name){
            controller.navigate(resIdRes, args)
        }
    }

}