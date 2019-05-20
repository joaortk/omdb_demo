package br.com.demo.omdbdemo

import android.app.Application
import br.com.demo.omdbdemo.di.component.OmdbComponent

class OmdbDemoApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        
//        appComponent =DaggerOmdbComponent
//            .builder()
////            .consultantModule(OmbdComponent(this))
//            .build()
    }

    companion object {
        lateinit var appComponent: OmdbComponent
    }
}