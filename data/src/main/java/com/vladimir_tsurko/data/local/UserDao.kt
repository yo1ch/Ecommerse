package com.vladimir_tsurko.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("SELECT * FROM users_table WHERE firstName LIKE :firstName")
    suspend fun getUser(firstName: String): UserEntity?

}