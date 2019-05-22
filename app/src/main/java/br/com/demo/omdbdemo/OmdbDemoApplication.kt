package br.com.demo.omdbdemo

import android.app.Application
import br.com.demo.omdbdemo.di.component.DaggerOmdbComponent
import br.com.demo.omdbdemo.di.component.OmdbComponent
import br.com.demo.omdbdemo.di.module.OmdbModule

class OmdbDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerOmdbComponent
                .builder()
                .omdbModule(OmdbModule(this))
                .build()
    }

    companion object {
        lateinit var appComponent: OmdbComponent
    }
}