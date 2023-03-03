package com.vladimir_tsurko.ecommerse.domain.repository

import androidx.lifecycle.LiveData
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

interface Repository {


    suspend fun registerUser(userEntity: UserEntity)

    suspend fun getUser(firstName: String): UserEntity?

    fun saveLoggedUser(firstName: String, password: String)

    fun checkLoggedUser(): Boolean

    fun logout()

    suspend fun getLatest(): ProductsHorisontalItem

}