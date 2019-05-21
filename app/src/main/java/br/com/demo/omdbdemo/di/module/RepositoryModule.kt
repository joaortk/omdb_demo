package br.com.demo.omdbdemo.di.module

import br.com.demo.omdbdemo.data.api.OmdbAPI
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import br.com.demo.omdbdemo.domain.repository.impl.OmdbRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideOmdbRepository(api: OmdbAPI): OmdbRepository {
        return OmdbRepositoryImpl(api)
    }
}