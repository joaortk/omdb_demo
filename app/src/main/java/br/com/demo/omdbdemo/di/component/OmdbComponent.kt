package br.com.demo.omdbdemo.di.component

import br.com.demo.omdbdemo.di.module.*
import br.com.demo.omdbdemo.feature.home.view.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        NetworkModule::class,
        ApiModule::class,
        RepositoryModule::class,
        OmdbModule::class
    ]
)
interface OmdbComponent {
    fun inject(activity: HomeActivity)
}