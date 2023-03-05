package com.vladimir_tsurko.ecommerse.data


import android.content.SharedPreferences
import com.vladimir_tsurko.ecommerse.data.local.UserDao
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.data.mappers.Mapper
import com.vladimir_tsurko.ecommerse.data.remote.ProductsApi
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.RegistrationModel
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
): Repository {

    override suspend fun registerUser(registrationModel: RegistrationModel): String {
        val user = getUser(registrationModel.firstName)
        return if(user != null){
            REGISTRATION_ERROR
        } else{
            val userEntity = mapper.mapRegistrationModelToUserEntity(registrationModel)
            userDao.registerUser(userEntity)
            saveLoggedUser(userEntity.firstName, userEntity.password)
            REGISTRATION_SUCCESS
        }

    }

    override suspend fun login(firstName: String, password: String): String{
        val user = getUser(firstName)
        return if (user == null) {
            LOGIN_FIRSTNAME_ERROR
        } else {
            if (password == user.password) {
                saveLoggedUser(firstName, password)
                LOGIN_SUCCESS
            } else WRONG_PASSWORD_ERROR
        }
    }

    private suspend fun getUser(firstName: String): UserEntity? {
        return userDao.getUser(firstName)
    }

    private fun saveLoggedUser(firstName: String, password: String){
        preferences.edit().putString("USERNAME", firstName).putString("PASSWORD", password).apply()
    }

    override fun checkLoggedUser(): Boolean {
        val getLogin = preferences.getString("USERNAME","")
        val getPassword = preferences.getString("PASSWORD","")
        return getLogin != "" && getPassword != ""
    }

    override fun logout() {
        preferences.edit().clear().apply()
    }

    override suspend fun getLatest(): ProductsHorisontalItem {
        return mapper.mapLatestItemsListDtoToProductsHorizontalItem(productsApi.getLatest())
    }

    override suspend fun getFlashSale(): ProductsHorisontalItem {
        return mapper.mapFlashSaleItemsListDtoToProductsHorizontalItem(productsApi.getFlashSale())
    }


}