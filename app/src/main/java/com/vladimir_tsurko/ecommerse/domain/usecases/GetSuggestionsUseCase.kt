package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.models.SuggestionsModel
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class GetSuggestionsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): SuggestionsModel = repository.getSuggestions()

}