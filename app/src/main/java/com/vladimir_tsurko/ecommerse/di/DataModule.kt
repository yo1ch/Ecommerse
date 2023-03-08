package com.vladimir_tsurko.ecommerse.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.vladimir_tsurko.data.RepositoryImpl
import com.vladimir_tsurko.data.local.UserDao
import com.vladimir_tsurko.data.local.UserDb
import com.vladimir_tsurko.data.remote.RetrofitInstance
import com.vladimir_tsurko.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @Binds
    fun bindRepository(implementation: RepositoryImpl): Repository

    companion object{

        @Provides
        fun provideUserDao(
            application: Application
        ): UserDao {
            return UserDb.getInstance(application).UserDao()
        }

        @Provides
        fun provideSharedPrefs(
            application: Application
        ): SharedPreferences{
            return application.getSharedPreferences("Auth_data", Context.MODE_PRIVATE)
        }

        @Provides
        fun provideApiService(): com.vladimir_tsurko.data.remote.ProductsApi {
            return RetrofitInstance.apiService
        }


    }

}