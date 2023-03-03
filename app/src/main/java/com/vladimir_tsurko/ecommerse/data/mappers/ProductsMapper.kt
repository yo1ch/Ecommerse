package com.vladimir_tsurko.ecommerse.data.mappers

import com.vladimir_tsurko.ecommerse.data.remote.dto.LatestDto
import com.vladimir_tsurko.ecommerse.data.remote.dto.LatestItemsListDto
import com.vladimir_tsurko.ecommerse.domain.models.LatestItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import javax.inject.Inject

class ProductsMapper @Inject constructor() {


    fun mapLatestItemsListDtoToProductsHorizontalItem(latestItemsListDto: LatestItemsListDto): ProductsHorisontalItem{
        val productsList = latestItemsListDto.latest.mapIndexed { index, item ->
            mapLatestDtoToLatestModel(item, index)
        }
        return ProductsHorisontalItem(
            title = "Latest",
            products = productsList
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

}