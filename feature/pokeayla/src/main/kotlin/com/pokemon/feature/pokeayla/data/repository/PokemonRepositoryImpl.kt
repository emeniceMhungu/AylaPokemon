package com.pokemon.feature.pokeayla.data.repository

import com.pokemon.feature.pokeayla.data.api.PokeAylaAPI
import com.pokemon.feature.pokeayla.data.model.responses.PokemonCharactersResponse
import com.pokemon.feature.pokeayla.data.model.responses.PokemonDetailsResponse
import com.pokemon.feature.pokeayla.data.model.responses.SpeciesDetailsResponse
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokeAylaAPI
) : PokemonRepository {
    override suspend fun retrievePokemonList(count: Int): ApiResult<PokemonCharactersResponse, Any> {
        return pokemonApi.getPokemonList(count)
    }

    override suspend fun getPokemonDetails(id: String): ApiResult<PokemonDetailsResponse, Any> {
        return pokemonApi.getPokemonDetails(id)
    }

    override suspend fun getSpeciesDetails(id: String): ApiResult<SpeciesDetailsResponse, Any> {
        return pokemonApi.getSpeciesDetails(id)
    }

}
