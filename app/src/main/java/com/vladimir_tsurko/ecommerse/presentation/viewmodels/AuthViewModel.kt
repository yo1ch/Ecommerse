package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.*
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.domain.usecases.*
import com.vladimir_tsurko.ecommerse.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val saveLoggedUserUseCase: SaveLoggedUserUseCase,
    private val checkLoggedUserUseCase: CheckLoggedUserUseCase,
    private val logOutUseCase: LogOutUseCase,
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
                saveLoggedUserUseCase(firstName, password)
            } else _loginStatus.value = Resource.Error("Wrong password")
        }
    }

    fun logout(){
        logOutUseCase()
    }


    fun checkLoggedUser(): Boolean{
        return checkLoggedUserUseCase()
    }



}