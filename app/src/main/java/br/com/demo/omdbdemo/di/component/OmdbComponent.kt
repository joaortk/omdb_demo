package br.com.demo.omdbdemo.di.component

import br.com.demo.omdbdemo.feature.home.view.MainActivity
import br.com.demo.omdbdemo.di.module.ApiModule
import br.com.demo.omdbdemo.di.module.NetworkModule
import br.com.demo.omdbdemo.di.module.OmdbModule
import br.com.demo.omdbdemo.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ApiModule::class,
        RepositoryModule::class,
        OmdbModule::class
    ]
)
interface OmdbComponent {
    fun inject(activity: MainActivity)
}