// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.roborazzi) apply false

}


subprojects {

    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

//    apply(plugin = "com.diffplug.spotless")

//    spotless {
//        kotlin {
//            target("**/*.kt")
//            trimTrailingWhitespace()
//            indentWithSpaces()
//            endWithNewline()
//        }
//
//        format("misc") {
//            target("**/*.gradle", "**/*.md", "**/.gitignore")
//        }
//
//        format("xml") {
//            target("**/*.xml")
//            indentWithSpaces()
//            trimTrailingWhitespace()
//            endWithNewline()
//        }
//    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
        kotlinOptions {
            if (project.findProperty("enableComposeCompilerReports") == "true") {
                freeCompilerArgs += arrayOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                            project.buildDir.absolutePath + "/compose_metrics"
                )
                freeCompilerArgs += arrayOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                            project.buildDir.absolutePath + "/compose_metrics"
                )
                freeCompilerArgs += arrayOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-Xjvm-default=all-compatibility"
                )
            }
        }
    }
}