package com.vladimir_tsurko.domain.usecases

import com.vladimir_tsurko.domain.repository.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(firstName: String, password: String): String = repository.login(firstName, password)
}