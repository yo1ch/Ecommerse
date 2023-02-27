package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.domain.usecases.GetUserUseCase
import com.vladimir_tsurko.ecommerse.domain.usecases.RegisterUserUseCase
import com.vladimir_tsurko.ecommerse.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val application: Application,
    private val registerUserUseCase: RegisterUserUseCase,
    private val getUserUseCase: GetUserUseCase,
): ViewModel() {


    private val _registrationStatus = MutableLiveData<Resource<UserEntity?>>()
    val registrationStatus: LiveData<Resource<UserEntity?>>
        get() = _registrationStatus



    fun registerUser(userEntity: UserEntity) = viewModelScope.launch{
        _registrationStatus.value = Resource.Loading()
        val user = getUserUseCase(userEntity.firstName)
        if(user != null){
            _registrationStatus.value = Resource.Error("This user are already exist")
        } else {
            registerUserUseCase(userEntity)
            _registrationStatus.value = Resource.Success(null)
        }
    }



}