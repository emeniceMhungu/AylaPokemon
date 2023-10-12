package com.pokemon.feature.pokeayla.data.model.responses

import androidx.annotation.Keep
import com.pokemon.feature.pokeayla.data.model.PokemonCharacter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PokemonCharactersResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "results")
    val results: List<PokemonCharacter>
)
