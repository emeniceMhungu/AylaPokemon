import com.android.build.api.dsl.ApplicationExtension
import com.pokemon.convention.configureKotlinAndroid
import com.pokemon.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {

                configureKotlinAndroid(this)

                defaultConfig {
                    targetSdk = 34
                }

                buildFeatures {
                    buildConfig = true
                    compose = true
                }


                buildTypes {

                    debug {
                        applicationIdSuffix = ".debug"
                        versionNameSuffix = ".debug"
                        isMinifyEnabled = false
                        isDebuggable = true
                        isDefault = true
                    }

                    release {
                        applicationIdSuffix = ".release"
                        versionNameSuffix = ".release"
                        isMinifyEnabled = true
                        isShrinkResources = true
                        isDebuggable = false
                        isDefault = false
                    }
                }

            }

            dependencies {
                add("implementation", libs.findLibrary("timber").get())
            }
        }
    }
}