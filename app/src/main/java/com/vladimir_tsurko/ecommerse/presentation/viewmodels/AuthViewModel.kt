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
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val application: Application,
    private val registerUserUseCase: RegisterUserUseCase,
    private val getUserUseCase: GetUserUseCase,
): ViewModel() {


    private val _registrationStatus = MutableLiveData<Resource<UserEntity?>>()
    val registrationStatus: LiveData<Resource<UserEntity?>>
        get() = _registrationStatus

    private val _loginStatus = MutableLiveData<Resource<UserEntity?>>()
    val loginStatus: LiveData<Resource<UserEntity?>>
        get() = _loginStatus



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

    fun login(firstName: String, password: String) = viewModelScope.launch {
        _loginStatus.value = Resource.Loading()
        val user = getUserUseCase(firstName)
        if(user == null){
            _loginStatus.value = Resource.Error("There are no user with such firstname")
        } else{
            if(password == user.password){
                _loginStatus.value = Resource.Success(null)
            } else _loginStatus.value = Resource.Error("Wrong password")
        }
    }



}