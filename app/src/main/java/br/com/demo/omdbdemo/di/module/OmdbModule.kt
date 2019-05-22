package br.com.demo.omdbdemo.di.module

import android.content.Context
import br.com.demo.omdbdemo.common.provider.ResourceProvider
import br.com.demo.omdbdemo.common.provider.ResourceProviderImpl
import dagger.Module
import dagger.Provides

@Module
class OmdbModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }
}