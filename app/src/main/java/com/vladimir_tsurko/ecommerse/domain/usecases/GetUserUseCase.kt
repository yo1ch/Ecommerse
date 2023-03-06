package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: Repository
) {

    operator suspend fun invoke(firstName: String): UserEntity? = repository.getUser(firstName)

}