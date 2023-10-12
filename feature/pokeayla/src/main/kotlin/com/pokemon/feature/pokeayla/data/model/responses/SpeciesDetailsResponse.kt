package com.pokemon.feature.pokeayla.data.model.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class SpeciesDetailsResponse(
    @Json(name = "base_happiness")
    val baseHappiness: Int,
    @Json(name = "capture_rate")
    val captureRate: Int,
    @Json(name = "color")
    val color: SpeciesColor,
    @Json(name = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    @Json(name = "forms_switchable")
    val formsSwitchable: Boolean,
    @Json(name = "gender_rate")
    val genderRate: Int,
    @Json(name = "has_gender_differences")
    val hasGenderDifferences: Boolean,
    @Json(name = "hatch_counter")
    val hatchCounter: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_baby")
    val isBaby: Boolean,
    @Json(name = "is_legendary")
    val isLegendary: Boolean,
    @Json(name = "is_mythical")
    val isMythical: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int,
)

@Keep
@JsonClass(generateAdapter = true)
data class SpeciesColor(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)

@Keep
@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    @Json(name = "flavor_text")
    val flavorText: String,
    @Json(name = "language")
    val language: Language
)

@Keep
@JsonClass(generateAdapter = true)
data class Language(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)