package com.vladimir_tsurko.ecommerse.data.mappers

import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.data.remote.dto.FlashSaleDto
import com.vladimir_tsurko.ecommerse.data.remote.dto.FlashSaleItemsListDto
import com.vladimir_tsurko.ecommerse.data.remote.dto.LatestDto
import com.vladimir_tsurko.ecommerse.data.remote.dto.LatestItemsListDto
import com.vladimir_tsurko.ecommerse.domain.models.FlashSaleItem
import com.vladimir_tsurko.ecommerse.domain.models.LatestItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.RegistrationModel
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapFlashSaleItemsListDtoToProductsHorizontalItem(flashSaleItemsListDto: FlashSaleItemsListDto): ProductsHorisontalItem{
        val productsList = flashSaleItemsListDto.flash_sale.mapIndexed{ index, item ->
            mapFlashSaleDtoToFlashSaleModel(item, index)
        }
        return ProductsHorisontalItem(
            title = "Flash Sale",
            products = productsList,
        )
    }

    fun mapLatestItemsListDtoToProductsHorizontalItem(latestItemsListDto: LatestItemsListDto): ProductsHorisontalItem{
        val productsList = latestItemsListDto.latest.mapIndexed { index, item ->
            mapLatestDtoToLatestModel(item, index)
        }
        return ProductsHorisontalItem(
            title = "Latest",
            products = productsList
        )
    }

    fun mapRegistrationModelToUserEntity(registrationModel: RegistrationModel): UserEntity{
        return UserEntity(
            firstName = registrationModel.firstName,
            secondName = registrationModel.secondName,
            email = registrationModel.email,
            password = registrationModel.password,
        )
    }


    private  fun mapLatestDtoToLatestModel(latestDto: LatestDto, latestDtoIndex: Int): LatestItem{
        return LatestItem(
            id = latestDtoIndex.toLong(),
            name = latestDto.name,
            category = latestDto.category,
            price = latestDto.price,
            image = latestDto.image_url,
        )
    }

    private fun mapFlashSaleDtoToFlashSaleModel(flashSaleDto: FlashSaleDto, flashSaleDtoIndex: Int): FlashSaleItem{
        return FlashSaleItem(
            id = flashSaleDtoIndex.toLong(),
            name = flashSaleDto.name,
            price = flashSaleDto.price,
            image = flashSaleDto.image_url,
            category = flashSaleDto.category,
            discount = flashSaleDto.discount,
        )
    }

}