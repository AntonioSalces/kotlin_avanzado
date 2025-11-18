package com.turingalan.pokemon.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turingalan.pokemon.ui.detail.PokemonDetailScreen
import com.turingalan.pokemon.ui.list.PokemonListScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class Route(val route:String) {
    @Serializable
    data object List:Route("pokemon_list")
    @Serializable
    data class Detail(val id:Long):Route(route = "pokemon_detail[$id]")
}

fun NavController.navigateToPokemonDetails(id:Long) {
    this.navigate(Route.Detail(id))
}

fun NavGraphBuilder.pokemonDetailDestination(
    modifier:Modifier = Modifier,
) {
    composable<Route.Detail> {
        backStackEntry ->
        PokemonDetailScreen(
            modifier = modifier,
        )
    }
}

fun NavGraphBuilder.pokemonListDestination(
    modifier:Modifier = Modifier,
    onNavigateToDetails:(Long)->Unit
) {
    composable<Route.List> {
        PokemonListScreen(modifier = modifier,
            onShowDetail = {
                id ->
                onNavigateToDetails(id)
            })


    }
}