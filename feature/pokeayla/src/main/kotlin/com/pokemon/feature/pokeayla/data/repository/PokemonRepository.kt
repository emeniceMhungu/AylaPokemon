package com.pokemon.feature.pokeayla.data.repository

import com.pokemon.feature.pokeayla.data.model.responses.PokemonCharactersResponse
import com.pokemon.feature.pokeayla.data.model.responses.PokemonDetailsResponse
import com.pokemon.feature.pokeayla.data.model.responses.SpeciesDetailsResponse
import com.slack.eithernet.ApiResult

interface PokemonRepository {
    suspend fun retrievePokemonList(count: Int): ApiResult<PokemonCharactersResponse, Any>
    suspend fun getPokemonDetails(id: String): ApiResult<PokemonDetailsResponse, Any>
    suspend fun getSpeciesDetails(id: String): ApiResult<SpeciesDetailsResponse, Any>
}
