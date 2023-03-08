package com.vladimir_tsurko.ecommerse.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vladimir_tsurko.ecommerse.App
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.databinding.FragmentUserInformationBinding
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.UserDetailsViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserInformationFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserDetailsViewModel

    private val component by lazy{
        (requireActivity().application as App).component
    }


    private var _binding: FragmentUserInformationBinding? = null
    private val binding: FragmentUserInformationBinding
        get() = _binding ?: throw RuntimeException("FragmentUserInformationBinding== null")



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserDetailsViewModel::class.java]
        bindViews()
        setupBackButton()
        setupLogoutButton()
        setupImagePicker()

    }

    private fun bindViews(){
        viewModel.loggedUser.observe(viewLifecycleOwner){
            binding.userNameTextVIew.text = "${it?.firstName} ${it?.secondName}"
            if(it?.imageUri != ""){
                binding.userPhoto.setImageURI(it?.imageUri?.toUri())
            }
        }
    }

    private fun setupImagePicker(){
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
            if(uri!=null){
                binding.userPhoto.setImageURI(uri)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.updateUserPhoto(uri.toString())
                }
            }
        }
        binding.buttonUpload.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun setupLogoutButton(){
        binding.logoutElement.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_global_to_authGraph)
        }
    }

    private fun setupBackButton(){
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInformationBinding.inflate(inflater, container, false)
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