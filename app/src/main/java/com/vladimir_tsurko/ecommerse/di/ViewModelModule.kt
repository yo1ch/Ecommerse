package com.vladimir_tsurko.ecommerse.di

import androidx.lifecycle.ViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.DetailsViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.HomeViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.UserDetailsViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModule(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    fun bindUserDetailsViewModule(viewModel: UserDetailsViewModel): ViewModel


}