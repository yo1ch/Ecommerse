package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.domain.models.RegistrationModel
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(registrationModel: RegistrationModel): String = repository.registerUser(registrationModel)

}