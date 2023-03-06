package com.vladimir_tsurko.ecommerse.di

import androidx.lifecycle.ViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindLoginViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel


}