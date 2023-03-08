package com.vladimir_tsurko.domain.usecases

import com.vladimir_tsurko.domain.models.RegistrationModel
import com.vladimir_tsurko.domain.repository.Repository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(registrationModel: RegistrationModel): String = repository.registerUser(registrationModel)

}