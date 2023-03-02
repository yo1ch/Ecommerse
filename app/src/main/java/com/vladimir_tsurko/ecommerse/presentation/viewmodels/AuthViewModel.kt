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

    private val _errorInputFirstName = MutableLiveData<String>()
    val errorInputFirstName: LiveData<String>
        get() = _errorInputFirstName

    private val _errorInputSecondName = MutableLiveData<String>()
    val errorInputSecondName: LiveData<String>
        get() = _errorInputSecondName

    private val _errorInputEmail = MutableLiveData<String>()
    val errorInputEmail: LiveData<String>
        get() = _errorInputEmail

    private val _errorInputPassword = MutableLiveData<String>()
    val errorInputPassword: LiveData<String>
        get() = _errorInputPassword


    fun registerUser(inputFirstName: String, inputSecondName: String, inputEmail: String, inputPassword: String) = viewModelScope.launch{
        val firstName = parseString(inputFirstName)
        val secondName = parseString(inputSecondName)
        val email = parseString(inputEmail)
        val password = parseString(inputPassword)
        val isFieldsValid = validateRegistrationInput(firstName, secondName, email, password)
        if(isFieldsValid){
            val user = getUserUseCase(firstName)
            if(user != null){
                _registrationStatus.value = Resource.Error("This user are already exist")
            } else {
                registerUserUseCase(UserEntity(
                    firstName = firstName,
                    secondName = secondName,
                    email = email,
                    password = password
                ))
                saveLoggedUserUseCase(firstName, password)
                _registrationStatus.value = Resource.Success(null)
            }
        }
    }

    fun login(inputFirstName: String, inputPassword: String) = viewModelScope.launch {
        val firstName = parseString(inputFirstName)
        val password = parseString(inputPassword)
        val isFieldsValid = validateLoginInput(firstName, password)
        if(isFieldsValid){
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
    }



    fun logout(){
        logOutUseCase()
    }


    fun checkLoggedUser(): Boolean{
        return checkLoggedUserUseCase()
    }

    private fun parseString(inputString: String?): String{
        return inputString?.trim() ?: ""
    }

    private fun validateRegistrationInput(firstName: String, secondName: String, email: String, password: String): Boolean{
        var result = true
        if (firstName.isBlank()) {
            _errorInputFirstName.value = "This field should not be empty"
            result = false
        }
        if (secondName.isBlank()) {
            _errorInputSecondName.value = "This field should not be empty"
            result = false
        }
        if (email.isBlank()) {
            _errorInputEmail.value = "This field should not be empty"
            result = false
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _errorInputEmail.value = "Wrong email"
            result = false
        }
        if (password.isBlank()) {
            _errorInputPassword.value = "This field should not be empty"
            result = false
        }
        return result
    }

    private fun validateLoginInput(firstName: String, password: String): Boolean{
        var result = true
        if (firstName.isBlank()) {
            _errorInputFirstName.value = "This field should not be empty"
            result = false
        }
        if (password.isBlank()) {
            _errorInputPassword.value = "This field should not be empty"
            result = false
        }
        return result
    }

    fun resetErrorInputFirstName(){
        _errorInputFirstName.value = ""
    }

    fun resetErrorInputSecondName(){
        _errorInputSecondName.value = ""
    }

    fun resetErrorInputEmail(){
        _errorInputEmail.value = ""
    }

    fun resetErrorInputPassword(){
        _errorInputPassword.value = ""
    }



}