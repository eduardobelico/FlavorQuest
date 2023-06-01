package com.example.flavorquest

import android.app.Application
import com.example.flavorquest.di.DataModule
import com.example.flavorquest.di.DomainModule
import com.example.flavorquest.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}