package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val application: Application,
    private val registerUserUseCase: RegisterUserUseCase,
): ViewModel() {

    fun registerUser(userEntity: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            registerUserUseCase(userEntity)
        }
    }


}