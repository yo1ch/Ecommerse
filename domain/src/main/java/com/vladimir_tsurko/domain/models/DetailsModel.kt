package com.vladimir_tsurko.domain.models

import com.denzcoskun.imageslider.models.SlideModel

data class DetailsModel(
    val colors: List<ColorModel>,
    val description: String,
    val image_urls: List<SlideModel>,
    val name: String,
    val number_of_reviews: Int,
    val price: Int,
    val rating: Double
)