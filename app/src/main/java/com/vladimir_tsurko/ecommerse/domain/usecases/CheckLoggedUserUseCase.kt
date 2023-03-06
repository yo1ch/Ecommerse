package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class CheckLoggedUserUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Boolean = repository.checkLoggedUser()
}