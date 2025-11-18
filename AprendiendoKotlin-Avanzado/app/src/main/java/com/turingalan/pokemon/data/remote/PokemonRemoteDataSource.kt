package com.turingalan.pokemon.data.remote

import com.turingalan.pokemon.data.PokemonDataSource
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.remote.model.PokemonRemote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val api: PokemonApi,
    private val scope: CoroutineScope
): PokemonDataSource {
    override suspend fun addAll(pokemonList: List<Pokemon>) {
        TODO("Not yet implemented")
    }

    override fun observe(): Flow<List<Pokemon>> {
        return flow {
            emit(listOf<Pokemon>())
            emit(readAll())
        }.shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000L),
            replay = 1
        )
    }

    override suspend fun readAll(): List<Pokemon> {
        //TODO Remove codigo malo
        val response = api.readAll()
        val finalList = mutableListOf<Pokemon>()
        return if(response.isSuccessful){
            val body = response.body()!!
            for(result in body.results){
                val remotePokemon = readOne(result.name)
                remotePokemon?.let{ //Si no es nulo se mete dentro del bloque, si es nulo no lo cojo
                    finalList.add(it)
                }
            }
            finalList
        }else {
            listOf<Pokemon>()
        }
      }

    private suspend fun readOne(name: String): Pokemon? {
        val response = api.readOne(name)
        return if(response.isSuccessful){
            response.body()!!.toExternal()
        }else{
            null
        }
    }

    override suspend fun readOne(id: Long): Pokemon? {
        val response = api.readOne(id)
        return if(response.isSuccessful){
            response.body()!!.toExternal()
        }else{
            null
        }
    }
}

fun PokemonRemote.toExternal():Pokemon{
    return Pokemon(
        id = this.id,
        name = this.name,
        sprite = this.sprites.front_default,
        artwork = "",
    )

}