package com.turingalan.pokemon.data.local

import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.PokemonDataSource
import com.turingalan.pokemon.di.LocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val scope: CoroutineScope
):
    PokemonDataSource {

        private val _pokemon: MutableList<Pokemon> = mutableListOf()
        private val _pokemonFlow: MutableSharedFlow<List<Pokemon>> = MutableSharedFlow<List<Pokemon>>()
    override suspend fun addAll(pokemonList: List<Pokemon>) {
        _pokemon.addAll(pokemonList)
        _pokemonFlow.emit(_pokemon)
    }

    override fun observe(): Flow<List<Pokemon>> {
        scope.launch { _pokemonFlow.emit(_pokemon) }

        return _pokemonFlow
    }

    override suspend fun readAll(): Result<List<Pokemon>> {
        return Result.success(_pokemon.toList())
    }

    override suspend fun readOne(id: Long): Result<Pokemon> {
        val pokemon = _pokemon.firstOrNull() { pokemon ->
            pokemon.id == id
        }
        pokemon?.let {
            return Result.success(it)
        }
        return Result.failure(RuntimeException)
    }
}