package com.turingalan.pokemon.ui.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.turingalan.pokemon.ui.navigation.Route
import com.turingalan.pokemon.ui.common.AppTopBar
import com.turingalan.pokemon.ui.detail.PokemonDetailScreen
import com.turingalan.pokemon.ui.list.PokemonListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val startDestination = Route.List
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(backStackEntry)
        }
    )
    {
        innerPadding ->

            val contentModifier = Modifier.consumeWindowInsets(innerPadding).padding(innerPadding)
            NavHost(
                navController = navController,
                startDestination = startDestination
            )
            {

                pokemonListDestination(contentModifier,
                    onNavigateToDetails = {
                        navController.navigateToPokemonDetails(it)
                        }
                    )
                pokemonDetailDestination(contentModifier)
            }
    }
}





