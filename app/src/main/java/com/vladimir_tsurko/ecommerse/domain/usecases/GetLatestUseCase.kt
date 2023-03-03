package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class GetLatestUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): ProductsHorisontalItem = repository.getLatest()
}