package com.turingalan.pokemon.data.remote.model

import com.google.gson.annotations.SerializedName
import com.turingalan.pokemon.data.model.Pokemon
import kotlinx.serialization.Serializable

data class PokemonListRemote(
    val results:List<PokemonListItemRemote>
)

data class PokemonListItemRemote(
    val name: String,
    val url: String,
)

data class PokemonRemote(
    val id:Long,
    val name: String,
    val sprites: PokemonSprites
)

data class PokemonSprites(
    val front_default: String,
    val other : PokemonOtherSprites
)

data class PokemonOtherSprites(
    @SerializedName("official-artwork")
    val officialArtWork: PokemonOfficialArtWork,
)

data class PokemonOfficialArtWork(
    val front_default: String
)

