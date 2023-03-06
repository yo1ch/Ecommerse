package com.vladimir_tsurko.ecommerse.di

import android.app.Application
import com.vladimir_tsurko.ecommerse.presentation.MainActivity
import com.vladimir_tsurko.ecommerse.presentation.fragments.*
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

    fun inject(fragment: UserInformationFragment)

    fun inject(fragment: DetailsFragment)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}