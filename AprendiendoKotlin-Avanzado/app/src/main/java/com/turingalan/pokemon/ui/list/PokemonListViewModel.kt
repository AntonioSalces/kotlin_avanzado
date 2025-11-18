package com.turingalan.pokemon.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


import javax.inject.Inject


@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PokemonRepository
): ViewModel() {


    private val _uiState: MutableStateFlow<ListUiState > =
        MutableStateFlow(value = ListUiState.Initial)

    val uiState: StateFlow<ListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = ListUiState.Loading
            repository.observe().collect { pokemons ->
                val successResponse = ListUiState.Success(pokemons.asListUiState())
                _uiState.value = successResponse
            }
/*            val allPokemon = repository.readAll()
            val successResponse = ListUiState.Success(
                allPokemon.asListUiState()
            )
            _uiState.value = successResponse*/
        }

    }

}

sealed class ListUiState {
    object Initial: ListUiState()
    object Loading: ListUiState()
    data class Success(
        val pokemons: List<ListItemUiState>
    ): ListUiState()
}

data class ListItemUiState(
    val id: Long, // Aunque luego no aparezca en la UI
    val name: String,
    val sprite:String,
)


fun Pokemon.asListItemUiState(): ListItemUiState {

    return ListItemUiState(
        id = this.id,
        name = this.name.replaceFirstChar { it.uppercase() },
        sprite = this.sprite
    )
}
fun List<Pokemon>.asListUiState():List<ListItemUiState>
= this.map(Pokemon::asListItemUiState)



