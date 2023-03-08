package com.vladimir_tsurko.domain.usecases

import com.vladimir_tsurko.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.domain.repository.Repository
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): ProductsHorisontalItem = repository.getBrands()

}