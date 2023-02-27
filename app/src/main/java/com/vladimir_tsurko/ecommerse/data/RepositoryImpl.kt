package com.vladimir_tsurko.ecommerse.data

import com.vladimir_tsurko.ecommerse.data.local.UserDao
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val userDao: UserDao,
): Repository {

    override suspend fun registerUser(userEntity: UserEntity) {
        userDao.registerUser(userEntity)
    }

    override suspend fun getUser(firstName: String): UserEntity? {
        return userDao.getUser(firstName)
    }

}