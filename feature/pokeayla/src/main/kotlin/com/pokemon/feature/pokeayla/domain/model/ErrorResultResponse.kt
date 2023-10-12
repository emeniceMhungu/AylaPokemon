package com.pokemon.feature.pokeayla.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResultResponse(
    @Json(name = "Code")
    val code: String? = null,
    @Json(name = "Id")
    val id: String? = null,
    @Json(name = "Message")
    val message: String? = null,
    @Json(name = "ServiceCode")
    val serviceCode: String? = null,
    @Json(name = "Errors")
    val errors: List<ErrorResponse> = emptyList()
)