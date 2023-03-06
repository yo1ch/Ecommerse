package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.models.DetailsModel
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): DetailsModel = repository.getDetails()

}