package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.models.CategoryModel
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): List<CategoryModel> = repository.getCategories()

}