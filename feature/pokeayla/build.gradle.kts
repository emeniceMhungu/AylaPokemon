plugins {
    id("pokemon.android.library")
    id("pokemon.hilt")
    id("pokemon.library.network")
    id("pokemon.testing")
}

android {
    namespace = "com.pokemon.feature.pokeayla"
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.testing.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}