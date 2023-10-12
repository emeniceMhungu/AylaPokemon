package com.pokemon.feature.pokeayla.domain.model

import com.pokemon.feature.pokeayla.domain.usecase.GetPokemonDetailsUseCase
import com.pokemon.feature.pokeayla.domain.usecase.GetPokemonListUseCase

typealias PokemonListResult = BaseResult<List<PokemonCharacterDomainModel>, GetPokemonListUseCase.Errors>

typealias PokemonDetailsResult = BaseResult<Pokemon, GetPokemonDetailsUseCase.Errors>