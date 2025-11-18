package com.turingalan.pokemon.di

import com.turingalan.pokemon.data.local.PokemonLocalDataSource
import com.turingalan.pokemon.data.PokemonDataSource
import com.turingalan.pokemon.data.remote.PokemonRemoteDataSource
import com.turingalan.pokemon.data.repository.PokemonRepository
import com.turingalan.pokemon.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    @RemoteDataSource
    abstract fun bindsRemotePokemonDataSource(ds: PokemonRemoteDataSource): PokemonDataSource

    @Binds
    @Singleton
    @LocalDataSource
    abstract fun bindsLocalPokemonDataSource(ds: PokemonLocalDataSource): PokemonDataSource

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository
    //abstract fun bindPokemonRepository(repository: PokemonFakeRemoteRepository): PokemonRepository
    //abstract fun bindPokemonRepository(repository: PokemonInMemoryRepository): PokemonRepository
}
/**
 * Modulo nuevo porque no es abstracto y no se puede mezclar metodos abstractos con no  asbtaractos
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource