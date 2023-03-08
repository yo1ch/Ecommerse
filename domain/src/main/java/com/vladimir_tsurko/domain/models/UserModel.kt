package com.vladimir_tsurko.domain.models

data class UserModel(
    val firstName: String?,
    val secondName: String?,
    val email: String?,
    val password: String?,
    var imageUri: String? = "",
)