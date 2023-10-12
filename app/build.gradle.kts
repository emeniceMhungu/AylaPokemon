import java.text.SimpleDateFormat
import java.util.Date

plugins {
    id("pokemon.app")
    id("pokemon.hilt")
    id("pokemon.application.compose")
    id("pokemon.network")
}

fun getDate(): String {
    return SimpleDateFormat("yyyyMMddHHmmss").format(Date())
}

android {
    namespace = "com.pokemon.aylapokemon"

    defaultConfig {
        applicationId = "com.pokemon.aylapokemon"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            versionNameSuffix = "-" + getDate()
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            isDefault = true
        }
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

}