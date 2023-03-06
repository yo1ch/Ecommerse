package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class SaveLoggedUserUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(firstName: String, password: String) = repository.saveLoggedUser(firstName, password)
}