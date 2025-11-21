package com.turingalan.pokemon.data.local

import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.PokemonDataSource
import com.turingalan.pokemon.data.remote.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val scope: CoroutineScope,
    private val api: PokemonApi
):
    PokemonDataSource {

        private val _pokemon: MutableList<Pokemon> = mutableListOf()
        private val _pokemonFlow: MutableSharedFlow<List<Pokemon>> = MutableSharedFlow<List<Pokemon>>()
    override suspend fun addAll(pokemonList: List<Pokemon>) {
        _pokemon.addAll(pokemonList)
        _pokemonFlow.emit(_pokemon)
    }

    override fun observe(): Flow<List<Pokemon>> {
        return flow {
            emit()
        }
    }

    override suspend fun readAll(): Result<List<Pokemon>> {
        val response = api.readAll()
        val finalList = mutableListOf<Pokemon>()
        return if (response.isSuccessful) {
            val body = response.body()!!
            for (result in body.results) {
                val remotePokemon = readOne(result.name)
                remotePokemon?.let {
                    finalList.add(it)
                }
            }
            Result.success(finalList)
        } else {
            return Result.failure(RuntimeException())
        }
    }


    override suspend fun readOne(id: Long): Result<Pokemon> {
        val pokemon = _pokemon.firstOrNull() { pokemon ->
            pokemon.id == id
        }
        pokemon?.let {
            return Result.success(it)
        }
        return Result.failure(RuntimeException())
    }

    override suspend fun readOne(name: String): Pokemon? {
        TODO("Not yet implemented")
    }
}