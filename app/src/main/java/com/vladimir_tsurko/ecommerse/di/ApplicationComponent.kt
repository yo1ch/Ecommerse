package com.vladimir_tsurko.ecommerse.di

import android.app.Application
import com.vladimir_tsurko.ecommerse.presentation.fragments.FragmentSignIn
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

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}