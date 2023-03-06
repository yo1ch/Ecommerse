package com.vladimir_tsurko.ecommerse.data.remote

import com.vladimir_tsurko.ecommerse.data.remote.dto.DetailsDto
import com.vladimir_tsurko.ecommerse.data.remote.dto.FlashSaleItemsListDto
import com.vladimir_tsurko.ecommerse.data.remote.dto.LatestItemsListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): LatestItemsListDto

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSale(): FlashSaleItemsListDto

    @GET("https://run.mocky.io/v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetails(): DetailsDto

}