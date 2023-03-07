package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentSignInBinding
import com.vladimir_tsurko.ecommerse.presentation.MainActivity
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import com.vladimir_tsurko.ecommerse.utils.Constants.REGISTRATION_ERROR
import com.vladimir_tsurko.ecommerse.utils.Constants.REGISTRATION_SUCCESS
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import javax.inject.Inject

class FragmentSignIn : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: AuthViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }


    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.hideBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
        loginSpan()
        setupInputErrors()
        setupClickListeners()
        checkLoggedUser()
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

    private fun checkLoggedUser() {
        val isUserLogged = viewModel.checkLoggedUser()
        if (isUserLogged) {
            findNavController().navigate(R.id.action_fragmentSignIn_to_main_graph)
        }
    }

    private fun loginSpan() {
        val spannableText = SpannableString("Already have an account? Log in")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_fragmentSignIn_to_fragmentLogIn)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#254FE6")
                ds.isUnderlineText = false
            }
        }
        spannableText.setSpan(clickableSpan, 25, 31, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.loginText.text = spannableText
        binding.loginText.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupClickListeners() {
        binding.registrationButton.setOnClickListener {
            viewModel.registerUser(
                inputFirstName = binding.firstName.et_sign_name.text.toString(),
                inputSecondName = binding.secondName.et_sign_second_name.text.toString(),
                inputEmail = binding.email.et_sign_email.text.toString(),
                inputPassword = binding.password.et_sign_password.text.toString()
            )
            observeRegistrationStatus()
        }
    }

    private fun observeRegistrationStatus() {
        viewModel.registrationStatus.observe(viewLifecycleOwner) {
            if (it == REGISTRATION_SUCCESS) {
                navigateSafe(R.id.action_fragmentSignIn_to_main_graph)
            } else {
                Toast.makeText(activity, REGISTRATION_ERROR, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupInputErrors() {
        viewModel.errorInputFirstName.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isEmpty()) {
                binding.etSignName.error = null
            } else {
                binding.etSignName.error = errorMessage
            }

        }
        binding.etSignName.addTextChangedListener(object : TextWatcher {
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

        viewModel.errorInputSecondName.observe(viewLifecycleOwner) { errorMessage ->

            if (errorMessage.isEmpty()) {
                binding.etSignSecondName.error = null
            } else {
                binding.etSignSecondName.error = errorMessage
            }

        }
        binding.etSignSecondName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputSecondName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        viewModel.errorInputEmail.observe(viewLifecycleOwner) { errorMessage ->

            if (errorMessage.isEmpty()) {
                binding.etSignEmail.error = null
            } else {
                binding.etSignEmail.error = errorMessage
            }
        }
        binding.etSignEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputEmail()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        viewModel.errorInputPassword.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isEmpty()) {
                binding.etSignPassword.error = null
            } else {
                binding.etSignPassword.error = errorMessage
            }

        }
        binding.etSignPassword.addTextChangedListener(object : TextWatcher {
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


    private fun Fragment.navigateSafe(@IdRes resIdRes: Int, args: Bundle? = null) {
        val controller = findNavController()
        val currentDestination =
            (controller.currentDestination as? FragmentNavigator.Destination)?.className
                ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className

        if (currentDestination == this.javaClass.name) {
            controller.navigate(resIdRes, args)
        }
    }

}