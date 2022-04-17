package com.example.newsapplication

import android.app.Application
import com.example.newsapplication.di.repositoryModule
import com.example.newsapplication.di.retrofitModule
import com.example.newsapplication.di.roomModule
import com.example.newsapplication.di.viewModels
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(retrofitModule, repositoryModule, roomModule, viewModels))
            androidContext(this@NewsApplication)
        }
    }
}