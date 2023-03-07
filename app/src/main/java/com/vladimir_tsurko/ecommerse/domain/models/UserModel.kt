package com.vladimir_tsurko.ecommerse.domain.models

import android.net.Uri

data class UserModel(
    val firstName: String?,
    val secondName: String?,
    val email: String?,
    val password: String?,
    var imageUri: String? = "",
)