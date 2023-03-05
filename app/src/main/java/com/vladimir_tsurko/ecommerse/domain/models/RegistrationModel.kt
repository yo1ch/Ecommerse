package com.vladimir_tsurko.ecommerse.domain.models

import androidx.room.PrimaryKey

data class RegistrationModel(
    val firstName: String,
    val secondName: String,
    val email: String,
    val password: String,
)
