package com.vladimir_tsurko.ecommerse.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.Navigation
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.data.local.UserDao
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.data.mappers.ProductsMapper
import com.vladimir_tsurko.ecommerse.data.remote.ProductsApi
import com.vladimir_tsurko.ecommerse.data.remote.dto.FlashSaleItemsListDto
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem
import com.vladimir_tsurko.ecommerse.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val preferences: SharedPreferences,
    private val productsApi: ProductsApi,
    private val mapper: ProductsMapper,
): Repository {

    override suspend fun registerUser(userEntity: UserEntity) {
        userDao.registerUser(userEntity)
    }

    override suspend fun getUser(firstName: String): UserEntity? {
        return userDao.getUser(firstName)
    }

    override fun saveLoggedUser(firstName: String, password: String){
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