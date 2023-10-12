import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.pokemon.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ApplicationNetworkingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.devtools.ksp")
            }
            val extension = extensions.getByType<ApplicationExtension>()
            configureNetwork(extension)
        }
    }
}

internal fun Project.configureNetwork(
    commonExtension: CommonExtension<*, *, *, *>
) {

    commonExtension.apply {
        dependencies {
            "implementation"(libs.findLibrary("retrofit").get())
            "implementation"(libs.findLibrary("retrofit.converter").get())
            "implementation"(libs.findLibrary("slack.eithernet").get())
            "testImplementation"(libs.findLibrary("slack.eithernet").get())
            "implementation"(libs.findLibrary("okhttp").get())
            "implementation"(libs.findLibrary("moshi").get())
            "implementation"(libs.findLibrary("moshi.retrofit").get())
            "implementation"(libs.findLibrary("okhttp.logging").get())
            "ksp"(libs.findLibrary("moshi.codegen").get())
        }
    }

}