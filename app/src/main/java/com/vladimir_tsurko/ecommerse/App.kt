package com.vladimir_tsurko.ecommerse

import android.app.Application
import com.vladimir_tsurko.ecommerse.di.DaggerApplicationComponent

class App: Application() {
    val component by lazy{
        DaggerApplicationComponent.factory().create(this)
    }
}