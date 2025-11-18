package com.turingalan.pokemon.data.remote

import androidx.compose.ui.geometry.Offset
import com.turingalan.pokemon.data.remote.model.PokemonListRemote
import com.turingalan.pokemon.data.remote.model.PokemonRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("/api/v2/pokemon") //recurso que quiero alcanzar la url vaya
    suspend fun readAll(@Query("limit") limit: Int=20,
                        @Query("offset") offset: Int=0): Response<PokemonListRemote>

    @GET("/api/v2/pokemon/{id}")
    suspend fun  readOne(@Path("id") id:Long):Response<PokemonRemote>

    @GET("/api/v2/pokemon/{name}")
    suspend fun  readOne(@Path("name") name:String):Response<PokemonRemote>
}



/**
 * Recomendación:
 * La respuesta envolverla en un response de retrofit2
 */