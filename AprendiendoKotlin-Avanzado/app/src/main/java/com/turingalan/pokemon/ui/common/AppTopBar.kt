package com.turingalan.pokemon.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.turingalan.pokemon.R
import com.turingalan.pokemon.ui.detail.PokemonDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    backStackEntry: NavBackStackEntry? = null,
//    detailViewModel: PokemonDetailViewModel = hiltViewModel()
) {


    // TODO Completar el titulo en función del destino


    TopAppBar(
        title = {
            Text(text=stringResource(R.string.app_name))
        }
    )

}