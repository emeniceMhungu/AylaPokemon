package com.pokemon.pokemoninfo.features.pokemon.data.repository


import com.pokemon.pokemoninfo.features.pokemon.data.responses.PokemonCharactersResponse
import com.pokemon.pokemoninfo.features.pokemon.data.responses.PokemonDetailsResponse
import com.pokemon.pokemoninfo.features.pokemon.data.responses.SpeciesDetailsResponse
import com.slack.eithernet.ApiResult

interface PokemonRepository {
    suspend fun retrievePokemonList(count: Int): ApiResult<PokemonCharactersResponse, Any>
    suspend fun getPokemonDetails(id: String): ApiResult<PokemonDetailsResponse, Any>
    suspend fun getSpeciesDetails(id: String): ApiResult<SpeciesDetailsResponse, Any>

}
