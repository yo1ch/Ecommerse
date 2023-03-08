package com.vladimir_tsurko.ecommerse.domain.repository

import com.vladimir_tsurko.ecommerse.domain.models.*


interface Repository {

    suspend fun registerUser(registrationModel: RegistrationModel): String

    suspend fun login(firstName: String, password: String): String

    fun getBrands(): ProductsHorisontalItem

    fun getCategories(): List<CategoryModel>

    fun checkLoggedUser(): Boolean

    fun logout()

    suspend fun updateUserPhoto(newImageUri: String)

    suspend fun getLoggedUser():UserModel?

    suspend fun getLatest(): ProductsHorisontalItem

    suspend fun getFlashSale(): ProductsHorisontalItem

    suspend fun getDetails(): DetailsModel

    suspend fun getSuggestions(): SuggestionsModel

}