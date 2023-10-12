package com.pokemon.feature.pokeayla.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "ErrorId")
    val errorId: String? = null,
    @Json(name = "ErrorCode")
    val errorCode: String? = null,
    @Json(name = "Message")
    val message: String? = null
)