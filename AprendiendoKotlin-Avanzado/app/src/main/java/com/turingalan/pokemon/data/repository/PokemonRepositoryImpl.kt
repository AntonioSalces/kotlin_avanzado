package com.turingalan.pokemon.data.repository

import com.turingalan.pokemon.data.local.PokemonLocalDataSource
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.PokemonDataSource
import com.turingalan.pokemon.di.LocalDataSource
import com.turingalan.pokemon.di.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    @RemoteDataSource private val remoteDataSource: PokemonDataSource,
    @LocalDataSource private val localDataSource: PokemonDataSource,
    private val scope: CoroutineScope
): PokemonRepository {
    override suspend fun readOne(id: Long): Result<Pokemon> {
        return remoteDataSource.readOne(id)
    }

    override suspend fun readOne(name: String): Pokemon? {
        return remoteDataSource.readOne(name)
    }

    override suspend fun readAll(): List<Pokemon> {
        return remoteDataSource.readAll()
    }

    override fun observe(): Flow<List<Pokemon>> {
        scope.launch {
            refresh()
        }
        return localDataSource.observe()
    }

    private suspend fun refresh() {
        val remotePokemon = remoteDataSource.readAll()
        localDataSource.addAll(remotePokemon)
    }
}