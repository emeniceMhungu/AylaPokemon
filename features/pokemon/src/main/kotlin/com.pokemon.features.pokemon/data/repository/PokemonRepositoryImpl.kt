package com.pokemon.pokemoninfo.features.pokemon.data.repository

import com.pokemon.pokemoninfo.features.pokemon.data.api.PokemonApi
import com.pokemon.pokemoninfo.features.pokemon.data.responses.PokemonCharactersResponse
import com.pokemon.pokemoninfo.features.pokemon.data.responses.PokemonDetailsResponse
import com.pokemon.pokemoninfo.features.pokemon.data.responses.SpeciesDetailsResponse
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
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
