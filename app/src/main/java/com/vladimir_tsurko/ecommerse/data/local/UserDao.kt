package com.vladimir_tsurko.ecommerse.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(userEntity: UserEntity)

    @Query("SELECT * FROM users_table WHERE firstName LIKE :firstName")
    suspend fun getUser(firstName: String): UserEntity?

}