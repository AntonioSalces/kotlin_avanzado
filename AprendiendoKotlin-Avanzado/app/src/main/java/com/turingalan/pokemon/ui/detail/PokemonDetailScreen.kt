package com.turingalan.pokemon.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turingalan.pokemon.R


@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PokemonDetailScreen(
        modifier = modifier,
        name = uiState.name,
        artwork =  uiState.artwork,
    )


}

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    name: String,
    artwork: String?,
    )
{

//    Column(modifier = Modifier.fillMaxSize(),
//
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        if (artwork != null)  {
//       Image(
//           painterResource(artwork),
//           contentDescription = name,
//           contentScale = ContentScale.Crop,
//
//       )
//        }
//    }

}

@Preview
@Composable
fun PokemonDetailScreenPreview() {
    Surface {
        PokemonDetailScreen(name = "Eeve", artwork = "")

    }

}