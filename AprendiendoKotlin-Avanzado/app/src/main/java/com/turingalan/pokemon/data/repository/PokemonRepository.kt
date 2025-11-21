package com.turingalan.pokemon.data.repository

import com.turingalan.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun readOne(id:Long): Result<Pokemon>
    suspend fun readOne(name:String): Result<Pokemon>
    suspend fun readAll(): Result<List<Pokemon>>
    fun observe(): Flow<Result<List<Pokemon>>>
}