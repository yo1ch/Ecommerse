package com.vladimir_tsurko.ecommerse.di

import androidx.lifecycle.ViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel


}