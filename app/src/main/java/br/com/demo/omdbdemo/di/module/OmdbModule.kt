package br.com.demo.omdbdemo.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class OmdbModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

}