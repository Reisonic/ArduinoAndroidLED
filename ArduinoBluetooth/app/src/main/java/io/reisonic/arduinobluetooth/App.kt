package io.reisonic.arduinobluetooth

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * This class used by for Koin technology
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-22
 */
class App : Application(){

    val appModule = module {
        single { MainPresenter() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}
