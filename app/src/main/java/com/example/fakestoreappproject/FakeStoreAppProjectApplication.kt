package com.example.fakestoreappproject

import android.app.Application
import com.example.fakestoreappproject.di.appModule
import com.example.fakestoreappproject.ui.utils.NetworkMonitor
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FakeStoreAppProjectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FakeStoreAppProjectApplication)
            modules(appModule)
            val networkChecker: NetworkMonitor = getKoin().get()
            networkChecker.startMonitoring()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        val networkChecker: NetworkMonitor = getKoin().get()
        networkChecker.stopMonitoring()
    }
}