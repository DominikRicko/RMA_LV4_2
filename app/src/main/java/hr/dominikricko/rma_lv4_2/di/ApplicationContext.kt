package hr.dominikricko.rma_lv4_2.di

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationContext : Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        startKoin{
            androidContext(this@ApplicationContext)
            modules(viewModelModule)
        }
    }
}