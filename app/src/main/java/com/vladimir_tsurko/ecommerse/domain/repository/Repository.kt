package com.vladimir_tsurko.ecommerse.domain.repository

import androidx.lifecycle.LiveData
import com.vladimir_tsurko.ecommerse.data.local.UserEntity

interface Repository {


    suspend fun registerUser(userEntity: UserEntity)


}