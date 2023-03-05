package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.models.BrandsItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): ProductsHorisontalItem = repository.getBrands()

}