package com.vladimir_tsurko.ecommerse.data.remote

import com.vladimir_tsurko.ecommerse.data.remote.dto.LatestItemsListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): LatestItemsListDto

}