package com.vladimir_tsurko.ecommerse.domain.usecases

import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class UpdateUserPhotoUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(newImageUri: String) = repository.updateUserPhoto(newImageUri)

}