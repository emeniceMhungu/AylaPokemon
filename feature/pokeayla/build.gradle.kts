plugins {
    id("pokemon.android.library")
    id("pokemon.hilt")
    id("pokemon.library.compose")
    id("pokemon.library.network")
    id("pokemon.testing")
}

android {
    namespace = "com.pokemon.feature.pokeayla"
}

dependencies {
    implementation(project(mapOf("path" to ":foundation:network")))
}