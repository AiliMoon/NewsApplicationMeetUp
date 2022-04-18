package com.example.newsapplication

import android.app.Application
import com.example.newsapplication.di.*
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(retrofitModule, dataModule, viewModels, domainModule))
            androidContext(this@NewsApplication)
        }
    }
}