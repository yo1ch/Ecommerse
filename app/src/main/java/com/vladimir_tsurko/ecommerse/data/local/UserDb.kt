package com.vladimir_tsurko.ecommerse.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDb: RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDb? = null

        fun getInstance(context: Context): UserDb{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDb::class.java,
                    "users_database",
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

    }


}