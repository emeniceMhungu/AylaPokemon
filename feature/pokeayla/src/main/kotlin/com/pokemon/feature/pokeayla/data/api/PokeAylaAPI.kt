package com.pokemon.feature.pokeayla.data.api

import com.pokemon.feature.pokeayla.data.model.responses.PokemonCharactersResponse
import com.pokemon.feature.pokeayla.data.model.responses.PokemonDetailsResponse
import com.pokemon.feature.pokeayla.data.model.responses.SpeciesDetailsResponse
import com.slack.eithernet.ApiResult
import com.slack.eithernet.DecodeErrorBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAylaAPI {
    companion object {
        private const val version = "BuildConfig.ApiVersion" //FIXME: to fix this.
    }

    @GET("$version/pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): ApiResult<PokemonCharactersResponse, Any>

    @DecodeErrorBody
    @GET("$version/pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") pokemonName: String
    ): ApiResult<PokemonDetailsResponse, Any>

    @GET("$version/pokemon-species/{id}")
    suspend fun getSpeciesDetails(
        @Path("id") speciesId: String
    ): ApiResult<SpeciesDetailsResponse, Any>
}