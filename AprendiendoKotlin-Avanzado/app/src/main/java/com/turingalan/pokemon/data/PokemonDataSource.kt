package com.turingalan.pokemon.data

import com.turingalan.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {
    suspend fun addAll(pokemonList: List<Pokemon>)

    fun observe(): Flow<List<Pokemon>>
    suspend fun readAll(): Result<List<Pokemon>>
    suspend fun readOne(id: Long): Result<Pokemon>
    suspend fun readOne(name: String): Result<Pokemon>
}
