package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.ecommerse.domain.models.UserModel
import com.vladimir_tsurko.ecommerse.domain.usecases.GetLoggedUserUseCase
import com.vladimir_tsurko.ecommerse.domain.usecases.LogOutUseCase
import com.vladimir_tsurko.ecommerse.domain.usecases.UpdateUserPhotoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val updateUserPhotoUseCase: UpdateUserPhotoUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase,
): ViewModel() {


    private var _loggedUser = MutableLiveData<UserModel?>()
    val loggedUser: LiveData<UserModel?>
        get() = _loggedUser

    init {
        viewModelScope.launch {
            getLoggedUser()
        }
    }

    private suspend fun getLoggedUser() = viewModelScope.launch {
        _loggedUser.value = getLoggedUserUseCase()
    }

    suspend fun updateUserPhoto(newImageUri: String){
        updateUserPhotoUseCase(newImageUri)
    }


    fun logout() {
        logOutUseCase()
    }

}