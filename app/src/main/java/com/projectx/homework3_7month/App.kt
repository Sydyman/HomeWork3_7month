package com.projectx.homework3_7month

import android.app.Application
import com.projectx.homework3_7month.data.di.dataModules
import com.projectx.homework3_7month.domain.di.domainModule
import com.projectx.homework3_7month.presentation.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.logging.Level

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModules)
            modules(domainModule)
            modules(uiModule)
        }
    }

}