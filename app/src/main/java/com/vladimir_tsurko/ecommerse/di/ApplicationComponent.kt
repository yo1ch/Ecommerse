package com.vladimir_tsurko.ecommerse.di

import android.app.Application
import com.vladimir_tsurko.ecommerse.presentation.fragments.FragmentLogIn
import com.vladimir_tsurko.ecommerse.presentation.fragments.FragmentSignIn
import com.vladimir_tsurko.ecommerse.presentation.fragments.HomeFragment
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(fragment: FragmentSignIn)

    fun inject(fragment: FragmentLogIn)

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}