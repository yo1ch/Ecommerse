package com.vladimir_tsurko.domain.usecases

import com.vladimir_tsurko.domain.repository.Repository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): List<com.vladimir_tsurko.domain.models.CategoryModel> = repository.getCategories()

}