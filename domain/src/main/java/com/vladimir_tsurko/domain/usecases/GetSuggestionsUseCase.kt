package com.vladimir_tsurko.domain.usecases


import com.vladimir_tsurko.domain.models.SuggestionsModel
import com.vladimir_tsurko.domain.repository.Repository
import javax.inject.Inject

class GetSuggestionsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): SuggestionsModel = repository.getSuggestions()

}