package com.vladimir_tsurko.domain.usecases

import com.vladimir_tsurko.domain.repository.Repository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() = repository.logout()
}