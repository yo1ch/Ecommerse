package com.vladimir_tsurko.ecommerse.data.mappers

import android.transition.Slide
import com.denzcoskun.imageslider.models.SlideModel
import com.vladimir_tsurko.ecommerse.data.local.UserEntity
import com.vladimir_tsurko.ecommerse.data.remote.dto.*
import com.vladimir_tsurko.ecommerse.domain.models.*
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

    fun mapDetailsDtoToDetailsModel(detailsDto: DetailsDto): DetailsModel{
        return DetailsModel(
            rating = detailsDto.rating,
            price = detailsDto.price,
            number_of_reviews = detailsDto.number_of_reviews,
            name = detailsDto.name,
            image_urls = mapImageUrlsToSlideModel(detailsDto.image_urls),
            description = detailsDto.description,
            colors = mapColorsToColorModelList(detailsDto.colors)
        )
    }

    fun mapSuggestionsDtoToSuggestionsModel(suggestionsDto: SuggestionsDto): SuggestionsModel{
        return SuggestionsModel(suggestionsDto.words)
    }

    private fun mapImageUrlsToSlideModel(imageUrls: List<String>): List<SlideModel>{
        val slideModelList = mutableListOf<SlideModel>()
        imageUrls.forEach { color ->
            slideModelList.add(
                SlideModel(color)
            )
        }
        return slideModelList
    }

    private fun mapColorsToColorModelList(colors: List<String>): List<ColorModel>{

        val colorModelList = mutableListOf<ColorModel>()
        colors.forEachIndexed { index, element ->
            colorModelList.add(
                ColorModel(
                    id = index,
                    color = element,
                    isSelected = index==0
                )
            )
        }
        return colorModelList
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