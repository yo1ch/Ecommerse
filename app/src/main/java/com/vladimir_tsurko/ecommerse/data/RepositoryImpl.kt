package com.vladimir_tsurko.ecommerse.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.Navigation
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.data.local.UserDao
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val preferences: SharedPreferences,
): Repository {

    override suspend fun registerUser(userEntity: UserEntity) {
        userDao.registerUser(userEntity)
    }

    override suspend fun getUser(firstName: String): UserEntity? {
        return userDao.getUser(firstName)
    }

    override fun saveLoggedUser(firstName: String, password: String){
        preferences.edit().putString("USERNAME", firstName).putString("PASSWORD", password).apply()
    }

    override fun checkLoggedUser(): Boolean {
        val getLogin = preferences.getString("USERNAME","")
        val getPassword = preferences.getString("PASSWORD","")
        return getLogin != "" && getPassword != ""
    }

    override fun logout() {
        preferences.edit().clear().apply()
    }


}