package com.pokemon.feature.pokeayla.domain.model

data class PokemonCharacterDomainModel(
    val id: String,
    val pokemonName: String,
    val pokemonUrl: String
)

fun createImageUrl(id: String, highQuality: Boolean = false) = buildString {
    if (highQuality) {
        append("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/")
    } else {
        append("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/")
    }
    append(id)
    append(".png")
}

fun String.getIdFromUrl(): String {
    val split = split("/").toMutableList().apply {
        removeIf { value ->
            value.isBlank()
        }
    }
    return split.last()
}