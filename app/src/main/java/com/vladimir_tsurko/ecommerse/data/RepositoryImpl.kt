package com.vladimir_tsurko.ecommerse.data


import android.content.SharedPreferences
import com.google.gson.Gson
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.data.local.UserDao
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.data.mappers.Mapper
import com.vladimir_tsurko.ecommerse.data.remote.ProductsApi
import com.vladimir_tsurko.ecommerse.domain.models.*
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import com.vladimir_tsurko.ecommerse.utils.Constants.LOGIN_FIRSTNAME_ERROR
import com.vladimir_tsurko.ecommerse.utils.Constants.LOGIN_SUCCESS
import com.vladimir_tsurko.ecommerse.utils.Constants.REGISTRATION_ERROR
import com.vladimir_tsurko.ecommerse.utils.Constants.REGISTRATION_SUCCESS
import com.vladimir_tsurko.ecommerse.utils.Constants.WRONG_PASSWORD_ERROR
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val preferences: SharedPreferences,
    private val productsApi: ProductsApi,
    private val mapper: Mapper,
) : Repository {

    override suspend fun registerUser(registrationModel: RegistrationModel): String {
        val user = getUserFromDb(registrationModel.firstName)
        return if (user != null) {
            REGISTRATION_ERROR
        } else {
            val userEntity = mapper.mapRegistrationModelToUserEntity(registrationModel)
            userDao.registerUser(userEntity)
            saveLoggedUser(userEntity)
            REGISTRATION_SUCCESS
        }

    }

    override suspend fun login(firstName: String, password: String): String {
        val user = getUserFromDb(firstName)
        return if (user == null) {
            LOGIN_FIRSTNAME_ERROR
        } else {
            if (password == user.password) {
                saveLoggedUser(user)
                LOGIN_SUCCESS
            } else WRONG_PASSWORD_ERROR
        }
    }

    override fun checkLoggedUser(): Boolean {
        val firstName = preferences.getString("USER", "")
        return firstName != ""
    }

    override fun logout() {
        preferences.edit().clear().apply()
    }

    override suspend fun updateUserPhoto(newImageUri: String) {
        val loggedUserJson = preferences.getString("USER","")
        val userEntity = parseJsonToUserEntity(loggedUserJson)
        userEntity.imageUri = newImageUri
        saveLoggedUser(userEntity)
        userDao.updateUser(userEntity)
    }

    override suspend fun getLoggedUser(): UserModel? {
        val loggedUserJson = preferences.getString("USER","")
        val loggedUser = parseJsonToUserEntity(loggedUserJson)
        return mapper.mapUserEntityToUserModel(loggedUser)
    }

    override suspend fun getLatest(): ProductsHorisontalItem {
        return mapper.mapLatestItemsListDtoToProductsHorizontalItem(productsApi.getLatest())
    }

    override suspend fun getFlashSale(): ProductsHorisontalItem {
        return mapper.mapFlashSaleItemsListDtoToProductsHorizontalItem(productsApi.getFlashSale())
    }

    override suspend fun getDetails(): DetailsModel {
        return mapper.mapDetailsDtoToDetailsModel(productsApi.getDetails())
    }

    override suspend fun getSuggestions(): SuggestionsModel {
        return mapper.mapSuggestionsDtoToSuggestionsModel(productsApi.getSuggestions())
    }

    private suspend fun getUserFromDb(firstName: String): UserEntity? {
        return userDao.getUser(firstName)
    }

    private suspend fun saveLoggedUser(userEntity: UserEntity) {
        val userJson = parseUserEntityToJson(userEntity)
        preferences.edit().putString("USER", userJson).apply()
    }

    private fun parseUserEntityToJson(userEntity: UserEntity): String {
        val gson = Gson()
        return gson.toJson(userEntity)
    }

    private fun parseJsonToUserEntity(userJson: String?): UserEntity{
        val gson = Gson()
        return gson.fromJson(userJson, UserEntity::class.java)

    }

    override fun getBrands(): ProductsHorisontalItem {
        val brands = listOf(
            BrandsItem(
                id = 0,
                name = "Vans",
                imageUrl = "https://asset.brandfetch.io/id4pDar7o9/id1a6Qkd5F.jpeg"
            ),
            BrandsItem(
                id = 1,
                name = "New balance",
                imageUrl = "https://asset.brandfetch.io/idjR6yqXUb/idNxhmnlFq.jpeg"
            ),
            BrandsItem(
                id = 2,
                name = "Tesla",
                imageUrl = "https://asset.brandfetch.io/id2S-kXbuK/idWvKxYIpS.png"
            ),
            BrandsItem(
                id = 3,
                name = "Adidas",
                imageUrl = "https://asset.brandfetch.io/idyqQWKFVE/idPydV7D0U.png"
            )
        )
        return ProductsHorisontalItem(
            title = "Brands",
            products = brands
        )
    }

    override fun getCategories(): List<CategoryModel> {
        return listOf(
            CategoryModel(
                name = "Phones",
                image = R.drawable.tab_phones,
            ),
            CategoryModel(
                name = "Headphones",
                image = R.drawable.tab_headphones,
            ),
            CategoryModel(
                name = "Games",
                image = R.drawable.tab_games,
            ),
            CategoryModel(
                name = "Cars",
                image = R.drawable.tab_cars,
            ),
            CategoryModel(
                name = "Furniture",
                image = R.drawable.tab_furniture,
            ),
            CategoryModel(
                name = "Kids",
                image = R.drawable.tab_kids,
            ),
            CategoryModel(
                name = "Phones",
                image = R.drawable.tab_phones,
            ),
        )
    }

}