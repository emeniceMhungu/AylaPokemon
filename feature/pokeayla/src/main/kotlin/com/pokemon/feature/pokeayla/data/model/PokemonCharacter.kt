package com.pokemon.feature.pokeayla.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PokemonCharacter(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)
