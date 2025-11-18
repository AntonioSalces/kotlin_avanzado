package com.turingalan.pokemon.data.repository

import com.turingalan.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun readOne(id:Long): Pokemon?
    suspend fun readAll():List<Pokemon>
    fun observe(): Flow<List<Pokemon>>
}