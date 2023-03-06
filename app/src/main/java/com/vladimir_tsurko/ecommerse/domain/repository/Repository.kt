package com.vladimir_tsurko.ecommerse.domain.repository

import androidx.lifecycle.LiveData
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.data.remote.dto.FlashSaleItemsListDto
import com.vladimir_tsurko.ecommerse.domain.models.BrandsItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.RegistrationModel
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem

interface Repository {

    suspend fun registerUser(registrationModel: RegistrationModel): String

    suspend fun login(firstName: String, password: String): String

    fun getBrands(): ProductsHorisontalItem

    fun checkLoggedUser(): Boolean

    fun logout()

    suspend fun getLatest(): ProductsHorisontalItem

    suspend fun getFlashSale(): ProductsHorisontalItem

}