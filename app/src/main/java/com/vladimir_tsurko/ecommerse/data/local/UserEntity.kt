package com.vladimir_tsurko.ecommerse.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val secondName: String,
    val email: String,
    val password: String,
)