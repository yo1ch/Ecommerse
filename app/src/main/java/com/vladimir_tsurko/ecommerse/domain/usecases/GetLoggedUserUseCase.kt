package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.models.UserModel
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class GetLoggedUserUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): UserModel? = repository.getLoggedUser()

}