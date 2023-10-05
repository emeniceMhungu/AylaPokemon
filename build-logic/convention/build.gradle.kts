plugins {
    `kotlin-dsl`
}

group = "com.pokemon.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApp"){
            id = "pokemon.app"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("network") {
            id = "pokemon.network"
            implementationClass = "ApplicationNetworkingConventionPlugin"
        }
        register("networkLibray") {
            id = "pokemon.library.network"
            implementationClass = "LibraryNetworkingConventionPlugin"
        }

        register("androidLibrary") {
            id = "pokemon.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidHilt") {
            id = "pokemon.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}